package designpattern.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author : ybyao
 * @Create : 2019-08-01 20:47
 * JDK动态代理只能针对接口进行
 */
public class JDKProxyHandler implements InvocationHandler {

    Class c = null;
    Object obj = null;

    public JDKProxyHandler(Object obj) {
        this.obj = obj;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = method.invoke(this.obj, args);
        return result;
    }

    public static void main(String[] args) {
        //定义一个玩家
        IGamePlayer gamePlayer = new GamePlayer("李大海");
        //定义一个handler
        InvocationHandler handler = new JDKProxyHandler(gamePlayer);
        //获得类的ClassLoader
        ClassLoader cl = gamePlayer.getClass().getClassLoader();
        //动态产生一个代理者
        IGamePlayer proxy = (IGamePlayer) Proxy.newProxyInstance(cl, new Class[]{IGamePlayer.class}, handler);

        proxy.login("","");
        proxy.logout("");

    }

}
