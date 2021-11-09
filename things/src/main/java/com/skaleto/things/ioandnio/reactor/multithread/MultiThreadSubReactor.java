package com.skaleto.things.ioandnio.reactor.multithread;

import com.skaleto.things.ioandnio.reactor.EventHandler;
import lombok.SneakyThrows;

import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Set;

/**
 * 多线程reactor模型-从reactor
 *
 * @author yaoyibin
 */
public class MultiThreadSubReactor implements Runnable {

    ServerSocketChannel serverSocketChannel;
    Selector selector;

    boolean block = false;

    public MultiThreadSubReactor(ServerSocketChannel serverSocketChannel, Selector selector) {
        this.serverSocketChannel = serverSocketChannel;
        this.selector = selector;
    }

    @SneakyThrows
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            while (!Thread.interrupted() && !block) {
                int selected = selector.select();
                if (selected == 0) {
                    continue;
                }
                Set<SelectionKey> keys = selector.selectedKeys();
                keys.forEach(i -> {
                    EventHandler handler = (EventHandler) i.attachment();
                    handler.handle();
                });
                selector.selectedKeys().clear();
            }
        }
    }

    public void block(boolean block) {
        this.block = block;
    }

}
