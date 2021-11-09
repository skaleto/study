package com.skaleto.things.ioandnio.reactor.singlethread;

import com.skaleto.things.ioandnio.reactor.EventHandler;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

/**
 * @author yaoyibin
 */
@Slf4j
public class SingleThreadReactorOutboundHandler implements EventHandler {

    SocketChannel socketChannel;
    Selector selector;

    public SingleThreadReactorOutboundHandler(SocketChannel socketChannel, Selector selector) {
        this.socketChannel = socketChannel;
        this.selector = selector;
    }

    @Override
    public void handle() {
        write();
    }

    private void write() {
        try {
            socketChannel.write(ByteBuffer.wrap("Received client data".getBytes()));
        } catch (IOException e) {
            log.error("", e);
        }
        selector.selectedKeys().forEach(SelectionKey::cancel);
    }
}
