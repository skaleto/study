package com.skaleto.things.sundry.bytecode;

import java.util.stream.IntStream;

/**
 * @author yaoyibin
 */
public class JavaByteCodeSecret {

    private static final Object o = new Object();

    public static void main(String[] args) {
//        {
//            byte[] placeholder = new byte[64 * 1024 * 1024];
//        }
//        int a = 0;
////        System.gc();
//        new JavaByteCodeSecret().test();
//        Unsafe.getUnsafe().objectFieldOffset();
    }

    public int add(int i) {
        return ((i + 1) - 2) * 3 / 4;
    }

    public static synchronized String test() {
        try {
            new Cat().isExtinct();
            IntStream.rangeClosed(1, 3).forEach(i -> System.out.println(++i));

            return "1";
        } catch (Exception e) {
            e.printStackTrace();
            return "2";
        }

    }
}
