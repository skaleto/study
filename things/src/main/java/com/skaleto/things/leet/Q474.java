package com.skaleto.things.leet;

public class Q474 {

    /**
     * 这道题独立写出来了，可把我牛逼坏了，叉会腰
     *
     * @param strs
     * @param m
     * @param n
     * @return
     */
    public int findMaxForm(String[] strs, int m, int n) {
        /**
         * 要在str数组里面找到一个子集，这个子集中0的数量<=m，1的数量<=n
         *
         * 01背包问题
         * 定义动态转移数组
         * dp[i][j][k]表示在strs数组的前i个字符串中，满足0的数量不超过j，1的数量不超过k的最大子集的长度
         *
         * 先把strs数组转成两个数组num0[]和num1[]，记录每个元素的0和1的数量
         * 不选择第i个时，dp值等于前i-1个放入j和k容量中
         *      dp[i][j][k]=dp[i-1][j][k]
         * 选择第i个时，dp值等于前i-1个放入j-num0[i],k-num1[i]
         *      dp[i][j][k]=dp[i-1][j-num0[i]][k-num1[i]] + 1
         */

        int[][] dp = new int[m + 1][n + 1];
        dp[0][0] = 0;

        int num0;
        int num1;
        for (int i = 0; i < strs.length; i++) {
            String str0 = strs[i].replace("1", "");
            String str1 = strs[i].replace("0", "");
            num0 = str0.length();
            num1 = str1.length();
            for (int j = m; j >= num0; j--) {
                for (int k = n; k >= num1; k--) {
                    dp[j][k] = Math.max(dp[j][k], dp[j - num0][k - num1] + 1);
                }
            }
        }

        return dp[m][n];
    }
}
