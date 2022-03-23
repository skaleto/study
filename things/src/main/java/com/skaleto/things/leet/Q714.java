package com.skaleto.things.leet;

public class Q714 {

    public int maxProfit(int[] prices, int fee) {
        // 令某一天为i，是否持有股票为1或0，已进行的交易次数为k
        // 用dp[i][0][k]来表示股票的递推公式
        // 【假定买入股票为一次交易】
        // 对于每一天来说
        // 假如今天持有股票
        // 今天的最大收益要么等于昨天没持有，今天买入了股票的收益；要么等于昨天也持有，今天继续持有的收益；
        // dp[i][1][k] = max( dp[i-1][0][k-1]-prices[i] , dp[i-1][1][k] )
        // 假如今天不持有股票
        // 今天的最大收益要么等于昨天没持有，今天不操作的收益；要么等于昨天持有，今天卖出股票的收益；
        // dp[i][0][k] = max( dp[i-1][0][k] , dp[i-1][1][k]+prices[i] )

        int[][] dp = new int[prices.length][2];

        dp[0][1] = -prices[0] - fee;
        dp[0][0] = 0;
        for (int i = 1; i < prices.length; i++) {
            dp[i][1] = Math.max(dp[i - 1][0] - prices[i] - fee, dp[i - 1][1]);
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
        }

        return dp[prices.length - 1][0];

    }

    /**
     * Your runtime beats 40.45 % of java submissions
     * Your memory usage beats 8.71 % of java submissions (48 MB)
     *
     * @param prices
     * @param fee
     * @return
     */
    public int maxProfit_20220113(int[] prices, int fee) {
        /**
         * dp[i][j][k]表示第i天是否持有股票的收益，j=0表示不持有，1表示持有，已经进行了k次交易
         *  （卖出算一次交易）
         *
         * dp[i][0][k]表示第i天不持有，且进行过k次交易的收益
         *    =Math.max(dp[i-1][0][k], dp[i-1][1][k-1]+prices[i]-fee)
         *    等于前一天不持有，进行过k次交易，今天不操作
         *    或者前一天持有，进行过k-1次交易，今天卖出，交易次数+1
         *
         * dp[i][1][k]表示第i天持有，且进行过k次交易的收益
         *    =Math.max(dp[i-1][0][k]-prices[i], dp[i-1][1][k])
         *    等于前一天不持有，进行过k次交易，今天买入
         *    或者前一天持有，进行过k次交易，今天不操作
         *
         */
        int[][] dp = new int[prices.length][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];

        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i] - fee);
            dp[i][1] = Math.max(dp[i - 1][0] - prices[i], dp[i - 1][1]);
        }

        return dp[prices.length - 1][0];


    }

    /**
     * Your runtime beats 95.02 % of java submissions
     * Your memory usage beats 52.7 % of java submissions (47.6 MB)
     */
    public int maxProfit_20220113_space_opt(int[] prices, int fee) {
        int nHold = 0;
        int hold = -prices[0];

        for (int i = 1; i < prices.length; i++) {
            nHold = Math.max(nHold, hold + prices[i] - fee);
            hold = Math.max(nHold - prices[i], hold);
        }

        return nHold;

    }

}
