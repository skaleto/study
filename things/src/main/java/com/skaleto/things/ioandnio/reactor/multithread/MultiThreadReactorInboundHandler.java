package com.skaleto.things.ioandnio.reactor.multithread;

import com.skaleto.things.ioandnio.reactor.EventHandler;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * 多线程数据入站处理器
 * @author yaoyibin
 */
@Slf4j
public class MultiThreadReactorInboundHandler implements EventHandler {

    SocketChannel socketChannel;
    Selector selector;

    public MultiThreadReactorInboundHandler(SocketChannel socketChannel, Selector selector) {
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
            key.attach(new MultiThreadReactorOutboundHandler(socketChannel, selector));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
