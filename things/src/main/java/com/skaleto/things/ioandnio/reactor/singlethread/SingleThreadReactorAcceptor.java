package com.skaleto.things.ioandnio.reactor.singlethread;

import com.skaleto.things.ioandnio.reactor.EventHandler;
import lombok.SneakyThrows;

import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * IO连接处理器
 *
 * @author yaoyibin
 */
public class SingleThreadReactorAcceptor implements EventHandler {

    ServerSocketChannel serverSocketChannel;
    Selector selector;

    public SingleThreadReactorAcceptor(ServerSocketChannel serverSocketChannel, Selector selector) {
        this.serverSocketChannel = serverSocketChannel;
        this.selector = selector;
    }

    @Override
    @SneakyThrows
    public void handle() {
        //建立连接
        SocketChannel socketChannel = serverSocketChannel.accept();

        socketChannel.configureBlocking(false);
        //此时连接已经建立，将selection重新注册为IO读事件
        SelectionKey selectionKey = socketChannel.register(selector, SelectionKey.OP_READ);
        //交给handler处理
        selectionKey.attach(new SingleThreadReactorInboundHandler(socketChannel, selector));
    }
}
