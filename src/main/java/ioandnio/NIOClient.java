package ioandnio;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @author : ybyao
 * @Create : 2019-07-31 10:32
 */
public class NIOClient {

    public static void main(String[] args) {
        new NIOClient().startClient();
    }

    public NIOClient() {

    }

    public void startClient() {
        try (Socket socket = new Socket("localhost", 9999);
             InputStream is = socket.getInputStream();
             OutputStream os = socket.getOutputStream()) {

            os.write("Hello\0".getBytes());


            // 读取服务端发来的数据
            int b;
            while ((b = is.read()) != 0) {
                System.out.print((char) b);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
