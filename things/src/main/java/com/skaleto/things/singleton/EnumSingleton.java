package com.skaleto.things.singleton;

public enum EnumSingleton {
    INSTANCE;
    /**
     * 这是目前最最被推荐的单例实现方式
     * 因为ENUM类型是不允许被反射创建对象的，因此解决了上面说到的序列化反序列化等由反射构造器引发的问题
     * @return
     */
    public static EnumSingleton getInstance() {
        return INSTANCE;
    }
}
