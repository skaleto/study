package com.skaleto.things.leet;

public class Q152 {

    public static void main(String[] args) {
        Q152 q = new Q152();
//        System.out.println(q.maxProduct(new int[]{2,3,-2,4}));
        System.out.println(q.maxProduct_20220110_space_opt(new int[]{-4, -3, -2}));
    }

    public int maxProduct(int[] nums) {
        /**
         * 状态转移方程：
         * dp[i]=max(dp[i-1]*nums[i],nums[i])
         */
        int result = Integer.MIN_VALUE;

        int max = 1;
        int min = 1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 0) {
                int temp = max;
                max = min;
                min = temp;
            }
            // dp[i]=Math.max(dp[i-1]*nums[i],nums[i]);
            max = Math.max(max * nums[i], nums[i]);
            min = Math.min(min * nums[i], nums[i]);

            result = Math.max(result, max);
        }

        return result;
    }

    public int maxProduct_20220110(int[] nums) {
        /**
         * 因为是乘积，可能存在负数乘以负数变成一个正值的情况，所以要同时记录最大值和最小值
         *
         *      maxdp[i]=Math.max(maxdp[i-1]*nums[i],nums[i],mindp[i-1]*nums[i])
         *      mindp[i]=Math.min(mindp[i-1]*nums[i],nums[i].maxdp[i-1]*nums[i])
         *
         */

        int[] maxdp = new int[nums.length];
        int[] mindp = new int[nums.length];

        maxdp[0] = nums[0];
        mindp[0] = nums[0];

        int result = nums[0];

        for (int i = 1; i < nums.length; i++) {
            maxdp[i] = Math.max(Math.max(maxdp[i - 1] * nums[i], nums[i]), mindp[i - 1] * nums[i]);
            mindp[i] = Math.min(Math.min(mindp[i - 1] * nums[i], nums[i]), maxdp[i - 1] * nums[i]);
            result = Math.max(result, maxdp[i]);
        }

        return result;
    }

    public int maxProduct_20220110_space_opt(int[] nums) {
        int maxdp = nums[0];
        int mindp = nums[0];

        int result = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int max = maxdp;
            maxdp = Math.max(Math.max(maxdp * nums[i], nums[i]), mindp * nums[i]);
            mindp = Math.min(Math.min(mindp * nums[i], nums[i]), max * nums[i]);
            result = Math.max(result, maxdp);
        }

        return result;
    }
}
