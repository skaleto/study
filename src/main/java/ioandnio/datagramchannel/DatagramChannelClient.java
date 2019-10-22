package ioandnio.datagramchannel;

import ioandnio.NIOClient;

import javax.xml.crypto.Data;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

public class DatagramChannelClient {


    public static void main(String[] args) {
        new DatagramChannelClient().startClient();
    }

    public DatagramChannelClient() {

    }

    public void startClient() {
        try (DatagramChannel channel = DatagramChannel.open()) {
            channel.configureBlocking(false);
            //UDP消息这里是不需要提前建立连接的，数据准备好直接发过去就是了

            ByteBuffer buffer = ByteBuffer.allocate(1024);
            buffer.putInt(1234567);
            buffer.flip();
            channel.send(buffer,new InetSocketAddress("127.0.0.1",9000));
            buffer.clear();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
