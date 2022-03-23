package com.skaleto.things.leet;

import java.util.Arrays;

public class Q410 {

    public int splitArray_20220120_dp(int[] nums, int m) {
        /**
         * dp[i][k]表示从0~i中，分割出k段后，在所有分法中（每段数字和的最大值）的最小值
         *
         * 那么dp[i][k]
         *      要么等于从0~j中分割出k-1段中，所有分法中的最小值
         *      要么等于j+1~i这一段的总和
         *
         * dp[i][k]=Math.min(dp[j][k-1], sum(j+1~i))
         */

        int[] sum = new int[nums.length + 1];
        sum[0] = 0;
        for (int i = 0; i < nums.length; i++) {
            sum[i + 1] = sum[i] + nums[i];
        }

        int[][] dp = new int[nums.length][m + 1];

        //初始条件
        for (int i = 0; i < nums.length; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }

        //分割数为 1 ，即不分割的情况，所有的前缀和就是依次的状态值
        for (int i = 0; i < nums.length; i++) {
            dp[i][1] = sum[i + 1];
        }

        // 从分割数为 2 开始递推
        for (int k = 2; k <= m; k++) {
            // 还未计算出的 i 是从 j 的最小值的下一位开始，因此是 k - 1
            for (int i = k - 1; i < nums.length; i++) {
                for (int j = k - 2; j <= i - 1; j++) {
                    dp[i][k] = Math.min(dp[i][k], Math.max(dp[j][k - 1], sum[i + 1] - sum[j + 1]));
                }
            }
        }

        return dp[nums.length - 1][m];

    }


    public int splitArray_20220120_binary_search(int[] nums, int m) {

        //二分下界
        int max = 0;
        //二分上界
        int sum = 0;

        for (int i = 0; i < nums.length; i++) {
            max = Math.max(max, nums[i]);
            sum += nums[i];
        }

        int left = max;
        int right = sum;
        while (left < right) {
            int mid = (left + right) / 2;

            int count = splitCount(nums, mid);
            if (count > m) {
                //分割数量太多了，说明我们的总和大小太小了，需要变大
                left = mid + 1;
            } else {
                right = mid;
            }

        }

        //此时left==right
        return left;
    }

    public int splitCount(int[] nums, int maxSplitSum) {
        int split = 1;
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (sum + nums[i] > maxSplitSum) {
                //如果结果即将超限，那么新增一个分割
                split++;
                //sum置为0，此时表示下一段分割的开始值
                sum = nums[i];
            } else {
                sum += nums[i];
            }
        }

        return split;
    }
}
