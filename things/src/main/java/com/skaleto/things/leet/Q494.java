package com.skaleto.things.leet;

public class Q494 {

    public static void main(String[] args) {
        Q494 q = new Q494();
        System.out.println(q.findTargetSumWays(new int[]{1, 1, 1, 1, 1}, 3));
    }

    public int findTargetSumWays(int[] nums, int target) {
        /**
         * 这道题可以转换成
         *      nums数组里面取出一个子集P和一个子集N【由于集合都是非负数，sum(P)+sum(N)=sum(nums)】
         *      使得sum(P)-sum(N)=target => sum(P)-sum(N)+sum(P)+sum(N)=target+sum(P)+sum(N)
         *      ∵ sum(P)+sum(N)=sum(nums)
         *      ∴ 2*sum(P)=target+sum(nums)
         *      ∴ target+sum(nums)必须为偶数 且 sum(P)=(target+sum(nums))/2
         *      即变成从nums数组里面无放回地选出一些数，是他们的和恰好等于(target+sum(nums))/2
         *
         * 01背包问题
         * dp[i][j]表示前i个数字恰好求和等于j的数目
         * 不选择第i个时
         *      dp[i][j]=dp[i-1][j]
         * 选择第i个时
         *      dp[i][j]=dp[i-1][j-nums[i]]
         * 因此
         *      dp[i][j]=dp[i-1][j] + dp[i-1][j-nums[i]]
         *
         */
        if (nums.length == 1 && nums[0] != target && nums[0] != -target) {
            return 0;
        }

        int sum = 0;
        for (Integer num : nums) {
            sum += num;
        }
        //不为偶数可以直接退出
        if ((target + sum) % 2 == 1) {
            return 0;
        }

        int P = (target + sum) / 2;

        int[][] dp = new int[nums.length + 1][P + 1];

        //从0个数字中选择出结果为0的方案有一种，就是什么都不选
        dp[0][0] = 1;

        for (int i = 1; i <= nums.length; i++) {
            for (int j = 0; j <= P; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j >= nums[i - 1]) {
                    dp[i][j] += dp[i - 1][j - nums[i - 1]];
                }
            }
        }

        return dp[nums.length][P];
    }

    public int findTargetSumWays_1_space_opt(int[] nums, int target) {
        if(nums.length==1 && nums[0]!=target && nums[0]!=-target){
            return 0;
        }

        int sum=0;
        for(Integer num:nums){
            sum+=num;
        }
        //不为偶数可以直接退出
        if((target+sum)%2==1){
            return 0;
        }

        int P=(target+sum)/2;

        int[] dp=new int[P+1];

        //从0个数字中选择出结果为0的方案有一种，就是什么都不选
        dp[0]=1;

        for (int i = 1; i <= nums.length; i++) {
            for (int j = P; j >= nums[i-1] ; j--) {
                dp[j] += dp[j - nums[i-1]];
            }
        }

        return dp[P];
    }
}
