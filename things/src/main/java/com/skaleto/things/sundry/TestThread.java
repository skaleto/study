package com.skaleto.things.sundry;

import java.util.concurrent.CountDownLatch;
import java.util.stream.IntStream;

public class TestThread {

    private static int a = 0;

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(10);
        Object o = new Object();
        IntStream.range(0, 10).forEach(i -> {
            new Thread(() -> {
                synchronized (o) {
                    IntStream.range(0, 10000).forEach(j -> a++);
                    latch.countDown();
                }
            }).start();
        });

        latch.await();
        System.out.println(a);

    }
}
