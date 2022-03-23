package com.skaleto.things.leet;

public class Q55 {

    public boolean canJump(int[] nums) {
        int n=nums.length-1;
        int cur=n;
        for(int i=n-1;i>=0;i--){
            if(nums[i]+i>=cur){
                cur=i;
            }
        }
        return cur==0;
    }


    public boolean canJump_20211229(int[] nums) {

        /**
         * dp[i]表示每个位置能够到达的最远距离
         *
         */

        int[] dp=new int[nums.length];
        dp[0]=nums[0];

        for(int i=1;i<nums.length;i++){
            if(nums[i]!=0){
                dp[i]=Math.max(dp[i-1], i+nums[i]);
            }
        }

        return dp[nums.length-1]>0;
    }

    public static void main(String[] args) {
        Q55 q=new Q55();
        System.out.println(q.canJump_20211229(new int[]{3,2,1,0,4}));
    }
}
