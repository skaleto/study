package ioandnio;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

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
        try (SocketChannel channel = SocketChannel.open()) {
            channel.configureBlocking(false);
            //注意这里要用connect，因为是客户端
            channel.connect(new InetSocketAddress(9000));

            while(!channel.finishConnect()){

            }

            ByteBuffer buffer = ByteBuffer.allocate(1024);

            FileChannel fileChannel = new FileInputStream(new File("/Users/yaoyibin/mytest/nio/source.rtf")).getChannel();

            while (fileChannel.read(buffer) > 0) {
                //这里需要将默认的写模式切换到读模式供channel读取并写入
                buffer.flip();
                channel.write(buffer);
                buffer.clear();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
