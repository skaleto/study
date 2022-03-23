package com.skaleto.things.leet;

public class Q121 {

    public static void main(String[] args) {
        Q121 q = new Q121();
        System.out.println(q.maxProfit_1(new int[]{7, 1, 5, 3, 6, 4}));
    }

    public int maxProfit(int[] prices) {
        /**
         * 1. 定义子问题
         * 2. 写出子问题的递推关系
         * 3. 确定 DP 数组的计算顺序
         * 4. 空间优化（可选）
         *
         * 1. 子问题：当天的最优解等于当天价格减去当天之前最低的价格
         *    当天的最优解等于前一天的最优解加上前一天的价格和当天价格的差值
         *          如果前一天>当天，那么当天的最优解会减少；反之会增加
         * 2. f(k) = f(k-1) + nums[k-1]-nums[k]
         */

        //特判1
        if (prices.length == 1) {
            return 0;
        }

        //特判2
        if (prices.length == 2) {
            return prices[1] > prices[0] ? prices[1] - prices[0] : 0;
        }

        //dp递推
        int[] dp = new int[prices.length];
        dp[0] = 0;
        dp[1] = prices[1] > prices[0] ? prices[1] - prices[0] : 0;
        for (int i = 2; i < prices.length; i++) {
            int tmp = dp[i - 1] + prices[i] - prices[i - 1];
            dp[i] = Math.max(tmp, 0);
        }

        int max = 0;
        //找到最大值
        for (int i = 0; i < dp.length; i++) {
            max = Math.max(max, dp[i]);
        }

        return max;

    }

    public int maxProfit_1(int[] prices) {
        //特判1
        if (prices.length == 1) {
            return 0;
        }

        //dp递推
        int pre = prices[1] > prices[0] ? prices[1] - prices[0] : 0;
        int max = pre;
        for (int i = 2; i < prices.length; i++) {
            int tmp = pre + prices[i] - prices[i - 1];
            pre = Math.max(tmp, 0);
            max = Math.max(max, pre);
        }

        return max;

    }


    /**
     * Your runtime beats 13.12 % of java submissions
     * Your memory usage beats 10.14 % of java submissions (54.8 MB)
     *
     * @param prices
     * @return
     */
    public int maxProfit_20220113(int[] prices) {
        /**
         * dp[i][j]表示第i天是否持有股票的收益，j=0表示不持有，1表示持有
         *
         * dp[i][0]=Math.max(dp[i-1][0],dp[i-1][1]+prices[i])，
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
            dp[i][1] = Math.max(dp[i - 1][1], -prices[i]);
        }

        return dp[prices.length - 1][0];

    }

    /**
     * Your runtime beats 89.57 % of java submissions
     * Your memory usage beats 67.24 % of java submissions (51.3 MB)
     *
     * @param prices
     * @return
     */
    public int maxProfit_20220113_space_opt(int[] prices) {

        int nhold = 0;
        int hold = -prices[0];

        for (int i = 1; i < prices.length; i++) {
            nhold = Math.max(nhold, hold + prices[i]);
            hold = Math.max(hold, -prices[i]);
        }

        return nhold;
    }

    /**
     * Your runtime beats 89.57 % of java submissions
     * Your memory usage beats 26.83 % of java submissions (51.5 MB)
     * @param prices
     * @return
     */
    public int maxProfit_20220113_greedy(int[] prices) {
        /**
         * 贪心的大致思路是找到一个最小值，往后找差最大的值
         */
        int min = Integer.MAX_VALUE;
        int result = 0;

        for (int i = 0; i < prices.length; i++) {
            min = Math.min(prices[i], min);
            result = Math.max(result, prices[i] - min);
        }

        return result;
    }
}
