package sundry.turing.iat;

import okhttp3.*;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class IatClient {
    private static final String HOST_URL = "http://172.31.131.18:8585";
    private static final String API_KEY = "ii-test";
    private static final String API_SECRET = "9FAC5585825445D3ADBBCAF68315277D";

    private static final String body =
            "{\"business\": {\"language\": \"zh_cn\",\"domain\":  \"default\",\"accent\":   \"mandarin\"}," +
                    " \"common\": {\"app_id\": \"ii-test\"}," +
                    "\"data\": {\"format\": \"audio/L16;rate=16000\",\"encoding\":\"raw\",\"audio\": \"";


    public static final MediaType JSON = MediaType.parse("application/json;charset=utf-8");
    private static final String path = "/Users/yaoyibin/iattest.pcm";

    public static void main(String[] args) throws Exception {
        System.out.println(add(body));
        Request req = getRequest();
        Response resp = doAuthtic(req);
        System.out.println(resp.code() + ":" + resp.body().string());
    }


    private static String add(String body) {
        String res = null;
        res = body + fileToBase64(path).toString() + "\"}}";
        return res.toString();
    }

    private static String fileToBase64(String path) {
        String base64 = null;
        InputStream in = null;
        try {
            File file = new File(path);
            in = new FileInputStream(file);
            byte[] bytes = new byte[(int) file.length()];
            in.read(bytes);
            base64 = Base64.getEncoder().encodeToString(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return base64;
    }

    public static Response doAuthtic(Request request) throws IOException {
        OkHttpClient client = new OkHttpClient.Builder().build();
        return client.newCall(request).execute();
    }

    public static Request getRequest() throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
        format.setTimeZone(TimeZone.getTimeZone("GMT"));
        String date = format.format(new Date());
        String digest = "SHA-256=" + signBody();

        RequestBody requestBody = RequestBody.create(JSON, add(body));
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
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encodestr;
    }

    private static String generateSignature(String digest, String date) throws Exception {
        URL url = new URL(HOST_URL);
        StringBuilder builder = new StringBuilder("host: ").append(url.getHost());
        if (url.getPort() != -1) {
            builder.append(":").append(url.getPort());
        }
        builder.append("\n").//
                append("date: ").append(date).append("\n").//
                append("POST ").append(url.getPath()).append(" HTTP/1.1").append("\n").//
                append("digest: ").append(digest);
        return hmacsign(builder.toString(), API_SECRET);
    }

    private static String getAuthorization(String sign) {
        return String.format("hmac api_key=\"%s\", algorithm=\"%s\", headers=\"%s\", signature=\"%s\"", //
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