package com.skaleto.things.leet;

public class Q63 {

    public static void main(String[] args) {
        Q63 q = new Q63();
        System.out.println(q.uniquePathsWithObstacles(new int[][]{{0, 0}, {1, 1}, {0, 0}}));
    }


    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int row = obstacleGrid.length;
        int col = obstacleGrid[0].length;

        int[][] result = new int[row][col];

        //先考虑第一行的情况，遇到障碍物直接结束，后面的路径已经不可达了
        for (int i = 0; i < row; i++) {
            if (obstacleGrid[i][0] == 1) {
                break;
            }
            result[i][0] = 1;
        }

        //先考虑第一列的情况，遇到障碍物直接结束，后面的路径已经不可达了
        for (int j = 0; j < col; j++) {
            if (obstacleGrid[0][j] == 1) {
                break;
            }
            result[0][j] = 1;
        }

        //这里不需要再考虑第一行和第一列的情况了
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if (obstacleGrid[i][j] == 1) {
                    result[i][j] = 0;
                    continue;
                }

                result[i][j] = result[i - 1][j] + result[i][j - 1];
            }
        }

        return result[row - 1][col - 1];

    }


    public int uniquePathsWithObstacles_1(int[][] obstacleGrid) {
        int row = obstacleGrid.length;
        int col = obstacleGrid[0].length;

        int[] store = new int[col];
        store[0] = obstacleGrid[0][0] == 0 ? 1 : 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (obstacleGrid[i][j] == 1) {
                    store[j] = 0;
                    continue;
                }

                if (j - 1 >= 0) {
                    store[j] += store[j - 1];
                }

            }
        }

        return store[col - 1];
    }
}
