package com.skaleto.things.leet;

public class Q518 {

    public static void main(String[] args) {
        Q518 q = new Q518();
        System.out.println(q.change(5, new int[]{1, 2, 5}));
    }

    public int change(int amount, int[] coins) {
        /**
         * 完全背包问题
         *
         * dp[i][j]表示前i种硬币凑满j金额的组合数
         *
         * 不选择第i个时
         *      dp[i][j]=dp[i-1][j]
         * 选择第i个时，
         *      dp[i][j]=dp[i][j-coins[i]]
         *
         */
        int[][] dp = new int[coins.length + 1][amount + 1];
        dp[0][0] = 1;
        //  for(int i=1;i<=amount;i++){
        //      dp[0][i]=0;
        //  }
        for (int i = 1; i <= coins.length; i++) {
            for (int j = 0; j <= amount; j++) {
                dp[i][j] = dp[i - 1][j];
                //注意这里的j>=coins[i-1]的条件不能放在j循环的条件里，否则上面一步dp[i - 1][j]的值会被遗漏
                if (j >= coins[i - 1]) {
                    dp[i][j] += dp[i][j - coins[i - 1]];
                }
            }
        }

        return dp[coins.length][amount];
    }

    public int change_space_opt(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;

        for (int i = 1; i <= coins.length; i++) {
            for (int j = coins[i - 1]; j <= amount; j++) {
                dp[i] += dp[j - coins[i - 1]];
            }
        }

        return dp[amount];
    }
}
