package com.skaleto.things.threadpool;

import java.util.Arrays;
import java.util.List;

public class ScalableThreadPool {

    /**
     * “弹性”线程池的实现思路
     *
     * 由于线程池在工作队列满了无法入队的情况下会扩容线程池，那么我们是否可以重写队列的 offer 方法，造成这个队列已满的假象呢？
     * 由于我们 Hack 了队列，在达到了最大线程后势必会触发拒绝策略，那么能否实现一个自定义的拒绝策略处理程序，这个时候再把任务真正插入队列呢？
     *
     */

    public static void main(String[] args) throws NoSuchFieldException {
//        BigDecimal bigDecimal1 = new BigDecimal("100");
//        BigDecimal bigDecimal2 = new BigDecimal(String.valueOf(100d));
//        BigDecimal bigDecimal3 = BigDecimal.valueOf(100d);
//        BigDecimal bigDecimal4 = new BigDecimal(Double.toString(100d));
//
//        System.out.println(bigDecimal1.multiply(new BigDecimal("4.015")));
//        System.out.println(bigDecimal2.multiply(new BigDecimal("4.015")));
//        System.out.println(bigDecimal3.multiply(new BigDecimal("4.015")));
//        System.out.println(bigDecimal4.multiply(new BigDecimal("4.015")));

        String[] arr = {"1", "2", "3"};
        List list = Arrays.asList(arr);
        arr[1] = "4";
        try {
            list.add("5");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        System.out.println("end");
    }
}
