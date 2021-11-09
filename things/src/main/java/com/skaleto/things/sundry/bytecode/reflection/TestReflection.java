package com.skaleto.things.sundry.bytecode.reflection;

import java.lang.reflect.Method;

public class TestReflection {

    public void foo(String a){
        System.out.println(a);
    }

    public static void main(String[] args) throws Exception {
        Class<?> clz = Class.forName("com.skaleto.things.sundry.bytecode.reflection.TestReflection");
        Object o = clz.newInstance();
        Method m = clz.getMethod("foo", String.class);
        for (int i = 0; i < 16; i++) {
            m.invoke(o, Integer.toString(i));
        }

    }
}
