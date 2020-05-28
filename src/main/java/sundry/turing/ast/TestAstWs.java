package sundry.turing.ast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import okhttp3.*;
import okio.ByteString;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.UUID;

/**
 * 实时转写引擎测试demo
 *
 */
public class TestAstWs {

    private static Logger logger = LoggerFactory.getLogger(TestAstWs.class);

    /**
     * TODO 测试ip端口替换为真实地址
     */
    private static final String SERVICE_ADDRESS = "ws://ip:port";
    private static final String TRANSCRIBE_SERVICE_URI = "/tuling/ast/v2/";
    /**
     * 音频文件路径
     * TODO 测试文件路径替换为真实路径
     * 这里使用音频一段一段传入引擎模拟对话的实时场景
     */
    private static final String AUDIO_FILE_PATH = "*******/test_chongqing_ed.wav";
    /**
     * 采样率
     * AST引擎仅支持16k采样率，如果音频格式不对，请先进行转码
     */
    private static final int SAMPLE_RATE = 16000;
    /**
     * 采样深度
     * AST引擎仅支持16bit采样深度，如果音频格式不对，请先进行转码
     */
    private static final int BITS_PER_SAMPLE = 16;
    /**
     * 每个数据包的字节数
     */
    private static final int FS = 4096;

    public static void main(String[] args) {
        OkHttpClient client = new OkHttpClient.Builder().build();
        Request request = new Request.Builder().url(generateUrl()).build();
        WebSocket ws = client.newWebSocket(request, new WebSocketListener() {
            boolean isRunning = false;

            @Override
            public void onClosed(@NotNull WebSocket webSocket, int code, @NotNull String reason) {
                super.onClosed(webSocket, code, reason);
            }

            @Override
            public void onClosing(@NotNull WebSocket webSocket, int code, @NotNull String reason) {
                super.onClosing(webSocket, code, reason);
                isRunning = false;
                logger.info("ws closing");
            }

            @Override
            public void onFailure(@NotNull WebSocket webSocket, @NotNull Throwable t, @Nullable Response response) {
                super.onFailure(webSocket, t, response);
                logger.warn("ws failure occurred");
            }

            @Override
            public void onMessage(@NotNull WebSocket webSocket, @NotNull String text) {
                super.onMessage(webSocket, text);
//                logger.info("ws receive text msg");
                getContent(text);
            }

            @Override
            public void onMessage(@NotNull WebSocket webSocket, @NotNull ByteString bytes) {
                super.onMessage(webSocket, bytes);
                logger.info("ws receive byte msg");
            }

            @Override
            public void onOpen(@NotNull WebSocket webSocket, @NotNull Response response) {
                super.onOpen(webSocket, response);
                isRunning = true;
                //默认偏移量44，pcm格式的wav文件一般文件头44字节，跳过44字节后为音频数据
                int offset = 44;
                while (isRunning) {
                    try (RandomAccessFile file = new RandomAccessFile(AUDIO_FILE_PATH, "r")) {
                        if (offset >= file.length()) {
                            break;
                        }
                        file.seek(offset);
                        byte[] buffer = new byte[FS];
                        int readBytes = file.read(buffer);
                        if (readBytes < 0) {
                            break;
                        }
                        webSocket.send(ByteString.of(buffer));
                        offset += readBytes;

                    } catch (IOException e) {
                        logger.error("read file error", e);
                    }
                }

                logger.info("finish send data");

                //发送一个空的数据包标识结束
                webSocket.send(ByteString.EMPTY);

            }
        });
    }

    public static String generateUrl() {
        StringBuilder builder = new StringBuilder();
        builder.append(SERVICE_ADDRESS).append(TRANSCRIBE_SERVICE_URI);

        String sessionId = UUID.randomUUID().toString();
        String appId = "app1";
        String bizId = "A";
        String bizName = "biz1";

        String params = String.format("%s?appId=%s&bizId=%s&bizName=%s&sr=%d&bps=%d&fs=%d", sessionId, appId, bizId, bizName, SAMPLE_RATE, BITS_PER_SAMPLE, FS);
        builder.append(params);

        return builder.toString();
    }

    /**
     * 把转写结果解析为句子
     */
    public static void getContent(String message) {
        StringBuilder resultBuilder = new StringBuilder();
        JSONObject messageObj = JSON.parseObject(message);
        String msgType = messageObj.getString("msgtype");

        //只处理句子最终结果，中间结果不处理
        if (!"sentence".equals(msgType)) {
            return;
        }

        JSONArray ws = messageObj.getJSONArray("ws");
        ws.forEach(i -> {
            JSONArray cw = ((JSONObject) i).getJSONArray("cw");
            cw.forEach(j -> {
                String res = ((JSONObject) j).getString("w");
                resultBuilder.append(res);
            });
        });

        logger.info(resultBuilder.toString());

    }
}
