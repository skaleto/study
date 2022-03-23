package com.skaleto.things.leet;

public class Q416 {

    public static void main(String[] args) {
        Q416 q=new Q416();
        System.out.println(q.canPartition(new int[]{1,5,11,5}));
    }

    public boolean canPartition(int[] nums) {
        /**
         * 01背包问题
         * 转换成是否能装到sum/2的即可
         * dp[i][j]=dp[i-1][j]+dp[i-1][j-nums[i]]+nums[i]
         */
        int sum=0;
        for(int i=0;i<nums.length;i++){
            sum+=nums[i];
        }

        //总和为奇数肯定不可能分割出两个等和子集
        if(sum%2!=0){
            return false;
        }

        boolean[] dp=new boolean[sum/2+1];
        dp[0]=true;

        for(int i=0;i<nums.length;i++){
            for(int j=sum/2;j>=nums[i];j--){
                dp[j]=dp[j] || dp[j-nums[i]];
            }
        }

        return dp[sum/2];

    }
}
