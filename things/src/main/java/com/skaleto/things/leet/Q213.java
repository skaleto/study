package com.skaleto.things.leet;

import java.util.Arrays;

public class Q213 {

    public static void main(String[] args) {
        Q213 q = new Q213();
        System.out.println(q.rob(new int[]{2, 3, 2}));
    }

    public int rob(int[] nums) {
        /**
         * 1. 定义子问题
         * 2. 写出子问题的递推关系
         * 3. 确定 DP 数组的计算顺序
         * 4. 空间优化（可选）
         *
         *
         * 1. 偷窃k个房屋的最高金额 = max(在1~k-1的房子中偷取的最高金额, 在2~k的房子中偷取)
         * 2. 每个范围内偷取的最大值 f(k) = max(f(k-2)+nums[k-1],f(k-1))
         * 3. 分别计算1~k-1和2~k中的偷取方式，取最终的最大值
         *
         */

        //一个数字的特判
        if (nums.length == 1) {
            return nums[0];
        }

        //两个数字的特判
        if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }

        //动态规划数组
        int[] dp = new int[nums.length + 1];

        /**
         * 计算1~k-1中的偷取方式
         */
        //从前0个中偷，能偷到的是0
        dp[0] = 0;
        //从前1个中偷，能偷到的是第一个值
        dp[1] = nums[0];
        //遍历2~k-1，每个数值i能偷到的金额dp[i] = max( 前i-2能偷到的金额 + 当前数值i对应的金额nums[i-1], 前i-1能偷到的金额 )
        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i - 1], dp[i - 1]);
        }
        //1~k-1中最大的金额就是dp的倒数第二个，因为最后一个没计算到
        int max1 = dp[nums.length - 1];

        //复位
        Arrays.fill(dp, 0);

        /**
         * 计算2~k中的偷取方式
         */
        //从前0个中偷，能偷到的是0
        dp[0] = 0;
        //从前1个中偷，能偷到的还是0，因为nums[0]不能偷
        dp[1] = 0;
        //从前2个中偷，能偷到的是nums[1]
        dp[2] = nums[1];
        //遍历3~k，每个数值i能偷到的金额dp[i] = max( 前i-2能偷到的金额 + 当前数值i对应的金额nums[i-1], 前i-1能偷到的金额 )
        for (int i = 3; i <= nums.length; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i - 1], dp[i - 1]);
        }
        //2~k中最大的金额就是dp的最后一个
        int max2 = dp[nums.length];

        return Math.max(max1, max2);

    }


    /**
     * 空间优化版
     *
     * @param nums
     * @return
     */
    public int rob_1(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }

        if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }

        int prepre = 0;
        int pre = nums[0];
        for (int i = 2; i < nums.length; i++) {
            int cur = Math.max(prepre + nums[i - 1], pre);
            prepre = pre;
            pre = cur;
        }
        int max1 = pre;

        prepre = 0;
        pre = nums[1];
        for (int i = 3; i <= nums.length; i++) {
            int cur = Math.max(prepre + nums[i - 1], pre);
            prepre = pre;
            pre = cur;
        }
        int max2 = pre;

        return Math.max(max1, max2);

    }


    public int rob_20220111(int[] nums) {
        /**
         * dp[i]
         * 即：要么从1~i-1中偷，要么从2~i中偷
         *
         */

        if(nums.length==0){
            return 0;
        }

        if(nums.length==1){
            return nums[0];
        }

        int[] dp=new int[nums.length+1];
        dp[0]=0;
        dp[1]=nums[0];

        int max=0;
        for(int i=2;i<nums.length;i++){
            dp[i]=Math.max(dp[i-1],dp[i-2]+nums[i-1]);
        }

        max=dp[nums.length-1];

        Arrays.fill(dp, 0);
        dp[0]=0;
        dp[1]=0;
        dp[2]=nums[1];

        for(int i=3;i<=nums.length;i++){
            dp[i]=Math.max(dp[i-1],dp[i-2]+nums[i-1]);
        }

        max=Math.max(max, dp[nums.length]);

        return max;

    }


    public int rob_20220111_space_opt(int[] nums) {

        if(nums.length==0){
            return 0;
        }

        if(nums.length==1){
            return nums[0];
        }

        int a=0;
        int b=nums[0];
        int c=b;

        int max=0;
        for(int i=2;i<nums.length;i++){
            c=Math.max(b,a+nums[i-1]);
            a=b;
            b=c;
        }

        max=c;

        a=0;
        b=nums[1];
        c=b;

        for(int i=3;i<=nums.length;i++){
            c=Math.max(b,a+nums[i-1]);
            a=b;
            b=c;
        }

        max=Math.max(max, c);

        return max;

    }

}
