package ioandnio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * @author : ybyao
 * @Create : 2019-07-31 10:03
 */
public class NIOServer {


    public static void main(String[] args) {
        new NIOServer().createServer();
    }

    public NIOServer() {
    }

    public void createServer() {
        try (Selector selector = Selector.open();
             ServerSocketChannel listenChannel = ServerSocketChannel.open()) {

            listenChannel.bind(new InetSocketAddress(9999));
            listenChannel.configureBlocking(false);
            listenChannel.register(selector, SelectionKey.OP_ACCEPT);

            ByteBuffer buffer = ByteBuffer.allocate(1024);
            while (true) {
                //阻塞直到有符合select条件的channel生效
                selector.select();
                Iterator<SelectionKey> selectionKeys = selector.selectedKeys().iterator();

                while (selectionKeys.hasNext()) {
                    SelectionKey key = selectionKeys.next();

                    if (key.isAcceptable()) {
                        SocketChannel channel = ((ServerSocketChannel) key.channel()).accept();
                        channel.configureBlocking(false);
                        channel.register(selector, SelectionKey.OP_READ);

                        System.out.println("Remote address:" + channel.getRemoteAddress());
                    } else if (key.isReadable()) {
                        buffer.clear();

                        // 读取到流末尾说明TCP连接已断开，
                        // 因此需要关闭通道或者取消监听READ事件
                        // 否则会无限循环
                        if (((SocketChannel) key.channel()).read(buffer) == -1) {
                            key.channel().close();
                            continue;
                        }

                        //准备对buffer进行读取
                        buffer.flip();
                        while (buffer.hasRemaining()) {
                            byte b = buffer.get();

                            if (b == 0) { // 客户端消息末尾的\0
                                System.out.println();

                                // 响应客户端
                                buffer.clear();
                                buffer.put("Hello, Client!\0".getBytes());
                                buffer.flip();
                                while (buffer.hasRemaining()) {
                                    ((SocketChannel) key.channel()).write(buffer);
                                }
                            } else {
                                System.out.print((char) b);

                            }
                        }
                    }

                    selectionKeys.remove();
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
