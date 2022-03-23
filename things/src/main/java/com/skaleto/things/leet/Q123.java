package com.skaleto.things.leet;

public class Q123 {

    public static void main(String[] args) {
        Q123 q = new Q123();
        System.out.println(q.maxProfit(new int[]{3, 3, 5, 0, 0, 3, 1, 4}));
    }

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
         * 今天的最大收益要么等于昨天没持有，今天买入了股票的收益；要么等于昨天也持有，今天继续持有的收益；
         * dp[i][1][k] = max( dp[i-1][0][k] - prices[i], dp[i-1][1][k] )
         *
         * 最后一天的收益是dp[n-1][0][k]的最大值;
         */

        int[][][] dp = new int[prices.length][2][3];
        //第0天当天持有股票，完成交易0次，收益为花出去的钱
        dp[0][1][0] = -prices[0];
        //第0天完成交易的都不可能存在
        dp[0][1][1] = Integer.MIN_VALUE / 2;
        dp[0][1][2] = Integer.MIN_VALUE / 2;
        dp[0][0][1] = Integer.MIN_VALUE / 2;
        dp[0][0][2] = Integer.MIN_VALUE / 2;

        for (int i = 1; i < prices.length; i++) {
            //如果没持有且没进行过交易，那么收益肯定为0
            dp[i][0][0] = 0;
            //今天不持有且进行过一次交易，最大收益要么等于昨天没持有，今天不操作的收益；要么等于昨天持有，今天卖出股票的收益；
            dp[i][0][1] = Math.max(dp[i - 1][1][0] + prices[i], dp[i - 1][0][1]);
            //今天不持有且进行过两次交易，最大收益要么等于昨天没持有，今天不操作的收益；要么等于昨天持有，今天卖出股票的收益；
            dp[i][0][2] = Math.max(dp[i - 1][1][1] + prices[i], dp[i - 1][0][2]);

            //今天持有且没进行过交易，收益要么等于昨天没持有，今天买入了股票的收益；要么等于昨天也持有，今天继续持有的收益；
            dp[i][1][0] = Math.max(dp[i - 1][0][0] - prices[i], dp[i - 1][1][0]);
            //今天持有且进行过一次交易，最大收益要么等于昨天卖出过一次，今天买入了股票的收益；要么等于昨天也持有，今天继续持有的收益；
            dp[i][1][1] = Math.max(dp[i - 1][0][1] - prices[i], dp[i - 1][1][1]);
            //今天持有且进行过两次交易，不存在
            // dp[i][1][2]=Integer.MIN_VALUE/2;

        }

