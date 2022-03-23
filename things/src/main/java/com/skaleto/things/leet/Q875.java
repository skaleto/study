package com.skaleto.things.leet;

public class Q875 {

    public static void main(String[] args) {
        Q875 q = new Q875();
        System.out.println(q.minEatingSpeed(new int[]{312884470}, 968709470));
    }

    public int minEatingSpeed(int[] piles, int h) {
        /**
         * 每小时最多吃一堆
         *
         */

        long max = 0;
        for (int i = 0; i < piles.length; i++) {
            max = Math.max(max, piles[i]);
        }

        //下界为一小时内吃掉少的一堆香蕉
        long left = 1;
        //上界为一小时内吃光所有堆香蕉
        long right = max;
        while (left < right) {
            long mid = (left + right) / 2;
            long time = minTime(piles, mid);
            if (time > h) {
                //吃完的时间多了，那么要加快速度
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return (int) left;
    }

    public long minTime(int[] piles, long speed) {
        int time = 0;
        for (int i = 0; i < piles.length; i++) {
            if (piles[i] % speed == 0) {
                time += piles[i] / speed;
            } else {
                time += piles[i] / speed + 1;
            }
        }
        return time;
    }
}
