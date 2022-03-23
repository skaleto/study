package com.skaleto.things.leet;

public class Q188 {

    public static void main(String[] args) {
        Q188 q = new Q188();
        System.out.println(q.maxProfit(4, new int[]{5, 7, 2, 7, 3, 3, 5, 3, 0}));
    }


    public int maxProfit(int k, int[] prices) {
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
         * 今天的最大收益要么等于昨天没持有，今天买入了股票的收益；要么等于昨天也持有，今天继续持有的收益；
         * dp[i][1][k] = max( dp[i-1][0][k] - prices[i], dp[i-1][1][k] )
         *
         * 最后一天的收益是dp[n-1][0][k]的最大值;
         */

        if (prices.length == 0) {
            return 0;
        }
        int[][][] dp = new int[prices.length][2][k + 1];

        for (int i = 0; i <= k; i++) {
            dp[0][0][i] = 0;
            dp[0][1][i] = -prices[0];
        }

        for (int i = 1; i < prices.length; i++) {
            //因为下面的循环j是从1开始的，所以要在这里对j=0的情况特殊处理
            dp[i][0][0] = 0;
            //这里初始化的[i][1][0]实际上是给i的下一层循环中j=1时候用的
            dp[i][1][0] = Math.max(dp[i - 1][0][0] - prices[i], dp[i - 1][1][0]);
            for (int j = 1; j <= k; j++) {
                dp[i][0][j] = Math.max(dp[i - 1][1][j - 1] + prices[i], dp[i - 1][0][j]);
                dp[i][1][j] = Math.max(dp[i - 1][0][j] - prices[i], dp[i - 1][1][j]);
            }
        }

        return dp[prices.length - 1][0][k];

    }

    /**
     * Your runtime beats 85.56 % of java submissions
     * Your memory usage beats 49.17 % of java submissions (37.9 MB)
     *
     * @param k
     * @param prices
     * @return
     */
    public int maxProfit_20220113(int k, int[] prices) {
        /**
         * dp[i][j][k]表示第i天是否持有股票的收益，j=0表示不持有，1表示持有，已经进行了k次交易
         *  （卖出算一次交易）
         *
         * dp[i][0][k]表示第i天不持有，且进行过k次交易的收益
         *    =Math.max(dp[i-1][0][k], dp[i-1][1][k-1]+prices[i])
         *    等于前一天不持有，进行过k次交易，今天不操作
         *    或者前一天持有，进行过k-1次交易，今天卖出，交易次数+1
         *
         * dp[i][1][k]表示第i天持有，且进行过k次交易的收益
         *    =Math.max(dp[i-1][0][k]-prices[i], dp[i-1][1][k])
         *    等于前一天不持有，进行过k次交易，今天买入
         *    或者前一天持有，进行过k次交易，今天不操作
         *
         */

        if (prices.length <= 1) {
            return 0;
        }

        int[][][] dp = new int[prices.length][2][k + 1];
        //在第1天不持有，交易0次的全部为0，即dp[i][0][0]=0
        dp[0][0][0] = 0;
        //在第1天持有，交易0次的为-prices[0]
        dp[0][1][0] = -prices[0];
        //在第1天交易>0次的不存在
        /**
         * 这里的初始化条件，假如非0的交易次数初始化和0相同的话，k这一位代表的是最多k次交易得到的收益
         * 假如初始化为最小值，k这一位代表的是恰好k次交易得到的收益
         */
        for (int i = 1; i <= k; i++) {
            dp[0][0][i] = Integer.MIN_VALUE / 2;
            dp[0][1][i] = Integer.MIN_VALUE / 2;
        }

        int max = 0;
        for (int i = 1; i < prices.length; i++) {
            for (int m = 0; m <= k; m++) {
                if (m > 0) {
                    dp[i][0][m] = Math.max(dp[i - 1][0][m], dp[i - 1][1][m - 1] + prices[i]);
                } else {
                    dp[i][0][m] = dp[i - 1][0][m];
                }

                dp[i][1][m] = Math.max(dp[i - 1][0][m] - prices[i], dp[i - 1][1][m]);
                max = Math.max(max, dp[i][0][m]);
            }
        }

        return max;

    }

    /**
     * Your runtime beats 99.95 % of java submissions
     * Your memory usage beats 77.59 % of java submissions (36.1 MB)
     *
     * @param k
     * @param prices
     * @return
     */
    public int maxProfit_20220113_space_opt(int k, int[] prices) {
        if (prices.length <= 1) {
            return 0;
        }

        int[][] dp = new int[2][k + 1];
        //在第1天不持有，交易0次的全部为0，即dp[i][0][0]=0
        dp[0][0] = 0;
        //在第1天持有，交易0次的为-prices[0]
        dp[1][0] = -prices[0];
        //在第1天交易>0次的不存在
        for (int i = 1; i <= k; i++) {
            dp[0][i] = Integer.MIN_VALUE / 2;
            dp[1][i] = Integer.MIN_VALUE / 2;
        }

        int max = 0;
        for (int i = 1; i < prices.length; i++) {
            for (int m = 0; m <= k; m++) {
                if (m > 0) {
                    dp[0][m] = Math.max(dp[0][m], dp[1][m - 1] + prices[i]);
                }

                dp[1][m] = Math.max(dp[0][m] - prices[i], dp[1][m]);
                max = Math.max(max, dp[0][m]);
            }
        }

        return max;
    }


}
