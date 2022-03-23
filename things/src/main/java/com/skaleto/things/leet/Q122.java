package com.skaleto.things.leet;

public class Q122 {

    public static void main(String[] args) {
        Q122 q = new Q122();
        System.out.println(q.maxProfit_1(new int[]{7, 1, 5, 3, 6, 4}));
    }

    /**
     * 贪心算法
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int result = 0;
        int yesterday = -1;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] >= yesterday) {
                if (yesterday != -1) {
                    result += prices[i] - yesterday;
                }
            }
            yesterday = prices[i];
        }

        return result;
    }

    /**
     * 动规
     *
     * @param prices
     * @return
     */
    public int maxProfit_1(int[] prices) {

        if (prices.length == 1) {
            return 0;
        }

        int[] dp = new int[prices.length];
        dp[0] = 0;
        dp[1] = Math.max(prices[1] - prices[0], 0);

        int max = Math.max(dp[0], dp[1]);
        for (int i = 2; i < prices.length; i++) {
            dp[i] = dp[i - 1] + Math.max(prices[i] - prices[i - 1], 0);
            max = Math.max(max, dp[i]);
        }

        return max;
    }

    /**
     * 动规+空间优化
     *
     * @param prices
     * @return
     */
    public int maxProfit_2(int[] prices) {
        /**
         * 1. 找最优子结构
         * 2. 找递推路径
         * 3. 确定dp状态转移方程
         *
         * 对于[7,1,5,3,6,4]
         * 第一天：0，第二天：0，第三天：4，第四天：4，第五天：4+3=7，第六天：7
         *
         * 3.f(x)=f(x-1)+Math.max(prices[i]-prices[i-1],0)
         *
         */
        if (prices.length == 1) {
            return 0;
        }
        int pre = Math.max(prices[1] - prices[0], 0);
        int max = pre;
        for (int i = 2; i < prices.length; i++) {
            pre += Math.max(prices[i] - prices[i - 1], 0);
            max = Math.max(max, pre);
        }

        return max;
    }


    public int maxProfit_20220113(int[] prices) {
        /**
         * dp[i][j]表示第i天是否持有股票的收益，j=0表示不持有，1表示持有
         *
         * dp[i][0]=Math.max(dp[i-1][0],dp[i-1][1]+prices[i])
         *    表示第i天不持有的收益等于前一天不持有的收益（当天不进行操作）
         *                      或者前一天持有，今天卖出的收益
         *
         * 假如只能买卖一次，那么
         * dp[i][1]=Math.max(dp[i-1][1],-prices[i])
         *    表示第i天持有的收益等于前一天持有的收益（当天不进行操作）
         *                    或者前面没有操作，今天进行买入的收益
         *
         * 假如能买卖多次，那么
         * dp[i][1]=Math.max(dp[i-1][1],dp[i-1][0]-prices[i])
         *    表示第i天持有的收益等于前一天持有的收益（当天不进行操作）
         *                    或者前一天不持有（可能前面有交易），今天进行买入的收益
         *
         */

        int[][] dp = new int[prices.length][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];

        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }

        return dp[prices.length - 1][0];
    }

    public int maxProfit_20220113_space_opt(int[] prices) {

        int nhold = 0;
        int hold = -prices[0];

        for (int i = 1; i < prices.length; i++) {
            nhold = Math.max(nhold, hold + prices[i]);
            hold = Math.max(hold, nhold - prices[i]);
        }

        return nhold;
    }

    public int maxProfit_20220113_greedy(int[] prices) {
        /**
         * 贪心：把每一个有增加趋势的差加进来
         * 假如prices[i]大于prices[i-1]，则结果加上prices[i]-prices[i-1]
         *
         */

        int result = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                result += prices[i] - prices[i - 1];
            }
        }

        return result;
    }





}
