package com.skaleto.things.sundry;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestReflect {

    public static void test() {

    }

    public static MethodHandles.Lookup getLookup() {
        return MethodHandles.lookup();
    }

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        MethodHandles.Lookup lookup = TestReflect.getLookup();
        Method method = TestReflect.class.getMethod("test");
        MethodHandle handle = lookup.unreflect(method);


        lookup.findStatic(TestReflect.class, "test", MethodType.methodType(void.class));

        MethodHandles.lookup();

        System.gc();
    }

    public enum Type{

        ABC,DEF
    }
}
