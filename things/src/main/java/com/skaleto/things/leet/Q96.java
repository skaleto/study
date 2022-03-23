package com.skaleto.things.leet;

public class Q96 {

    int[][] memo = null;

    /**
     * Your runtime beats 100 % of java submissions
     * Your memory usage beats 47.81 % of java submissions (38 MB)
     * @param n
     * @return
     */
    public int numTrees(int n) {
        memo = new int[n + 1][n + 1];
        return count(1, n);
    }

    public int count(int lo, int hi) {
        if (lo > hi) {
            return 1;
        }

        if (memo[lo][hi] != 0) {
            return memo[lo][hi];
        }

        int result = 0;
        for (int i = lo; i <= hi; i++) {
            int left = count(lo, i - 1);
            int right = count(i + 1, hi);
            result += left * right;
        }
        memo[lo][hi] = result;
        return result;
    }
}
