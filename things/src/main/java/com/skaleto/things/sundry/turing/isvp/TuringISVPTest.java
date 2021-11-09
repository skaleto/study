package com.skaleto.things.sundry.turing.isvp;


import cn.hutool.core.codec.Base64;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import com.skaleto.things.sundry.turing.isvp.domain.*;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

@Slf4j
public class TuringISVPTest {

    private static final String APP_ID = "chongqing_test";

    private static final String API_SECRET = "960972AAFEE84436AB39C226C318B63B";

    public static final String DB_NAME = "testdb";

    public static final String CREATE_DB = "http://172.31.131.18:8585/tuling/isvp/v2/db/" + DB_NAME;

    public static final String DIGIT_PWD_REGISTER = "http://172.31.131.18:8585/tuling/isvp/v2/vpp/enroll";

    public static final String DIGIT_PWD_VERIFY = "http://172.31.131.18:8585/tuling/isvp/v2/vpp/verify";

    public static final String FREE_SAY_REGISTER = "http://172.31.131.18:8585/tuling/isvp/v2/mlv/enroll";

    public static final String FREE_SAY_VERIFY = "http://172.31.131.18:8585/tuling/isvp/v2/mlv/verify";

    public static final String VOICE_PRINT_DIR = "/Users/yaoyibin/IdeaProjects/study/test/voiceprint/";

    @Test
    public void testCreateDB() {
        CreateDBRequest request = CreateDBRequest.builder().desc("测试用库").build();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<CreateDBResponse> response = restTemplate.exchange(CREATE_DB, HttpMethod.PUT, new HttpEntity<>(request), CreateDBResponse.class);

        log.info("");

    }

    @Test
    public void testDigitPwdRegister() throws Exception {
        DigitPwdRegisterRequest request = DigitPwdRegisterRequest.builder()
                .bizId("biz1")
                .dbName(DB_NAME)
                .audios(new Audio[]{Audio.builder()
                        .audioId("audioid1")
                        .audioBytes(encodeAudio(new File(VOICE_PRINT_DIR + "register/digitPwdRegister.wav")))
                        .isvCode("12345678")
                        .build()})
                .build();


        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<DigitPwdRegisterRequest> req = new HttpEntity<>(request,
                generateHeaders(new URL(DIGIT_PWD_REGISTER), HttpMethod.POST, APP_ID, API_SECRET, true, JSON.toJSONString(request)));
        Object response = restTemplate.postForObject(DIGIT_PWD_REGISTER, req, Object.class);

        log.info("");

    }

    @Test
    public void testDigitPwdVerify() throws Exception {
        DigitPwdVerifyRequest request = DigitPwdVerifyRequest.builder()
                .bizId("biz1")
                .audio(Audio.builder()
                        .audioId("audioid2")
                        .audioBytes(Base64.encode(new File(VOICE_PRINT_DIR + "verify/digitPwdVerify.wav")))
                        .isvCode("98765432")
                        .build())
                .build();

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<DigitPwdVerifyRequest> req = new HttpEntity<>(request,
                generateHeaders(new URL(DIGIT_PWD_VERIFY), HttpMethod.POST, APP_ID, API_SECRET, true, JSON.toJSONString(request)));

        Object response = restTemplate.postForObject(DIGIT_PWD_VERIFY, req, Object.class);

        log.info("");

    }


    @Test
    public void testFreeSayRegister() throws Exception {
        FreeSayRegisterRequest request = FreeSayRegisterRequest.builder()
                .bizId("biz2")
                .dbName(DB_NAME)
                .audios(new Audio[]{Audio.builder()
                        .audioId("audioid3")
                        .audioBytes(Base64.encode(new File(VOICE_PRINT_DIR + "register/digitPwdRegister.wav")))
                        .build()})
                .build();

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<FreeSayRegisterRequest> req = new HttpEntity<>(request,
                generateHeaders(new URL(FREE_SAY_REGISTER), HttpMethod.POST, APP_ID, API_SECRET, true, JSON.toJSONString(request)));

        Object response = restTemplate.postForObject(FREE_SAY_REGISTER, req, Object.class);

        log.info("");

    }

    @Test
    public void testFreeSayVerify() throws Exception {
        FreeSayVerifyRequest request = FreeSayVerifyRequest.builder()
                .bizId("biz2")
                .audio(Audio.builder()
                        .audioId("audioid4")
                        .audioBytes(Base64.encode(new File(VOICE_PRINT_DIR + "verify/digitPwdVerify.wav")))
                        .build())
                .build();

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<FreeSayVerifyRequest> req = new HttpEntity<>(request,
                generateHeaders(new URL(FREE_SAY_VERIFY), HttpMethod.POST, APP_ID, API_SECRET, true, JSON.toJSONString(request)));

        Object response = restTemplate.postForObject(FREE_SAY_VERIFY, req, Object.class);

        log.info("");

    }


    private static String encodeAudio(File file) throws IOException {
        try (FileInputStream fis = new FileInputStream(file)) {
            fis.skip(44);
            return Base64.encode(fis);
        } catch (Exception e) {
            log.error("encodeAudio error", e);
            throw e;
        }
    }

    public static HttpHeaders generateHeaders(URL url, HttpMethod method, String apiKey, String apiSecret, boolean hasBody, String body) throws Exception {
        HttpHeaders headers = new HttpHeaders();

        SimpleDateFormat format = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
        format.setTimeZone(TimeZone.getTimeZone("GMT"));
        String date = format.format(new Date());

        headers.set("Host", url.getHost());
        headers.set("Date", date);

        Mac mac = Mac.getInstance("hmacsha256");
        SecretKeySpec spec = new SecretKeySpec(apiSecret.getBytes(StandardCharsets.UTF_8), "hmacsha256");
        mac.init(spec);
        StringBuilder signStrBuilder = new StringBuilder()
                .append("host: ").append(url.getHost()).append("\n")
                .append("date: ").append(date).append("\n")
                .append(method.toString()).append(" ").append(url.getPath()).append(" HTTP/1.1");

        byte[] hexDigits = mac.doFinal(signStrBuilder.toString().getBytes(StandardCharsets.UTF_8));
        String sha = java.util.Base64.getEncoder().encodeToString(hexDigits);

        if (!hasBody) {
            String authorization = String.format("hmac api_key=\"%s\", algorithm=\"%s\", headers=\"%s\", signature=\"%s\"",
                    apiKey, "hmac-sha256", "host date request-line", sha);

            headers.set("'Authorization", authorization);

        } else {

            byte[] bodyHex = mac.doFinal(body.getBytes(StandardCharsets.UTF_8));
            String bodyDigest = java.util.Base64.getEncoder().encodeToString(bodyHex);
            headers.set("Digest", "SHA-256=" + bodyDigest);

            String authorization = String.format("hmac api_key=\"%s\", algorithm=\"%s\", headers=\"%s\", signature=\"%s\"",
                    apiKey, "hmac-sha256", "host date request-line digest", sha);
            headers.set("Authorization", authorization);
        }


        return headers;
    }
}
