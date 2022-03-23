package com.skaleto.things.leet;

public class Q45 {

    public int jump(int[] nums) {
        int step = 0;
        int maxIndex = 0;
        int border = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            //记录从该点开始能到达的最大边界位置
            maxIndex = Math.max(maxIndex, nums[i] + i);
            //如果到达了最大边界，则算作一次跳跃，并且边界移到最大位置
            if (i == border) {
                border = maxIndex;
                step++;
            }
        }
        return step;
    }

    public int jump_20211229(int[] nums) {

        /**
         * dp[i]为i位置上的最少跳跃次数
         * dp[i]怎么得来？需要往前找到一个能跳到当前位置的最近的位置j
         * 随后，dp[i]=dp[j]+1
         */

        //定义dp数组
        int[] dp = new int[nums.length];
        //第一个位置不用跳，所以为0
        dp[0] = 0;

        //向前寻找的第二指针j
        int j = 0;
        for (int i = 1; i < nums.length; i++) {
            //向前寻找一个能跳到当前位置i的最近的位置j
            //j+nums[j]表示在j位置能跳到的最远距离
            while (j + nums[j] < i) {
                j++;
            }

            //此时的j跳一跳能跳到i，且路径最短
            dp[i] = dp[j] + 1;
        }

        return dp[nums.length - 1];
    }
}
