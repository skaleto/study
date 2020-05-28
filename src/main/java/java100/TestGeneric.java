package java100;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author yaoyibin
 */
public class TestGeneric {

    public static void main(String[] args) {

        Child1 child1 = new Child1();
        Method[] m = child1.getClass().getMethods();
        Arrays.stream(child1.getClass().getMethods())
                //这里的isBridge不能少，假如少了，那么会匹配到setValue因为泛型而生成的桥接方法加入到列表中
                .filter(method -> method.getName().equals("setValue") && !method.isBridge())
                .forEach(method -> {
                    try {
                        method.invoke(child1, "test");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
        System.out.println(child1.toString());
    }
}


class Parent<T> {
    //用于记录value更新的次数，模拟日志记录的逻辑
    AtomicInteger updateCount = new AtomicInteger();
    private T value;

    //重写toString，输出值和值更新次数
    @Override
    public String toString() {
        return String.format("value: %s updateCount: %d", value, updateCount.get());
    }

    //设置值
    public void setValue(T value) {
        this.value = value;
        updateCount.incrementAndGet();
        System.out.println("parent.setValue called");
    }
}


class Child1 extends Parent<String> {

    @Override
    //这个地方的setValue不会重写父类的方法，因为经过类型擦除后，父类的方法入参是Object
    public void setValue(String value) {
        System.out.println("Child1.setValue called");
        super.setValue(value);
    }
}
