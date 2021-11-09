package com.skaleto.things.ioandnio.reactor.singlethread;

import com.skaleto.things.ioandnio.reactor.EventHandler;
import lombok.SneakyThrows;

import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Set;

/**
 * 单线程reactor模型
 * @author yaoyibin
 */
public class SingleThreadReactor implements Runnable {


    Selector selector;
    ServerSocketChannel serverSocketChannel;

    @SneakyThrows
    public SingleThreadReactor() {
        //初始化并创建selector和channel
        selector = Selector.open();
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(8080));
        //将channel配置为非阻塞
        serverSocketChannel.configureBlocking(false);

        //为channel注册感兴趣的事件，这里是连接事件
        SelectionKey selectionKey = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        //配置连接事件的处理器，是名为acceptor的处理器
        selectionKey.attach(new SingleThreadReactorAcceptor(serverSocketChannel, selector));
    }

    @SneakyThrows
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            selector.select();
            Set<SelectionKey> keys = selector.selectedKeys();
            keys.forEach(i -> {
                EventHandler handler = (EventHandler) i.attachment();
                handler.handle();
            });
            selector.selectedKeys().clear();
        }
    }

    public static void main(String[] args) {
        new Thread(new SingleThreadReactor()).start();
    }


}