package com.skaleto.things.sundry.turing.tts.v2;

import cn.hutool.core.codec.Base64;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import okio.ByteString;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.skaleto.things.sundry.turing.tts.v2.domain.request.Business;
import com.skaleto.things.sundry.turing.tts.v2.domain.request.Common;
import com.skaleto.things.sundry.turing.tts.v2.domain.request.Data;
import com.skaleto.things.sundry.turing.tts.v2.domain.request.TTSRequest;
import com.skaleto.things.sundry.turing.tts.v2.domain.response.TTSResponse;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * @author yaoyibin
 */
@Slf4j
public class TuringTTSTest {

    public static final String FILE_PATH = "/Users/yaoyibin/IdeaProjects/study/test/";

    public static final String REQUEST_URL = "http://172.31.131.18:8585/turing/v2/tts";

    private static final String APP_ID = "chongqing_test";

    private static final String API_SECRET = "1887EBA0D7224D37B5D18A29684B2477";

    public static void main(String[] args) throws Exception {
        TuringTTSTest test = new TuringTTSTest();
        test.testTTS();
    }

    //    @Test
    public void testTTS() throws Exception {
        TTSRequest request = TTSRequest.builder()
                .common(Common.builder()
                        .app_id(APP_ID)
                        .build())
                .business(Business.builder()
                        .vcn("xiaoyan")
                        .volume(50)
                        .speed(50)
                        .build())
                .data(Data.builder()
                        .status(2)
                        .text(Base64.encode("今天合肥天气真不错".getBytes()))
                        .build())
                .build();

        OkHttpClient client = new OkHttpClient.Builder().build();
        String authUrl = getAuthUrl(REQUEST_URL, APP_ID, API_SECRET);
        String url = authUrl.replace("http://", "ws://").replace("https://", "wss://");

        Request req = new Request.Builder().url(url).build();
        OutputStream os = new FileOutputStream(new File(FILE_PATH + System.currentTimeMillis() + ".pcm"));
        WebSocket webSocket = client.newWebSocket(req, new WebSocketListener() {
            @Override
            public void onClosed(@NotNull WebSocket webSocket, int code, @NotNull String reason) {
                super.onClosed(webSocket, code, reason);
                log.info("close");
            }

            @Override
            public void onClosing(@NotNull WebSocket webSocket, int code, @NotNull String reason) {
                super.onClosing(webSocket, code, reason);
                log.info("closing");
            }

            @Override
            public void onFailure(@NotNull WebSocket webSocket, @NotNull Throwable t, @Nullable Response response) {
                super.onFailure(webSocket, t, response);
                log.info("failure");
            }

            @Override
            public void onMessage(@NotNull WebSocket webSocket, @NotNull String text) {
                super.onMessage(webSocket, text);
                log.info("receive message");
                TTSResponse resp = JSON.parseObject(text, TTSResponse.class);
                //处理返回数据
                System.out.println("receive=>" + text);
                if (resp != null) {
                    if (resp.getCode() != 0) {
                        System.out.println("error=>" + resp.getMessage() + " sid=" + resp.getSid());
                        return;
                    }
                    if (resp.getData() != null) {
                        String result = resp.getData().getAudio();
                        byte[] audio = java.util.Base64.getDecoder().decode(result);

                        try {
                            os.write(audio);
                            os.flush();
                            if (resp.getData().getStatus() == 2) {
                                //resp.data.status ==2 说明数据全部返回完毕，可以关闭连接，释放资源
                                System.out.println("session end ");
                                webSocket.close(1000, "");
                                try {
                                    os.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        } catch (Exception e) {
                            log.error("error occurred", e);
                        }
                    }
                }
            }

            @Override
            public void onMessage(@NotNull WebSocket webSocket, @NotNull ByteString bytes) {
                super.onMessage(webSocket, bytes);
            }

            @Override
            public void onOpen(@NotNull WebSocket webSocket, @NotNull Response response) {
                super.onOpen(webSocket, response);
                log.info("start send data");
                webSocket.send(JSON.toJSONString(request));
                log.info("send success");
            }
        });

    }

    public static String getAuthUrl(String hostUrl, String apiKey, String apiSecret) throws Exception {
        URL url = new URL(hostUrl);
        SimpleDateFormat format = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
        format.setTimeZone(TimeZone.getTimeZone("GMT"));
        String date = format.format(new Date());
        StringBuilder builder = new StringBuilder("host: ").append(url.getHost()).append("\n").
                append("date: ").append(date).append("\n").
                append("GET ").append(url.getPath()).append(" HTTP/1.1");
        Charset charset = StandardCharsets.UTF_8;
        Mac mac = Mac.getInstance("hmacsha256");
        SecretKeySpec spec = new SecretKeySpec(apiSecret.getBytes(charset), "hmacsha256");
        mac.init(spec);
        byte[] hexDigits = mac.doFinal(builder.toString().getBytes(charset));
        String sha = java.util.Base64.getEncoder().encodeToString(hexDigits);

        String authorization = String.format("hmac api_key=\"%s\", algorithm=\"%s\", headers=\"%s\", signature=\"%s\"", apiKey, "hmac-sha256", "host date request-line", sha);
        HttpUrl httpUrl = HttpUrl.parse("http://" + url.getHost() + ":" + url.getPort() + url.getPath()).newBuilder().
                addQueryParameter("authorization", java.util.Base64.getEncoder().encodeToString(authorization.getBytes(charset))).
                addQueryParameter("date", date).
                addQueryParameter("host", url.getHost()).
                build();
        return httpUrl.toString();
    }

}
