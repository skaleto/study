package singleton;

import java.io.Serializable;

public class StaticInnerClass implements Serializable {
    /**
     * 其实这种方式和直接静态成员变量的方式差不多
     */
    private static class InnerClass{
        private static StaticInnerClass staticInnerClass=new StaticInnerClass();
    }

    public static StaticInnerClass getInstance(){
        return InnerClass.staticInnerClass;
    }

    /**
     * 这里的readResolve不是单例模式本身需要的，而是实现了序列化接口后需要的
     * 在单例的情况下如果实现了序列化，那么在反序列化的过程中，JVM内部其实是反射调用类的构造器来创建一个对象，那么这个时候得到的对象其实和我们原来的对象不是同一个，违背了单例的原则
     * 但是呢，在反序列化的过程中，jvm也会去检查对象是否有readResolve方法，假如有，就会直接调用它来生成对象，而不通过反射创建
     * 于是在这里实现的readResolve方法就是为了在反序列化的时候仍然能够得到原本的对象
     * @return
     */
    public StaticInnerClass readResolve(){
        return InnerClass.staticInnerClass;
    }
}
