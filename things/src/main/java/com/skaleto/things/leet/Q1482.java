package com.skaleto.things.leet;

public class Q1482 {

    public static void main(String[] args) {
        Q1482 q = new Q1482();
        System.out.println(q.minDays(new int[]{7, 7, 7, 7, 12, 7, 7}, 2, 3));
    }

    /**
     * Your runtime beats 54.45 % of java submissions
     * Your memory usage beats 5.59 % of java submissions (49.8 MB)
     * @param bloomDay
     * @param m
     * @param k
     * @return
     */
    public int minDays(int[] bloomDay, int m, int k) {
        /**
         * 二分法
         * 最少要等待的天数是最小值，最多是最大值
         *
         * 不能摘到m束花的情况只能是数量不符合
         */

        if (bloomDay.length < m * k) {
            return -1;
        }

        int min = 0;
        int max = 0;
        for (int i = 0; i < bloomDay.length; i++) {
            min = Math.min(min, bloomDay[i]);
            max = Math.max(max, bloomDay[i]);
        }

        int left = min;
        int right = max;
        while (left < right) {
            int mid = (left + right) / 2;

            int count = getBouquetCount(bloomDay, mid, k);
            //如果能摘到的花束少了，那么等的天数要多一点，反之姚少一点
            if (count < m) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;

    }

    public int getBouquetCount(int[] bloomDay, int day, int k) {
        int count = 0;

        int i = 0;
        int num = 0;
        while (i < bloomDay.length) {
            while (i < bloomDay.length && num < k) {
                if (bloomDay[i] <= day) {
                    //当前连续的花束可以组成一束花
                    num++;
                    i++;
                } else {
                    //当前连续的花束不能组成一束花，需要跳出
                    num = 0;
                    break;
                }
            }

            if (num == k) {
                count++;
                num = 0;
            } else {
                i++;
            }
        }

        return count;
    }
}
