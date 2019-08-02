package designpattern.proxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author : ybyao
 * @Create : 2019-08-01 21:07
 * CGLIB动态代理可以对class进行，但对final的也无法操作
 */
public class CglibProxy implements MethodInterceptor {

    private Object obj = null;

    public CglibProxy(Object obj) {
        this.obj = obj;
    }

    public Object getInstance() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.obj.getClass());
        enhancer.setCallback(this);
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {

        Object value = method.invoke(obj, objects);
//        Object value=methodProxy.invokeSuper(obj,objects);

        return value;
    }

    public static void main(String[] args) {
        GamePlayer playerProxy = (GamePlayer) new CglibProxy(new GamePlayer("name")).getInstance();
        playerProxy.login("", "");

    }
}
