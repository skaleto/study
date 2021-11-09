package com.skaleto.things.singleton;

public class Hungry {

    private static Hungry hungry=new Hungry();

    /**
     * 这种情况下的单例模式在类加载的时候就已经创建好了对象
     * 也就是说这种方式的单例模式其实是线程安全的
     * @return
     */
    public static Hungry getInstance(){
        return hungry;
    }
}
