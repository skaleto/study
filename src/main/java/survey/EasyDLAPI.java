package survey;

import com.alibaba.fastjson.JSON;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;

/**
 * @author : ybyao
 * @Create : 2019-09-29 9:28
 */
public class EasyDLAPI {

    public static final String SERVICE_URL = "https://aip.baidubce.com/rpc/2.0/ai_custom/v1/sound_cls/dogcataudio";

    public static final String ACCESS_TOKEN = "24.8f0c422c340208e7d07aedee2e88870f.2592000.1572315102.282335-17374880";

    public static void main(String[] args) throws Exception {
        EasyDLAPI easyDLAPI = new EasyDLAPI();
        String audioStr = easyDLAPI.base64("C:\\Users\\iflyrec\\Downloads\\audio-cats-and-dogs\\cats_dogs\\test\\dogs\\dog_barking_34.wav");

        EasyDLEntity entity = new EasyDLEntity();
        entity.setSound(audioStr);
        entity.setTop_num("2");

        easyDLAPI.sendRequest(SERVICE_URL + "?access_token=" + ACCESS_TOKEN, "POST", JSON.toJSONBytes(entity));
    }


    public String base64(String filePath) throws Exception {
        File file = new File(filePath);
        long length = file.length();
        try (FileInputStream fis = new FileInputStream(file)) {
            //暂不考虑较大的long转int的问题
            byte[] buffer = new byte[(int) length];
            fis.read(buffer);

            byte[] encode = Base64.getEncoder().encode(buffer);
            return new String(encode);
        }
    }

    public void sendRequest(String url, String method, byte[] data) throws IOException {
        URL u = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) u.openConnection();
        connection.setRequestMethod(method);
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.connect();

        connection.getOutputStream().write(data);


        StringBuilder resultBuffer = new StringBuilder();
        BufferedReader buffer = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String str;
        while ((str = buffer.readLine()) != null) {
            resultBuffer.append(str);
        }
        System.out.println(resultBuffer.toString());

    }
}
