package com.skaleto.things.leet;

public class Q1011 {

    public static void main(String[] args) {
        Q1011 q = new Q1011();
        System.out.println(q.shipWithinDays(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 5));
    }

    /**
     * Your runtime beats 88.66 % of java submissions
     * Your memory usage beats 5.02 % of java submissions (45.3 MB)
     * @param weights
     * @param days
     * @return
     */
    public int shipWithinDays(int[] weights, int days) {
        /**
         * 运载能力最小是最大货物重量（否则肯定有货物运不了），最大是总和
         * 采用二分
         */
        int max = Integer.MIN_VALUE;
        int sum = 0;
        for (int i = 0; i < weights.length; i++) {
            max = Math.max(max, weights[i]);
            sum += weights[i];
        }

        int left = max;
        int right = sum;
        while (left < right) {
            int mid = (left + right) / 2;

            int needDay = needDayByPower(weights, mid);
            //如果消耗的天数多了，那么要增大载重量，从而减少天数
            if (needDay > days) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }

    public int needDayByPower(int[] weights, int power) {
        int day = 1;
        int carry = 0;
        for (int i = 0; i < weights.length; i++) {
            if (carry + weights[i] > power) {
                day++;
                carry = weights[i];
            } else {
                carry += weights[i];
            }
        }
        return day;
    }
}
