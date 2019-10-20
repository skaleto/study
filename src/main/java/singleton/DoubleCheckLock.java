package singleton;

public class DoubleCheckLock {

    /**
     * 这里的volatile是必要的，如果没有，可能存在多个线程看到的当前对象的状态不同。
     */
    private volatile static DoubleCheckLock doubleCheckLock;

    public static DoubleCheckLock getInstance() {
        /**
         * 这里为什么要先检查一次呢？因为如果不检查，那么即使当前的对象已经初始化完成，他也会进入同步块，这是不必要的
         */
        if (doubleCheckLock == null) {
            synchronized (DoubleCheckLock.class) {
                /**
                 * 这里为什么要再检查一次呢？因为可能当前线程拿到的同步锁是其他线程用完之后的，也就是说其他线程已经完成了对象初始化
                 */
                if (doubleCheckLock == null) {
                    doubleCheckLock = new DoubleCheckLock();
                }
            }
        }
        return doubleCheckLock;
    }
}
