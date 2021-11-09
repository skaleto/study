package com.skaleto.things.sundry.working;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;

import javax.crypto.SecretKey;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

public class JwtUtil {
    public static void main(String[] args) {

        String signingKey = "ningmengdou-1802-kaijingdasha-dunhualu";
        Map<String, Object> claims = new LinkedHashMap<>();
        claims.put("phone", "1629097874355001");
        try {
            String s = buildJwtTokenString(claims, signingKey, 60 * 60 * 1000L, UUID.randomUUID().toString());
            System.err.println(s);
            Jws<Claims> claimsJws = extractJWT(s, signingKey);
            Object exp = claimsJws.getBody().get("exp");
            System.err.println(exp);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (SignatureException e) {
            System.err.println("...");
        }


    }

    /**
     *
     * @param claims 自定义字段，例如用户id，用户角色等信息可以存储在此处
     * @param signingKey 签名key
     * @param expMillis 有效期时长毫秒数，例如一小时：60 * 60 * 1000L
     * @param jti token的id  可以根据UUID生成
     * @return jwt
     * @throws UnsupportedEncodingException
     */
    public static String buildJwtTokenString(Map<String, Object> claims, String signingKey,Long expMillis,String jti) throws UnsupportedEncodingException {

        SecretKey secretKey = Keys.hmacShaKeyFor(signingKey.getBytes("UTF-8"));
        JwtBuilder builder = Jwts.builder()
                .setId(jti)
                .setHeaderParam("typ", "JWT")
                .setExpiration(new Date(System.currentTimeMillis() + expMillis))
                .setIssuedAt(new Date())//设置签发时间
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .addClaims(claims);
        String result = builder.compact();
        return result;
    }

    /**
     * 注意：在解析jwt内容的时候有可能会抛出throws列表中的异常，过期会抛出ExpiredJwtException，签名伪造会抛出SignatureException
     * @param jwtStr token 内容
     * @param signingKey 签名key
     * @return
     * @throws UnsupportedEncodingException
     */
    public static Jws<Claims> extractJWT(String jwtStr, String signingKey) throws UnsupportedEncodingException ,ExpiredJwtException, SignatureException{

        SecretKey secretKey = null;
        secretKey = Keys.hmacShaKeyFor(signingKey.getBytes("UTF-8"));
        Jws<Claims> claimsJws = Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(jwtStr);
        return claimsJws;
    }

}
