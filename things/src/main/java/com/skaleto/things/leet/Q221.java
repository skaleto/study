package com.skaleto.things.leet;

public class Q221 {

    public static void main(String[] args) {
        Q221 q = new Q221();
//        char[][] test = new char[][]{
//                {'1', '0', '1', '0', '0'},
//                {'1', '0', '1', '1', '1'},
//                {'1', '1', '1', '1', '1'},
//                {'1', '0', '0', '1', '0'}};

//        char[][] test = new char[][]{
//                {'1', '1', '1', '1', '1'},
//                {'1', '1', '1', '1', '1'},
//                {'0', '0', '0', '0', '0'},
//                {'1', '1', '1', '1', '1'},
//                {'1', '1', '1', '1', '1'}};

//        char[][] test = new char[][]{
//                {'1', '1', '0', '1'},
//                {'1', '1', '0', '1'},
//                {'1', '1', '1', '1'}};

        char[][] test = new char[][]{
                {'0', '1'},
                {'0', '1'}};
        System.out.println(q.maximalSquare_20220118(test));

    }

    public int maximalSquare(char[][] matrix) {
        /**
         * dp[i][j]为 matrix[0][0] 到 matrix[i][j] 位置的最大正方形面积
         *
         * ["1","0","1","0","0"],
         * ["1","0","1","1","1"],
         * ["1","1","1","1","1"],
         * ["1","0","0","1","0"]
         *
         * 1   1   1   1   1
         * 1   1   1   1   1
         * 1   1   1   4   4
         * 1   1   1   4   4
         *
         * ["1","0","1","0","0"],
         * ["1","0","1","1","1"],
         * ["1","1","1","1","1"],
         * ["1","0","1","1","1"]
         *
         * 1   1   1   1   1
         * 1   1   1   1   1
         * 1   1   1   4   4
         * 1   1   1   4   9
         *
         *
         * 对于matrix[i][j]来说，
         * 如果matrix[i][j]的左上、上、左三个位置都是1，即matrix[i-1][j]==1 && matrix[i][j-1]==1 && matrix[i-1][j-1]==1
         *      则进一步判断 dp[i-1][j]、dp[i][j-1]和dp[i-1][j-1]是否相等
         *          如果三者相等，则dp[i][j]=(Math.sqrt(dp[i-1][j-1])+1)^2
         *          如果三者不等，则dp[i][j]=Math.max(dp[i-1][j],dp[i][j-1],dp[i-1][j-1])
         * 如果matrix[i][j]的左上、上、左三个位置有一个不是1，那么dp[i][j]=dp[i-1][j-1]
         */

        int row = matrix.length;
        int col = matrix[0].length;
        int[][] dp = new int[row][col];

        int max = 0;
        //第一行第一列的处理
        for (int j = 0; j < col; j++) {
            dp[0][j] = matrix[0][j] == '1' ? 1 : 0;
            max = Math.max(max, dp[0][j]);
        }

        for (int i = 0; i < row; i++) {
            dp[i][0] = matrix[i][0] == '1' ? 1 : 0;
            max = Math.max(max, dp[i][0]);
        }

        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if (matrix[i][j] == '1') {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                }
                max = Math.max(max, dp[i][j]);
            }
        }

        return max * max;

    }


    public int maximalSquare_20220118(char[][] matrix) {
        /**
         * [
         * ["1","0","1","0","0"],
         * ["1","0","1","1","1"],
         * ["1","1","1","1","1"],
         * ["1","0","0","1","0"]
         * ]
         *
         * dp[i][j]表示左上角到i，j位置能组成的最大正方形边长
         *
         *
         * 假如matrix[i][j]=='0'
         *     dp[i][j]=Math.max(dp[i-1][j],dp[i][j-1],dp[i-1][j-1])
         * 假如matrix[i][j]=='1'
         *     边长+1的条件是matrix[i][j]往左和往右移动dp[i-1][j-1]个长度的范围内都是1
         *
         */

        int r = matrix.length;
        int c = matrix[0].length;

        int[][] dp = new int[r][c];
        dp[0][0] = matrix[0][0] == '0' ? 0 : 1;
        for (int i = 1; i < r; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], matrix[i][0] == '0' ? 0 : 1);
        }

        for (int j = 1; j < c; j++) {
            dp[0][j] = Math.max(dp[0][j - 1], matrix[0][j] == '0' ? 0 : 1);
        }

        for (int i = 1; i < r; i++) {
            for (int j = 1; j < c; j++) {
                if (matrix[i][j] == '0') {
                    dp[i][j] = Math.max(Math.max(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]);
                } else {
                    int sideLength = dp[i - 1][j - 1];
                    boolean canAdd = true;
                    for (int k = i - 1; k >= i - sideLength && k >= 0; k--) {
                        if (matrix[k][j] == '0') {
                            canAdd = false;
                            break;
                        }
                    }

                    for (int k = j - 1; k >= j - sideLength && k >= 0; k--) {
                        if (matrix[i][k] == '0') {
                            canAdd = false;
                            break;
                        }
                    }

                    if (canAdd) {
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                    } else {
                        dp[i][j] = dp[i - 1][j - 1];
                    }
                }
            }
        }

        return dp[r - 1][c - 1] * dp[r - 1][c - 1];

    }
}
