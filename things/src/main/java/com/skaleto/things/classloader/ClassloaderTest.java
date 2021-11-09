package com.skaleto.things.classloader;

/**
 * @author : ybyao
 * @Create : 2019-08-15 20:09
 */
public class ClassloaderTest {

    private static int a = 1234;

    public static void main(String[] args) {
        ClassloaderTest test=new ClassloaderTest();

        System.out.println(ClassLoader.getSystemClassLoader());
        System.out.println(ClassLoader.getSystemClassLoader().getParent());
        System.out.println(ClassLoader.getSystemClassLoader().getParent().getParent());

//        Class<?> clazz = Reflection.getCallerClass();
//        System.out.println("Hello " + clazz);
    }
}
