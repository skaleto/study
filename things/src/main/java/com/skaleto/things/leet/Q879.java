package com.skaleto.things.leet;

public class Q879 {

    public int profitableSchemes_1(int n, int minProfit, int[] group, int[] profit) {
        /**
         * dp[i][j][k]为前i种工作使用人数不超过j，产生至少k的利润的计划数
         *
         * 不选第i种工作时
         *      dp[i][j][k] = dp[i-1][j][k]
         * 选择第i种工作时
         *      dp[i][j][k] = dp[i-1][j-group[i]][k-profit[i]]  对吗？
         *      此时k-profit[i]可能为负值，那么负值理论上应该和为0时保持一致
         *      所以此时dp[i][j][k] = dp[i-1][j-group[i]][Math.max(0,k-profit[i])]
         * 最后相加
         *
         */
        int mod = (int) 1e9 + 7;
        int count = profit.length;

        int[][][] dp = new int[count + 1][n + 1][minProfit + 1];
        //从前0种工作使用人数不超过0，产生0的利润的计划数有一种，就是啥也不干
        dp[0][0][0] = 1;

        //从前0种工作使用人数为i，产生0的利润的计划数有一种，也是啥也不干
        for (int i = 0; i <= n; i++) {
            dp[0][i][0] = 1;
        }

        for (int i = 1; i <= count; i++) {
            for (int j = 0; j <= n; j++) {
                for (int k = 0; k <= minProfit; k++) {
                    dp[i][j][k] = dp[i - 1][j][k];
                    int g = group[i - 1];
                    if (j - g >= 0) {
                        int p = Math.max(0, k - profit[i - 1]);
                        dp[i][j][k] += dp[i - 1][j - g][p];
                        //加上之后可能数字会太大了，因此做一下模
                        dp[i][j][k] %= mod;
                    }
                }
            }
        }

        return dp[count][n][minProfit];
    }


    public int profitableSchemes_1_space_opt(int n, int minProfit, int[] group, int[] profit) {
        int mod = (int) 1e9 + 7;
        int count = profit.length;

        int[][] dp = new int[n + 1][minProfit + 1];
        //从前0种工作使用人数不超过0，产生0的利润的计划数有一种，就是啥也不干
        dp[0][0] = 1;

        //从前0种工作使用人数为i，产生0的利润的计划数有一种，也是啥也不干
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
        }

        for (int i = 1; i <= count; i++) {
            for (int j = n; j >= 0; j--) {
                for (int k = minProfit; k >= 0; k--) {
                    int g = group[i - 1];
                    if (j - g >= 0) {
                        int p = Math.max(0, k - profit[i - 1]);
                        dp[j][k] += dp[j - g][p];
                        //加上之后可能数字会太大了，因此做一下模
                        dp[j][k] %= mod;
                    }
                }
            }
        }

        return dp[n][minProfit];
    }


    public int profitableSchemes_2_space_opt(int n, int minProfit, int[] group, int[] profit) {
        /**
         * dp[i][j][k]的定义中j和k所代表的的内容可以反转，得到的结果是一样的
         */
        int mod = (int) 1e9 + 7;
        int count = profit.length;

        int[][] dp = new int[minProfit + 1][n + 1];
        //从前0种工作使用人数不超过0，产生0的利润的计划数有一种，就是啥也不干
        dp[0][0] = 1;

        //从前0种工作使用人数为i，产生0的利润的计划数有一种，也是啥也不干
        for (int i = 0; i <= n; i++) {
            dp[0][i] = 1;
        }

        for (int i = 1; i <= count; i++) {
            for (int j = minProfit; j >= 0; j--) {
                for (int k = n; k >= 0; k--) {
                    int g = group[i - 1];
                    if (k - g >= 0) {
                        int p = Math.max(0, j - profit[i - 1]);
                        dp[j][k] += dp[p][k - g];
                        //加上之后可能数字会太大了，因此做一下模
                        dp[j][k] %= mod;
                    }
                }
            }
        }

        return dp[minProfit][n];
    }
}
