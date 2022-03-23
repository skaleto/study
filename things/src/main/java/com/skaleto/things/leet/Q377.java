package com.skaleto.things.leet;

public class Q377 {

    public int combinationSum4(int[] nums, int target) {

        /**
         * ①dfs回溯
         * ②完全背包问题
         *
         * dp[i][j]表示组合长度为i，凑成总和为j的方案数是多少
         *
         * 等于最后一位为每个数字各自选一遍的个数和
         * dp[i][j]=Math.sum(
         *                  dp[i-1][j-nums[1]],
         *                  dp[i-1][j-nums[2]],
         *                  ...
         *                  dp[i-1][j-nums[i]]
         *                  )
         */

        int[][] dp=new int[target+1][target+1];
        dp[0][0]=1;

        int result=0;
        for(int i=1;i<=target;i++){
            for(int j=0;j<=target;j++){
                for(Integer num:nums){
                    if(j>=num){
                        dp[i][j]+=dp[i-1][j-num];
                    }
                }
            }
            result+=dp[i][target];
        }

        return result;
    }


    public int combinationSum4_space_opt(int[] nums, int target) {
        /**
         *  f[i] 为凑成总和为 i 的方案数是多少
         */
        int[] dp=new int[target+1];
        dp[0]=1;

        for(int j=0;j<=target;j++){
            for(Integer num:nums){
                if(j>=num){
                    dp[j]+=dp[j-num];
                }
            }
        }

        return dp[target];
    }
}
