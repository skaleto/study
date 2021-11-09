package com.skaleto.things.designpattern.proxy;

import org.springframework.cglib.core.DebuggingClassWriter;
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

    /**
     * @param o           cglib生成的代理对象
     * @param method      被代理的方法
     * @param objects     方法入参
     * @param methodProxy 代理方法
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {

//        Object value = method.invoke(obj, objects);
        Object value = methodProxy.invokeSuper(o, objects);

        return value;
    }

    public static void main(String[] args) {
        //代理类class文件存入本地磁盘
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "E:\\iflytektest");
        GamePlayer playerProxy = (GamePlayer) new CglibProxy(new GamePlayer()).getInstance();
        playerProxy.login("", "");

    }
}
