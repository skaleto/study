package com.skaleto.things.leet;

import java.util.Arrays;

public class Q529 {

    public static void main(String[] args) {
        Q529 q = new Q529();
        char[][] result = q.updateBoard(new char[][]{
                        {'E', 'E', 'E', 'E', 'E'},
                        {'E', 'E', 'M', 'E', 'E'},
                        {'E', 'E', 'E', 'E', 'E'},
                        {'E', 'E', 'E', 'E', 'E'}},
                new int[]{3, 0}
        );

        System.out.println(Arrays.deepToString(result));

    }

    public char[][] updateBoard(char[][] board, int[] click) {
        int r = click[0];
        int c = click[1];

        char cur = board[r][c];

        //如果挖的是一个M
        if (cur == 'M') {
            board[r][c] = 'X';
            return board;
        }

        //如果挖的是一个E
        if (cur == 'E') {
            int aroundMineNum = getAroundMineNum(board, r, c);
            if (aroundMineNum == 0) {
                //递归把所有相邻的未挖出的方块揭露
                dfs(board, r, c);
            }

            if (aroundMineNum != 0) {
                board[r][c] = (char) (aroundMineNum + '0');
            }
        }

        return board;
    }

    public void dfs(char[][] board, int row, int col) {
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length) {
            return;
        }

        //仅针对E方块做迭代，非E方块的直接返回即可
        if (board[row][col] != 'E') {
            return;
        }

        int aroundMineNum = getAroundMineNum(board, row, col);
        if (aroundMineNum == 0) {
            board[row][col] = 'B';
            //递归把所有相邻的未挖出的方块揭露
            dfs(board, row - 1, col - 1);
            dfs(board, row - 1, col);
            dfs(board, row - 1, col + 1);

            dfs(board, row, col - 1);
            dfs(board, row, col + 1);

            dfs(board, row + 1, col - 1);
            dfs(board, row + 1, col);
            dfs(board, row + 1, col + 1);
        }

        if (aroundMineNum != 0) {
            board[row][col] = (char) (aroundMineNum + '0');
        }

    }

    public int getAroundMineNum(char[][] board, int row, int col) {
        int mineNum = 0;
        if (row - 1 >= 0 && col - 1 >= 0) {
            mineNum += board[row - 1][col - 1] == 'M' ? 1 : 0;
        }
        if (row - 1 >= 0) {
            mineNum += board[row - 1][col] == 'M' ? 1 : 0;
        }
        if (row - 1 >= 0 && col + 1 < board[row].length) {
            mineNum += board[row - 1][col + 1] == 'M' ? 1 : 0;
        }

        if (col - 1 >= 0) {
            mineNum += board[row][col - 1] == 'M' ? 1 : 0;
        }
        if (col + 1 < board[row].length) {
            mineNum += board[row][col + 1] == 'M' ? 1 : 0;
        }

        if (row + 1 < board.length && col - 1 >= 0) {
            mineNum += board[row + 1][col - 1] == 'M' ? 1 : 0;
        }
        if (row + 1 < board.length) {
            mineNum += board[row + 1][col] == 'M' ? 1 : 0;
        }
        if (row + 1 < board.length && col + 1 < board[row].length) {
            mineNum += board[row + 1][col + 1] == 'M' ? 1 : 0;
        }
        return mineNum;
    }

}
