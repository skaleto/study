package com.skaleto.things.multithread.guavafuture;

import com.google.common.util.concurrent.*;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GuavaFutureTest {

    public static Integer digit = 0;

    public static void main(String[] args) throws InterruptedException {
        Callable<Integer> damage = new Damage();
        Callable<Integer> healing = new Healing();

        //创建java线程池
        ExecutorService pool = Executors.newFixedThreadPool(10);
        //包装为guava的线程池（因为要拿到ListenableFuture对象）
        ListeningExecutorService guavaPool = MoreExecutors.listeningDecorator(pool);

        //将两个线程实现类放入guavaPool
        ListenableFuture damageFuture = guavaPool.submit(damage);
        ListenableFuture healingFuture = guavaPool.submit(healing);

        //绑定异步回调
        Futures.addCallback(damageFuture, new FutureCallback<Integer>() {

            public void onSuccess(@Nullable Integer integer) {
                digit += integer;
            }

            public void onFailure(Throwable throwable) {
                System.out.println(throwable.getMessage());
            }
        }, guavaPool);
        Futures.addCallback(healingFuture, new FutureCallback<Integer>() {

            public void onSuccess(@Nullable Integer integer) {
                digit += integer;
            }

            public void onFailure(Throwable throwable) {
                System.out.println(throwable.getMessage());
            }
        }, guavaPool);

        Thread.sleep(1000);
        System.out.println(digit);

    }

    static class Damage implements Callable<Integer> {

        public Integer call() throws Exception {
            return -10000;
        }
    }

    static class Healing implements Callable<Integer> {

        public Integer call() throws Exception {
            return 20000;
        }
    }


}
