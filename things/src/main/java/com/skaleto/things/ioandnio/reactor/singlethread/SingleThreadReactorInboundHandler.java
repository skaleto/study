package com.skaleto.things.ioandnio.reactor.singlethread;

import com.skaleto.things.ioandnio.reactor.EventHandler;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

@Slf4j
public class SingleThreadReactorInboundHandler implements EventHandler {

    SocketChannel socketChannel;
    Selector selector;

    public SingleThreadReactorInboundHandler(SocketChannel socketChannel, Selector selector) {
        this.socketChannel = socketChannel;
        this.selector = selector;
    }

    @Override
    public void handle() {
        read();
    }

    private void read() {
        try {
            ByteBuffer buffer = ByteBuffer.allocate(1024);

            int readBytes;
            do {
                readBytes = socketChannel.read(buffer);
                if (readBytes > 0) {
                    log.info(new String(Arrays.copyOf(buffer.array(), buffer.position()), StandardCharsets.UTF_8));
                }
            } while (readBytes > 0);

            SelectionKey key = socketChannel.register(selector, SelectionKey.OP_WRITE);
            key.attach(new SingleThreadReactorOutboundHandler(socketChannel, selector));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
