package com.skaleto.things.leet;

public class Q53 {

    public int maxSubArray(int[] nums) {
        /**
         * 状态转移方程：
         * dp[i]=max(dp[i-1]+nums[i],nums[i])
         */
        int[] dp = new int[nums.length];
        dp[0] = nums[0];

        int max = dp[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            max = Math.max(max, dp[i]);
        }

        return max;

    }

    public int maxSubArray_20220110(int[] nums) {
        /**
         * dp[i]表示在前i个元素具有最大和的连续子数组的和
         *
         * 要么把前i-1个算进去，dp[i]=dp[i-1]+nums[i]
         * 要么不算前i-1个，只算当前
         *
         * 即: dp[i]=Math.max(dp[i-1]+nums[i],nums[i])
         *
         */

        int pre = nums[0];
        int max = pre;
        for (int i = 1; i < nums.length; i++) {
            pre = Math.max(pre + nums[i], nums[i]);
            max = Math.max(max, pre);
        }

        return max;

    }

    public int maxSubArray_20220110_1(int[] nums) {
        /**
         * 贪心算法
         * 从头到尾遍历，遇到sum<0的情况重新开始计算子串
         *
         */

        int max = Integer.MIN_VALUE;
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            max = Math.max(max, sum);
            if (sum < 0) {
                sum = 0;
            }
        }

        return max;

    }
}
