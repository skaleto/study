package com.skaleto.things.collection;

import java.util.Vector;

public class VectorTest {

    private static Vector testVector = new Vector();

    public static void main(String[] arg) {
        while (true) {
            for (int i = 0; i < 10; i++) {
                testVector.add(i);
                System.out.println("add" + i);
            }
            Thread remove = new Thread() {
                @Override
                public void run() {
                    try {

                        for (int i = 0; i < testVector.size(); i++) {
                            testVector.remove(i);
                            System.out.println("remove" + i);
                            Thread.sleep(1000);
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };

            Thread print = new Thread() {
                @Override
                public void run() {
                    try {

                        for (int i = 0; i < testVector.size(); i++) {
                            System.out.println(testVector.get(i));
                            Thread.sleep(1000);
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };

            print.start();
            remove.start();
            while (Thread.activeCount() > 10) ;
        }

    }
}
