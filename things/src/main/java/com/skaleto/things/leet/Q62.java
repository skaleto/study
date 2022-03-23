package com.skaleto.things.leet;

public class Q62 {

    public static void main(String[] args) {
        Q62 q = new Q62();
        q.uniquePaths(3, 7);
    }

    public int uniquePaths(int m, int n) {
        //初始化一个二维数组，代表整个路径的棋盘
        int[][] board = new int[m][n];
        //从棋盘右下角向上遍历
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                //先把底边和右边填满，因为这两条边始终只有一种走法
                if (i == m - 1 || j == n - 1) {
                    board[i][j] = 1;
                    continue;
                }

                //某个位置的数值等于下边位置+右边位置的数量和
                board[i][j] = board[i + 1][j] + board[i][j + 1];
            }
        }

        return board[0][0];
    }

    public int uniquePaths_20220109(int m, int n) {
        int[][] dp = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0) {
                    dp[i][j] = 1;
                    continue;
                }

                if (j == 0) {
                    dp[i][j] = 1;
                    continue;
                }

                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }

        return dp[m - 1][n - 1];
    }

    public int uniquePaths_20220109_space_opt(int m, int n) {
        int[] dp = new int[n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0) {
                    dp[j] = 1;
                    continue;
                }

                dp[j] = dp[j] + dp[j - 1];
            }
        }

        return dp[n - 1];
    }
}
