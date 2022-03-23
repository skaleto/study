package com.skaleto.things.leet;

import java.util.Arrays;

public class Q1552 {

    public static void main(String[] args) {
        Q1552 q = new Q1552();
        System.out.println(q.maxDistance_(new int[]{79, 74, 57, 22}, 4));
    }

    public int maxDistance_(int[] position, int m) {
        /**
         * 最小xx最大，考虑二分
         * 最小磁力=最小的位置间距
         * 下界为篮子的最小间距，上界为篮子的最大间距position[-1]-position[0]除以
         */
        int min = Integer.MAX_VALUE;
        Arrays.sort(position);

        for (int i = 1; i < position.length; i++) {
            min = Math.min(min, position[i] - position[i - 1]);
        }

        int max = position[position.length - 1] - position[0];

        int left = min;
        int right = max / (m - 1);
        int result = 0;
        while (left < right) {
            int mid = (left + right) / 2;
            int counts = countsWithPower(position, mid);
            //如果能装的球多，说明磁力间距太小了，要增大磁力间距
            if (counts >= m) {
                result = mid;
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;

    }

    public int maxDistance(int[] position, int m) {
        /**
         * 最小xx最大，考虑二分
         * 最小磁力=最小的位置间距
         * 下界为篮子的最小间距，上界为篮子的最大间距position[-1]-position[0]除以球的间距数量
         */
        int min = Integer.MAX_VALUE;
        Arrays.sort(position);

        for (int i = 1; i < position.length; i++) {
            min = Math.min(min, position[i] - position[i - 1]);
        }

        int max = position[position.length - 1] - position[0];

        int left = min;
        int right = max / (m - 1);
        int result = 0;
        while (left <= right) {
            int mid = (left + right) / 2;
            int counts = countsWithPower(position, mid);
            //如果能装的球多，说明磁力间距太小了，要增大磁力间距
            if (counts >= m) {
                result = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return result;

    }

    /**
     * 检查已minPower
     *
     * @param position
     * @param minPower
     * @return
     */
    public int countsWithPower(int[] position, int minPower) {
        int count = 1;
        int left = 0;
        for (int right = 1; right < position.length; right++) {
            int delta = position[right] - position[left];
            if (delta >= minPower) {
                //磁力差大于最小要求，可以放入一个球
                count++;
                left = right;
            }
        }

        return count;
    }
}
