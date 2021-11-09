package com.skaleto.things.ioandnio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
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
        //打开选择器，打开channel
        try (Selector selector = Selector.open();
             ServerSocketChannel listenChannel = ServerSocketChannel.open()) {
            //将channel绑定至9999端口
            listenChannel.bind(new InetSocketAddress(9000));
            //channel需要设置为非阻塞模式，而FileChannel没有configureBlocking方法，无法设置为非阻塞
            listenChannel.configureBlocking(false);
            /*
             * selector可以注册多种事件
             * OP_CONNECT:监听socket通道的连接就绪
             * OP_ACCEPT:监听接受就绪事件
             * OP_WRITE:监听写就绪事件
             * OP_READ:监听读就绪事件
             * 如果需要监听多种事件，则例如 OP_CONNECT|OP_ACCEPT|OP_WRITE 使用位或操作将三种事件组合起来，
             * 但是要注意的是，不同类型的channel，同时组合的事件是有不同的，例如ServerSocketChannel，就仅支持注册ACCEPT事件，而SocketChannel则支持多种事件
             */
            listenChannel.register(selector, SelectionKey.OP_ACCEPT);
            //也可以在注册的时候附带上一个用于标识的对象
            //listenChannel.register(selector,SelectionKey.OP_ACCEPT,new Object());

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
                    //处理完事件后需要将该键集删去
                    selectionKeys.remove();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
