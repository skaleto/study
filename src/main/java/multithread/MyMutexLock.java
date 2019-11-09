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
public class MyMutexLock implements Lock {

    private static class Sync extends AbstractQueuedSynchronizer {
        private static final int UNLOCKED = 0;
        private static final int LOCKED = 1;

        //检查当前在排他模式下，锁是否被占用
        @Override
        protected boolean isHeldExclusively() {
            return getState() == LOCKED;
        }

        @Override
        protected boolean tryAcquire(int arg) {
            //CAS检查当前状态是否是0，0表示锁没有被占用，于是将它设置为1占用状态
            if (compareAndSetState(UNLOCKED, LOCKED)) {
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        @Override
        protected boolean tryRelease(int arg) {
            if (getState() == UNLOCKED) {
                throw new IllegalMonitorStateException();
            }
            setState(UNLOCKED);
            return true;
        }
        //new Condition可以分配给一个线程，用来实现等待和通知机制，唤醒某个特定的线程
        private Condition newCondition() {
            return new ConditionObject();
        }

    }

    private final Sync sync = new Sync();

    @Override
    public void lock() {
        //同步器的acquire方法为final，实际上调用了我们重写的tryAcquire
        sync.acquire(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        //同步器的acquireInterruptibly方法为final，实际上调用了我们重写的tryAcquire
        sync.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return sync.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        //同步器的tryAcquireNanos方法为final，实际上调用了我们重写的tryAcquire
        return sync.tryAcquireNanos(1, unit.toNanos(time));
    }

    @Override
    public void unlock() {
        sync.tryRelease(1);
    }

    @Override
    public Condition newCondition() {
        return sync.newCondition();
    }
}
