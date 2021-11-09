package com.skaleto.things.threadpool;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author yaoyibin
 */
@Slf4j
public class TestThreadPool {


    ThreadPoolExecutor pool = new ThreadPoolExecutor(1,
            2,
            60L, TimeUnit.MILLISECONDS,
            new LinkedBlockingDeque<>(),
            new ThreadFactoryBuilder().setNameFormat("receive-audio-pool-%d").build(),
            new ThreadPoolExecutor.AbortPolicy());

    public static void main(String[] args) throws InterruptedException {
        TestThreadPool test = new TestThreadPool();
        for (int i = 0; i < 10; i++) {
            test.pool.submit(() -> {
                log.info("active" + test.pool.getPoolSize()+" "+test.pool.getQueue().size());
                Thread.sleep(20);
                throw new IllegalStateException();
            });

        }
        Thread.sleep(5000);
        log.info(String.valueOf(test.pool.getPoolSize()));

    }
}
