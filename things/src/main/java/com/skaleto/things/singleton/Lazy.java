package com.skaleto.things.singleton;

public class Lazy {

    private static Lazy lazy;

    /**
     * 这里的getInstance明显不是线程安全的
     * @return
     */
    public static Lazy getInstance() {
        if (lazy == null) {
            lazy = new Lazy();
        }
        return lazy;
    }
}
