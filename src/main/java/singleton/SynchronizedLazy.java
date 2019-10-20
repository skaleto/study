package singleton;

public class SynchronizedLazy {

    private static SynchronizedLazy synchronizedLazy;

    public synchronized static SynchronizedLazy getInstance1() {
        if (synchronizedLazy == null) {
            synchronizedLazy = new SynchronizedLazy();
        }
        return synchronizedLazy;
    }

    public static SynchronizedLazy getInstance2() {
        synchronized (SynchronizedLazy.class) {
            if (synchronizedLazy == null) {
                synchronizedLazy = new SynchronizedLazy();
            }
            return synchronizedLazy;
        }
    }
}
