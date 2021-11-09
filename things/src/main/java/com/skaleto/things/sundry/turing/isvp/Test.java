package com.skaleto.things.sundry.turing.isvp;

import okhttp3.*;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
public class Test {
    private static final String HOST_URL = "https://rest-api.xfyun.cn/v2/***";
    private static final String API_KEY = "***";
    private static final String API_SECRET = "***";
    public static final MediaType JSON = MediaType.parse("application/json,version=1.0");
    private static final String body = "***";
    public static void main(String[] args) throws Exception {
        Request req = getRequest();
        Response resp = doAuthtic(req);
        System.out.println(resp.code() + ":" + resp.body().string());
    }
    public static Response doAuthtic(Request request) throws IOException {
        OkHttpClient client = new OkHttpClient.Builder().build();
        return client.newCall(request).execute();
    }
    public static Request getRequest() throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("EEE, dd MMM yyyy HH: mm:ss z", Locale.US);
        format.setTimeZone(TimeZone.getTimeZone("GMT"));
        String date = format.format(new Date());
        String digest = "SHA-256=" + signBody();
        RequestBody requestBody = RequestBody.create(JSON, body);
        Request request = new Request.Builder().url(HOST_URL).//
                addHeader("Content-Type", "application/json").//
                addHeader("Date", date).//
                addHeader("Digest", digest).//
                addHeader("Authorization", getAuthorization(generateSignature(digest, date))).//
                post(requestBody).//
                build();
        return request;
    }
    private static String signBody() throws Exception {
        MessageDigest messageDigest;
        String encodestr = "";
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(body.getBytes("UTF-8"));
            encodestr = Base64.getEncoder().encodeToString(messageDigest.digest());
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encodestr;
    }
    private static String generateSignature(String digest, String date) throws Exception {
        URL url = new URL(HOST_URL);
        StringBuilder builder = new StringBuilder("host: ").append(url.getHost()).append(":").append(url.getPort()).append("\n")
                .append("date: ").append(date).append("\n")
                .append("POST ").append(url.getPath()).append(" HTTP/1.1").append("\n")
                .append("digest: ").append(digest);
        return hmacsign(builder.toString(), API_SECRET);
    }
    private static String getAuthorization(String sign) {
        return String.format("hmac api_key=\"%s\", algorithm=\"%s\", headers =\"%s\", signature=\"%s\"", //
                API_KEY, "hmac-sha256", "host date request-line digest", sign);
    }
    private static String hmacsign(String signature, String apiSecret) throws Exception {
        Charset charset = Charset.forName("UTF-8");
        Mac mac = Mac.getInstance("hmacsha256");
        SecretKeySpec spec = new SecretKeySpec(apiSecret.getBytes(charset), "hmacsha256");
        mac.init(spec);
        byte[] hexDigits = mac.doFinal(signature.getBytes(charset));
        return Base64.getEncoder().encodeToString(hexDigits);
    }
}
