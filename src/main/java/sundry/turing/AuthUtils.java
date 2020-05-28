package sundry.turing;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.URL;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.*;

public class AuthUtils {

    /**
     * 计算签名所需要的header参数 （http 接口）
     * @param requestUrl
     * @param apiKey
     * @param apiSecret
     * @param body
     * @return header map
     */
    public static Map<String ,String> assembleRequestHeader(String requestUrl, String apiKey, String apiSecret,byte[] body){
        URL url = null;
        try {
            url = new URL(requestUrl);
            //============Date字段
            SimpleDateFormat format = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
            format.setTimeZone(TimeZone.getTimeZone("UTC"));
            String date = format.format(new Date());
            //============Digest字段：计算body 摘要(SHA256)
            MessageDigest instance = MessageDigest.getInstance("SHA-256");
            instance.update(body);
            String  digest = "SHA256="+Base64.getEncoder().encodeToString(instance.digest());
            //============Host字段
            String host = url.getHost();
            //构建签名计算所需参数
            StringBuilder builder = new StringBuilder().
                    append("host: ").append(host).append("\n").//
                    append("date: ").append(date).append("\n").//
                    append("POST ").append(url.getPath()).append(" HTTP/1.1").append("\n").
                    append("digest: ").append(digest);
            Charset charset = Charset.forName("UTF-8");
            //使用hmac-sha256计算签名
            Mac mac = Mac.getInstance("hmacsha256");
            System.out.println(builder.toString());
            SecretKeySpec spec = new SecretKeySpec(apiSecret.getBytes(charset), "hmacsha256");
            mac.init(spec);
            byte[] hexDigits = mac.doFinal(builder.toString().getBytes(charset));
            String sha = Base64.getEncoder().encodeToString(hexDigits);
            // 构建header
            String authorization = String.format("api_key=\"%s\", algorithm=\"%s\", headers=\"%s\", signature=\"%s\"", apiKey, "hmac-sha256", "host date request-line digest", sha);
            Map<String,String > header = new HashMap<String ,String>();
            header.put("authorization",authorization);
            header.put("host",host);
            header.put("date",date);
            header.put("digest",digest);
            return header;
        } catch (Exception e) {
            throw new RuntimeException("assemble requestHeader  error:"+e.getMessage());
        }
    }

}