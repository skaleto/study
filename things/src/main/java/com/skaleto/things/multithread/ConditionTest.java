package com.skaleto.things.multithread;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;

/**
 * @author : ybyao
 * @Create : 2019-11-08 10:54
 */
public class ConditionTest implements Condition {

    @Override
    public void await() throws InterruptedException {

    }

    @Override
    public void awaitUninterruptibly() {

    }

    @Override
    public long awaitNanos(long nanosTimeout) throws InterruptedException {
        return 0;
    }

    @Override
    public boolean await(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }


    @Override
    public boolean awaitUntil(Date deadline) throws InterruptedException {
        return false;
    }


    @Override
    public void signal() {

    }

    @Override
    public void signalAll() {

    }
}
