package com.skaleto.things.leet;

public class Q1049 {
    public int lastStoneWeightII(int[] stones) {
        /**
         * 可以转化为背包问题，转化思路如下：
         * 1. 根据题意可以转化成将一堆石头分成两堆，两堆石头的差值最小
         * 2. 要让差值最小，那么两堆石头都要非常接近sum/2，一堆<=sum/2，一堆>=sum/2
         * 3. 转化成从石头中找到一堆石头重量<=sum/2的最大重量a
         *    最终的最小重量等于sum-2*a
         *    （为什么？设最小重量为k，另一堆重量为sum-a, 差值为(sum-a)-a即sum-2*a）
         *
         * 转化成01背包问题填到sum/2中的最大重量
         *
         */

        int sum = 0;
        for (int i = 0; i < stones.length; i++) {
            sum += stones[i];
        }

        int[][] dp = new int[stones.length + 1][sum / 2 + 1];
        //初始化条件：从N个石头中选择一些重量<=0的选法都是0；

        for (int i = 1; i <= stones.length; i++) {
            for (int j = 0; j <= sum / 2; j++) {
                if (j >= stones[i - 1]) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - stones[i - 1]] + stones[i - 1]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        int a = dp[stones.length][sum / 2];

        return sum - 2 * a;
    }


    public int lastStoneWeightII_space_opt(int[] stones) {
        int sum = 0;
        for (int i = 0; i < stones.length; i++) {
            sum += stones[i];
        }

        int[] dp = new int[sum / 2 + 1];
        //初始化条件：从N个石头中选择一些重量<=0的选法都是0；

        for (int i = 1; i <= stones.length; i++) {
            for (int j = sum / 2; j >= 0; j--) {
                if (j >= stones[i - 1]) {
                    dp[j] = Math.max(dp[j], dp[j - stones[i - 1]] + stones[i - 1]);
                }
            }
        }

        int a = dp[sum / 2];

        return sum - 2 * a;
    }
}
