package com.skaleto.things.interview;

/**
 * 启动三个线程
 * 第一个线程打印1,2,3,4,5
 * 第二个线程打印6,7,8,9,10
 * 第三个线程打印11,12,13,14,15
 * 以此类推
 */
public class NumberMulti {

    private static volatile int cur=1;
    private static Object lock1 = new Object();
    private static Object lock2 = new Object();
    private static Object lock3 = new Object();

    public static void main(String[] args) {
        new Thread(() -> {
            while (cur <= 70) {
                synchronized (lock1) {
                    try {
                        lock1.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if(cur>70){
                    break;
                }
                for (int i = 0; i < 5; i++) {
                    System.out.println("线程1: " + cur);
                    cur++;
                }
                synchronized (lock2) {
                    lock2.notify();
                }
            }
        }).start();

        new Thread(() -> {
            while (cur <= 70) {
                synchronized (lock2) {
                    try {
                        lock2.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                for (int i = 0; i < 5; i++) {
                    System.out.println("线程2: " + cur);
                    cur++;
                }
                synchronized (lock3) {
                    lock3.notify();
                }
            }
        }).start();

        new Thread(() -> {
            while (cur <= 70) {
                synchronized (lock3) {
                    try {
                        lock3.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                for (int i = 0; i < 5; i++) {
                    System.out.println("线程3: " + cur);
                    cur++;
                }
                synchronized (lock1) {
                    lock1.notify();
                }
            }
        }).start();

        synchronized (lock1){
            lock1.notify();
        }
    }
}
