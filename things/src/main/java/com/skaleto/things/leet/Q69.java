package com.skaleto.things.leet;

public class Q69 {

    public int mySqrt(int x) {
        if (x == 0 || x == 1) {
            return x;
        }
        int left = 1;
        int right = x / 2;
        while (left < right) {
            int mid = (left + right) / 2 + 1;
            int temp = x / mid;
            if (mid > temp) {
                right = mid - 1;
            } else {
                left = mid;
            }
        }

        return left;
    }
}
