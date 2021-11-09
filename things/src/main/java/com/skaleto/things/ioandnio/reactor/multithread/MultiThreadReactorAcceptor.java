package com.skaleto.things.ioandnio.reactor.multithread;

import cn.hutool.core.thread.ThreadFactoryBuilder;
import com.skaleto.things.ioandnio.reactor.EventHandler;
import lombok.SneakyThrows;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * IO接收器
 *
 * @author yaoyibin
 */
public class MultiThreadReactorAcceptor implements EventHandler {

    ServerSocketChannel serverSocketChannel;
    final int subReactorNums = Runtime.getRuntime().availableProcessors();
    Selector[] selectors;
    MultiThreadSubReactor[] subReactors;

    int currentSubReactorIndex = 0;

    ThreadPoolExecutor subReactorPool = new ThreadPoolExecutor(
            subReactorNums,
            subReactorNums,
            60,
            TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(1024),
            new ThreadFactoryBuilder().setNamePrefix("SUB-REACTOR-").build()
    );

    public MultiThreadReactorAcceptor(ServerSocketChannel serverSocketChannel) {
        this.serverSocketChannel = serverSocketChannel;

        selectors = new Selector[subReactorNums];
        subReactors = new MultiThreadSubReactor[subReactorNums];
        IntStream.range(0, subReactorNums).forEach(i -> {
            try {
                selectors[i] = Selector.open();
                subReactors[i] = new MultiThreadSubReactor(serverSocketChannel, selectors[i]);
                //subReactor提交给线程池执行
                subReactorPool.execute(subReactors[i]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    @SneakyThrows
    public void handle() {
        //建立连接
        SocketChannel socketChannel = serverSocketChannel.accept();
        socketChannel.configureBlocking(false);

        selectors[currentSubReactorIndex].wakeup();
        subReactors[currentSubReactorIndex].block(true);

        //此时连接已经建立，将socketChannel注册到某个selector上，并监听Read事件
        SelectionKey selectionKey = socketChannel.register(selectors[currentSubReactorIndex], SelectionKey.OP_READ);
        //交给handler处理
        selectionKey.attach(new MultiThreadReactorInboundHandler(socketChannel, selectors[currentSubReactorIndex]));

        subReactors[currentSubReactorIndex].block(false);
        selectors[currentSubReactorIndex].wakeup();

        //检查当前的subReactor是否越界，是则重置index
        configCurrentSubReactorIndex();
    }

    private void configCurrentSubReactorIndex() {
        if (++currentSubReactorIndex >= subReactorNums) {
            currentSubReactorIndex = 0;
        }
    }
}
