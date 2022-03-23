package com.skaleto.things.leet;

import java.util.Arrays;

public class Q279 {

    public static void main(String[] args) {
        Q279 q=new Q279();
        System.out.println(q.numSquares(12));
    }

    public int numSquares(int n) {
        /**
         * 完全背包问题
         * dp[i][j]表示从前i个数中选择一些数的和恰好等于j的数量
         *
         * 不选择第i个时
         *      dp[i][j]=dp[i-1][j]
         * 选择第i个时
         *      dp[i][j]=Math.min( dp[i-1][j-1*i^2]+1,
         *                         dp[i-1][j-2*i^2]+2,
         *                         dp[i-1][j-3*i^2]+3,
         *                         ...
         *                         dp[i-1][j-k*i^2]+k )
         */
        int dimension=(int) Math.round(Math.sqrt(n));
        int[][] dp=new int[dimension+1][n+1];
        Arrays.fill(dp[0], Integer.MAX_VALUE);

        dp[0][0]=0;

        for(int i=1;i<=dimension;i++){
            int value=i*i;
            for(int j=0;j<=n;j++){
                // 对于不选第 i 个数的情况
                dp[i][j] = dp[i - 1][j];
                // 对于选 k 次第 i 个数的情况
                for (int k = 1; k * value <= j; k++) {
                    // 能够选择 k 个 x 的前提是剩余的数字 j - k * x 也能被凑出
                    if (dp[i - 1][j - k * value] != Integer.MAX_VALUE) {
                        dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - k * value] + k);
                    }
                }
            }
        }

        return dp[dimension][n];

    }

    public int numSquares_space_opt(int n) {
        /**
         * 完全背包问题
         * dp[i][j]表示从前i个数中选择一些数的和恰好等于j的数量
         *
         * 不选择第i个时
         *      dp[i][j]=dp[i-1][j]
         * 选择第i个时
         *      dp[i][j]=dp[i-1][j-i^2]+1
         *
         * dp[i][j]=Math.min(dp[i-1][j], dp[i-1][j-i^2]+1)
         */

        int[] dp=new int[n+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        //从前0个数中选择一些数的和恰好等于j的数量为0
        dp[0]=0;

        for(int i=1;i*i<=n;i++){
            int min=i*i;
            for(int j=min;j<=n;j++){
                dp[j]=Math.min(dp[j], dp[j-min]+1);
            }
        }

        return dp[n];
    }
}
