package com.skaleto.things.singleton;

public class StaticBlock {
    private static StaticBlock staticBlock;

    /**
     * 静态代码块的方式和其他静态方式创建的单例也没有太大区别
     */
    static {
        staticBlock = new StaticBlock();
    }

    public static StaticBlock getInstance() {
        return staticBlock;
    }
}
