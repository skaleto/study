package multithread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author : ybyao
 * @Create : 2019-11-06 10:52
 * 自定义互斥锁
 */
public class MySharedLock implements Lock {

    private static class Sync extends AbstractQueuedSynchronizer {

        Sync(int shareCount) {
            setState(shareCount);
        }

        //new Condition可以分配给一个线程，用来实现等待和通知机制，唤醒某个特定的线程
        private Condition newCondition() {
            return new ConditionObject();
        }

        //获得一个锁
        @Override
        protected int tryAcquireShared(int arg) {
            //死循环直到获取成功或失败，造成循环的条件一般为CAS操作失败，即再重试一次
            for (; ; ) {
                int currentState = getState();
                //假如当前可用锁数量-1小于0时，表示当前已经没有可用的锁了，因此可以直接返回可用所数量-1
                //相反如果可用锁数量-1大于等于0，表示当前有可用的锁，因此CAS操作将可用锁数量设置为新的值
                if (currentState - 1 < 0 || compareAndSetState(currentState, currentState - 1)) {
                    return currentState - 1;
                }
            }
        }

        @Override
        protected boolean tryReleaseShared(int arg) {
            for (; ; ) {
                int currentState = getState();
                if (compareAndSetState(currentState, currentState + 1)) {
                    return true;
                }
            }
        }
    }

    private final Sync sync = new Sync(2);

    @Override
    public void lock() {
        sync.acquireShared(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireSharedInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return sync.tryAcquireShared(1) >= 0;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireSharedNanos(1, unit.toNanos(time));
    }

    @Override
    public void unlock() {
        sync.tryReleaseShared(1);
    }

    @Override
    public Condition newCondition() {
        return sync.newCondition();
    }
}
