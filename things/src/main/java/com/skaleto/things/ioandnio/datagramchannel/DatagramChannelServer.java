package com.skaleto.things.ioandnio.datagramchannel;

import com.skaleto.things.ioandnio.NIOServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

public class DatagramChannelServer {

    public static void main(String[] args) {
        new DatagramChannelServer().createServer();
    }

    public DatagramChannelServer() {
    }

    public void createServer() {
        //打开选择器，打开channel
        try (Selector selector = Selector.open();
             DatagramChannel listenChannel = DatagramChannel.open()) {
            listenChannel.bind(new InetSocketAddress(9000));
            listenChannel.configureBlocking(false);

            //注意这里监听的是read，因为UDP没有连接事件
            listenChannel.register(selector, SelectionKey.OP_READ);

            ByteBuffer buffer = ByteBuffer.allocate(1024);
            while (true) {
                selector.select();
                Iterator<SelectionKey> selectionKeys = selector.selectedKeys().iterator();

                while (selectionKeys.hasNext()) {
                    SelectionKey key = selectionKeys.next();
                    if (key.isReadable()) {
                        //这里的address包含了客户端的IP端口等信息
                        SocketAddress address = listenChannel.receive(buffer);
                        buffer.flip();
                        System.out.println(buffer.getInt());
                        buffer.clear();
                    }
                    //处理完事件后需要将该键集删去
                    selectionKeys.remove();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
