package com.skaleto.things.leet;

public class Q309 {

    public int maxProfit(int[] prices) {
        /**
         * 令某一天为i，是否持有股票为1或0，已进行的交易次数为k【卖出股票视为一次交易】
         * 用dp[i][0/1][k]来表示股票的递推公式
         * 对于每一天来说
         *
         * 假如今天不持有股票
         * 今天的最大收益要么等于昨天持有，今天卖出股票的收益；要么等于昨天没持有，今天不操作的收益；
         * dp[i][0][k] = max( dp[i-1][1][k-1] + prices[i], dp[i-1][0][k])
         *
         * 假如今天持有股票
         * 今天的最大收益要么等于前天没持有（昨天没有进行过交易），今天买入了股票的收益；要么等于昨天也持有，今天继续持有的收益；
         * dp[i][1][k] = max( dp[i-2][0][k] - prices[i], dp[i-1][1][k] )
         *
         * 最后一天的收益是dp[n-1][0][k]的最大值;
         */

        int[][] dp = new int[prices.length][2];
        //第0天当天持有股票，收益为花出去的钱
        dp[0][1] = -prices[0];

        for (int i = 1; i < prices.length; i++) {
            //如果没持有且没进行过交易，那么收益肯定为0
            dp[i][0] = 0;
            //今天不持有且进行过一次交易，最大收益要么等于昨天没持有，今天不操作的收益；要么等于昨天持有，今天卖出股票的收益；
            dp[i][0] = Math.max(dp[i - 1][1] + prices[i], dp[i - 1][0]);

            if (i > 1) {
                //今天持有且没进行过交易，收益要么等于前天没持有，今天买入了股票的收益；要么等于昨天也持有，今天继续持有的收益；
                dp[i][1] = Math.max(dp[i - 2][0] - prices[i], dp[i - 1][1]);
            } else {
                dp[i][1] = Math.max(-prices[i], dp[i - 1][1]);
            }

        }

        return Math.max(0, dp[prices.length - 1][0]);

    }


    public int maxProfit_20220113(int[] prices) {
        /**
         * dp[i][j]表示第i天是否持有股票的收益，j=0表示不持有，1表示持有
         * 买入的时候前一天不能有交易
         *
         * dp[i][0]=Math.max(dp[i-1][0],dp[i-1][1]+prices[i])
         *    表示第i天不持有的收益等于前一天不持有的收益（当天不进行操作）
         *                      或者前一天持有，今天卖出的收益
         *
         * 假如能买卖多次，那么
         * dp[i][1]=Math.max(dp[i-1][1],dp[i-2][0]-prices[i])
         *    表示第i天持有的收益等于前一天持有的收益（当天不进行操作）
         *                    或者前两天不持有（前一天不能有交易），今天进行买入的收益
         */
        if (prices.length <= 1) {
            return 0;
        }
        int[][] dp = new int[prices.length][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        dp[1][0] = Math.max(0, prices[1] - prices[0]);
        dp[1][1] = Math.max(dp[0][1], -prices[1]);

        for (int i = 2; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 2][0] - prices[i]);
        }

        return dp[prices.length - 1][0];
    }

    public int maxProfit_20220113_space_opt(int[] prices) {
        if (prices.length <= 1) {
            return 0;
        }
        int prenHold = 0;
        int nHold = Math.max(0, prices[1] - prices[0]);
        int hold = Math.max(-prices[0], -prices[1]);

        for (int i = 2; i < prices.length; i++) {
            int nHoldTmp = Math.max(prenHold, hold + prices[i]);
            int holdTmp = Math.max(hold, prenHold - prices[i]);
            prenHold = nHold;
            nHold = nHoldTmp;
            hold = holdTmp;
        }

        return nHold;
    }


}