        return Math.max(0, Math.max(dp[prices.length - 1][0][1], dp[prices.length - 1][0][2]));

    }

    public int maxProfit_2(int[] prices) {
        int sell1 = 0, sell2 = 0;
        int buy1 = -prices[0], buy2 = -prices[0];

        for (int i = 1; i < prices.length; i++) {
            sell2 = Math.max(sell2, buy2 + prices[i]);
            buy2 = Math.max(sell1 - prices[i], buy2);

            sell1 = Math.max(sell1, buy1 + prices[i]);
            buy1 = Math.max(-prices[i], buy1);
        }

        return sell2;
    }


    /**
     * Your runtime beats 5.18 % of java submissions
     * Your memory usage beats 23.68 % of java submissions (57.8 MB)
     *
     * @param prices
     * @return
     */
    public int maxProfit_20220113(int[] prices) {
        /**
         * dp[i][j][k]表示第i天是否持有股票的收益，j=0表示不持有，1表示持有，已经进行了k次交易
         *  （卖出算一次交易）
         *
         * dp[i][0][0]表示第i天不持有，且进行过0次交易的收益
         *    =dp[i-1][0][0]
         *    等于前一天不持有，进行过0次交易，今天不操作
         *    或者前一天持有，进行过0次交易，今天卖出【不存在的情况，否则今天的交易次数就是1】
         *
         * dp[i][0][1]表示第i天不持有，且进行过1次交易的收益
         *    =Math.max(dp[i-1][0][1], dp[i-1][1][0]+prices[i])
         *    等于前一天不持有，进行过1次交易，今天不操作
         *    或者前一天持有，进行过0次交易，今天卖出，交易次数+1
         *
         * dp[i][0][2]表示第i天不持有，且进行过2次交易的收益【这是最终结果】
         *    =Math.max(dp[i-1][0][2], dp[i-1][1][1]+prices[i])
         *    等于前一天不持有，进行过2次交易，今天不操作
         *    或者前一天持有，进行过1次交易，今天卖出，交易次数+1
         *
         *
         * dp[i][1][0]表示第i天持有，且进行过1次交易的收益
         *    =Math.max(dp[i-1][0][0]-prices[i], dp[i-1][1][0])
         *    等于前一天不持有，进行过0次交易，今天买入
         *    或者前一天持有，进行过0次交易，今天不操作
         *
         * dp[i][1][1]表示第i天持有，且进行过1次交易的收益
         *    =Math.max(dp[i-1][0][1]-prices[i], dp[i-1][1][1])
         *    等于前一天不持有，进行过1次交易，今天买入
         *    或者前一天持有，进行过1次交易，今天不操作
         *
         * dp[i][1][2]表示第i天持有，且进行过2次交易的收益【不存在的情况】
         *
         */
        int[][][] dp = new int[prices.length][2][3];
        dp[0][0][0] = 0;
        //不存在的情况
        dp[0][0][1] = Integer.MIN_VALUE / 2;
        //不存在的情况
        dp[0][0][2] = Integer.MIN_VALUE / 2;

        dp[0][1][0] = -prices[0];
        //不存在的情况
        dp[0][1][1] = Integer.MIN_VALUE / 2;
        //不存在的情况
        dp[0][1][2] = Integer.MIN_VALUE / 2;

        for (int i = 1; i < prices.length; i++) {
            dp[i][0][0] = dp[i - 1][0][0];
            dp[i][0][1] = Math.max(dp[i - 1][0][1], dp[i - 1][1][0] + prices[i]);
            dp[i][0][2] = Math.max(dp[i - 1][0][2], dp[i - 1][1][1] + prices[i]);

            dp[i][1][0] = Math.max(dp[i - 1][0][0] - prices[i], dp[i - 1][1][0]);
            dp[i][1][1] = Math.max(dp[i - 1][0][1] - prices[i], dp[i - 1][1][1]);
            dp[i][1][2] = Integer.MIN_VALUE / 2;
        }

        return Math.max(0, Math.max(dp[prices.length - 1][0][2], dp[prices.length - 1][0][1]));
    }


    /**
     * 三维dp优化为二维
     * Your runtime beats 58.7 % of java submissions
     * Your memory usage beats 28.1 % of java submissions (54.4 MB)
     */
    public int maxProfit_20220113_space_opt_1(int[] prices) {
        int[][] dp = new int[2][3];
        dp[0][0] = 0;
        //不存在的情况
        dp[0][1] = Integer.MIN_VALUE / 2;
        //不存在的情况
        dp[0][2] = Integer.MIN_VALUE / 2;

        dp[1][0] = -prices[0];
        //不存在的情况
        dp[1][1] = Integer.MIN_VALUE / 2;
        //不存在的情况
        dp[1][2] = Integer.MIN_VALUE / 2;

        for (int i = 1; i < prices.length; i++) {
            dp[0][1] = Math.max(dp[0][1], dp[1][0] + prices[i]);
            dp[0][2] = Math.max(dp[0][2], dp[1][1] + prices[i]);

            dp[1][0] = Math.max(dp[0][0] - prices[i], dp[1][0]);
            dp[1][1] = Math.max(dp[0][1] - prices[i], dp[1][1]);
        }

        return Math.max(0, Math.max(dp[0][2], dp[0][1]));
    }

    /**
     * Your runtime beats 98.62 % of java submissions
     * Your memory usage beats 55.3 % of java submissions (51.3 MB)
     * @param prices
     * @return
     */
    public int maxProfit_20220113_space_opt_2(int[] prices) {
        /**
         * 优化的第一步是把二维dp中的dp[0][1]、dp[0][2]、dp[1][0]、dp[1][1]用变量代替
         */
        //dp[0][1]
        int a = Integer.MIN_VALUE / 2;
        //dp[0][2]
        int b = Integer.MIN_VALUE / 2;
        //dp[1][0]
        int c = -prices[0];
        //dp[1][1]
        int d = Integer.MIN_VALUE / 2;

        for (int i = 1; i < prices.length; i++) {
            a = Math.max(a, c + prices[i]);
            b = Math.max(b, d + prices[i]);

            c = Math.max(-prices[i], c);
            d = Math.max(a - prices[i], d);
        }

        return Math.max(0, Math.max(a, b));
    }

}
