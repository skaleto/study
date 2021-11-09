package com.skaleto.things.multithread.executor;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorTest {

    private ExecutorService executor = Executors.newSingleThreadExecutor();

    public Future<Integer> calculate(final Integer input) {
        Callable<Integer> c = () -> {
            Thread.sleep(1000);
            return input * input;
        };
        return executor.submit(c);
    }

}
