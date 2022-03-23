package com.skaleto.things.leet;

public class Q198 {

    public static void main(String[] args) {
        Q198 q=new Q198();
        System.out.println(q.rob(new int[]{1,2,3,1}));
    }

    public int rob(int[] nums) {
        /**
         * 1. 定义子问题
         * 2. 写出子问题的递推关系
         * 3. 确定 DP 数组的计算顺序
         * 4. 空间优化（可选）
         *
         * 1. 从k个房间里面能偷到的最大金额result
         * 2. 一个房间：result=    nums[0]
         *    两个房间：result=max(nums[0], nums[1])
         *    三个房间：result=max(nums[0]+nums[2], nums[1])
         *    四个房间：result=max(nums[0]+nums[2], nums[0]+nums[3], nums[1]+nums[3])
         *    五个房间：result=max(nums[0]+nums[2]+nums[4], nums[0]+nums[3], nums[1]+nums[3], nums[1]+nums[4])
         *
         *    从k个房间里偷有两种方式，要么从k-1个房间里偷，不偷第k个；要么从k-2个房间里偷，加上第k个
         *    f(k)=Math.max(f(k-1),f(k-2)+1)
         *
         * 3. dp[k]=Math.max(f(k-1),f(k-2)+1)
         */

        int[] dp=new int[nums.length+1];
        dp[0]=0;
        dp[1]=nums[0];

        for(int i=2;i<=nums.length;i++){
            dp[i]=Math.max(dp[i-1],dp[i-2]+1);
        }

        return dp[nums.length];

    }

    public int rob_20220111(int[] nums) {
        /**
         * dp[i]
         * 不偷第i家，dp[i]=dp[i-1]
         * 偷第i家，dp[i]=dp[i-2]+nums[i]
         */

        int[] dp=new int[nums.length+1];
        dp[0]=0;
        dp[1]=nums[0];

        for(int i=2;i<=nums.length;i++){
            dp[i]=Math.max(dp[i-1],dp[i-2]+nums[i-1]);
        }

        return dp[nums.length];

    }
}
