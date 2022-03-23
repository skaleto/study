package com.skaleto.things.leet;

public class Q50 {

    /**
     * 快速幂法
     *
     * @param x
     * @param n
     * @return
     */
    public double myPow(double x, int n) {
        if (n == 0) {
            return 1.0;
        }
        if ((long) n > 0) {
            return positive(x, n);
        } else {
            return 1 / positive(x, -(long) n);
        }
    }

    /**
     * n的比特位为正的进行计算
     *
     * @param x
     * @param n
     * @return
     */
    public double positive(double x, long n) {
        if (n == 1) {
            return x;
        }

        double res = 1;
        double tmp = x;
        while (n > 0) {
            //如果最低位是1(是奇数)，那么该位要算进去
            if ((n & 1) != 0) {
                res *= tmp;
            }
            //每右移一位，多计算一次幂
            tmp *= tmp;
            n = n >> 1;
        }

        return res;
    }


    /**
     * 递归法
     *
     * @param x
     * @param n
     * @return
     */
    public double myPow_1(double x, int n) {
        if (n == 0) {
            return 1.0;
        }
        long pow = n;
        if (pow > 0) {
            return positive_1(x, pow);
        } else {
            return 1 / positive_1(x, -pow);
        }
    }

    public double positive_1(double x, long n) {
        if (n == 1) {
            return x;
        }
        double res = positive_1(x, n / 2);
        if (n % 2 == 0) {
            return res * res;
        } else {
            return x * res * res;
        }
    }
}
