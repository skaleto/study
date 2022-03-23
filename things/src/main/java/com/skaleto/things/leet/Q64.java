package com.skaleto.things.leet;

public class Q64 {

    public static void main(String[] args) {
        Q64 q = new Q64();
        System.out.println(q.minPathSum(new int[][]{
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}}));
    }

    public int minPathSum(int[][] grid) {

        /**
         * 某个位置的值等于该位置的上方或左方移动得到
         * dp[i][j]=Math.min(dp[i-1][j],dp[i][j-1])+grid[i][j]
         *
         */

        int row = grid.length;
        int col = grid[0].length;
        int[][] dp = new int[row][col];
        for (int i = 0; i < grid.length; i++) {
            dp[i][0] = grid[i][0];
        }

        for (int j = 0; j < grid[0].length; j++) {
            dp[0][j] = grid[0][j];
        }

        for (int i = 1; i < grid.length; i++) {
            for (int j = 1; j < grid[0].length; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }

        return dp[row - 1][col - 1];

    }

    public int minPathSum_20220116(int[][] grid) {
        /**
         * dp[i][j]=grid[i][j]+Math.min(dp[i-1][j],dp[i][j-1])
         */
        int r = grid.length;
        int c = grid[0].length;
        int[][] dp = new int[grid.length][grid[0].length];

        dp[0][0] = grid[0][0];
        for (int i = 1; i < c; i++) {
            dp[0][i] = dp[0][i - 1] + grid[0][i];
        }
        for (int i = 1; i < r; i++) {
            for (int j = 0; j < c; j++) {
                int tmp = dp[i - 1][j];
                if (j >= 1) {
                    tmp = Math.min(tmp, dp[i][j - 1]);
                }
                dp[i][j] = grid[i][j] + tmp;
            }
        }

        return dp[r - 1][c - 1];

    }

    public int minPathSum_20220116_space_opt(int[][] grid) {
        /**
         * dp[i][j]=grid[i][j]+Math.min(dp[i-1][j],dp[i][j-1])
         */
        int r = grid.length;
        int c = grid[0].length;
        int[] dp = new int[c];

        dp[0] = grid[0][0];
        for (int i = 1; i < c; i++) {
            dp[i] = dp[i - 1] + grid[0][i];
        }
        for (int i = 1; i < r; i++) {
            for (int j = 0; j < c; j++) {
                int tmp = dp[j];
                if (j >= 1) {
                    tmp = Math.min(tmp, dp[j - 1]);
                }
                dp[j] = grid[i][j] + tmp;
            }
        }

        return dp[c - 1];

    }
}
