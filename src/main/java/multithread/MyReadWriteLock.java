package multithread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author : ybyao
 * @Create : 2019-11-07 17:36
 */
public class MyReadWriteLock implements ReadWriteLock {
    /**
     * Returns the lock used for reading.
     *
     * @return the lock used for reading
     */
    @Override
    public Lock readLock() {
        return null;
    }

    /**
     * Returns the lock used for writing.
     *
     * @return the lock used for writing
     */
    @Override
    public Lock writeLock() {
        return null;
    }
}
