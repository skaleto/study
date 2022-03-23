package com.skaleto.things.leet;

public class RedPackage {

    public static void main(String[] args) {
        algorithm1(100,10,0.01);
    }

    public static void algorithm1(int sum, int count, double min) {
        for (int i = 0; i < count - 1; i++) {
            double price = (min + Math.random() * (sum/(count-i) * 2));
            System.out.println(price);
            sum-=price;
        }
        System.out.println(sum);
    }
}
