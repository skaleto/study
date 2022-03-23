package com.skaleto.things.leet;

import java.util.*;

public class Q239 {



    public int[] maxSlidingWindow_20211220(int[] nums, int k) {

        /**
         * 构造一个最大堆
         *
         */

        int[] result = new int[nums.length - k + 1];
        Queue<int[]> queue = new PriorityQueue<>((o1, o2) -> {
            if (o1[1] == o2[1]) {
                return 0;
            }
            if (o1[1] < o2[1]) {
                return 1;
            }
            if (o1[1] > o2[1]) {
                return -1;
            }
            return 0;
        });

        for (int i = 0; i < nums.length; i++) {
            while (i < k) {
                queue.add(new int[]{i, nums[i]});
                i++;
            }

            if (i == k) {
                result[0] = queue.peek()[1];
            }

            if (i == nums.length) {
                break;
            }

            queue.add(new int[]{i, nums[i]});

            while (queue.peek()[0] <= i - k) {
                queue.poll();
            }

            result[i - k + 1] = queue.peek()[1];

        }


        return result;
    }

    public static int[] maxSlidingWindow(int[] nums, int k) {
        int[] result = new int[nums.length - k + 1];
        Queue<int[]> pQueue = new PriorityQueue<>((o1, o2) -> Integer.compare(o2[1], o1[1]));

        for (int i = 0; i < nums.length; i++) {
            while (i < k) {
                pQueue.offer(new int[]{i, nums[i]});
                i++;
            }

            if (i == k) {
                result[0] = pQueue.peek()[1];
            }

            if (i == nums.length) {
                break;
            }

            pQueue.offer(new int[]{i, nums[i]});

            //如果加入新元素之后，堆上的最大元素坐标已经不在，那么最大元素可以出队了
            assert pQueue.peek() != null;

            while (pQueue.peek()[0] <= i - k) {
                pQueue.poll();
            }

            assert pQueue.peek() != null;
            result[i - k + 1] = pQueue.peek()[1];

        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(maxSlidingWindow(new int[]{9, 10, 9, -7, -4, -8, 2, -6}, 5)));
    }
}
