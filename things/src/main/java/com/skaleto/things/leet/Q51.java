package com.skaleto.things.leet;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Q51 {

    public static void main(String[] args) {
        Q51 q = new Q51();
        System.out.println(q.solveNQueens(4));
    }

    public List<List<String>> solveNQueens(int n) {
        //最终结果
        List<List<String>> result = new ArrayList<>();

        //每一次遍历到最后的结果，长度总是为N
        Deque<Integer> path = new LinkedList<>();

        boolean[] col = new boolean[n];
        boolean[] left_diagonal = new boolean[2 * n];
        boolean[] right_diagonal = new boolean[2 * n];

        dfs(result, path, 0, n, col, left_diagonal, right_diagonal);

        return result;
    }

    /**
     * @param result
     * @param path
     * @param curRow 当前行索引
     * @param colNum 列数
     */
    public void dfs(List<List<String>> result, Deque<Integer> path, int curRow, int colNum, boolean[] col, boolean[] left, boolean[] right) {
        //path中的结果满了（数量等于长或宽）
        if (curRow == colNum) {
            result.add(convertPathToResult(path));
            return;
        }

        //遍历这一行中所有列的位置
        for (int i = 0; i < colNum; i++) {
            //如果该列已被占 或者 该点的横纵坐标差值对应的位置已被占(左上的对角线) 或者 该点的横纵坐标和对应的位置已被占(右上的对角线)
            //则直接寻找下一个点
            if (col[i] || left[curRow - i + colNum - 1] || right[curRow + i]) {
                continue;
            }

            //此时满足需求，将三个记录位置置为true
            col[i] = true;
            left[curRow - i + colNum - 1] = true;
            right[curRow + i] = true;

            //加入该点的列坐标
            path.addLast(i);

            //搜索下一层
            dfs(result, path, curRow + 1, colNum, col, left, right);

            //将三个记录位置复位
            col[i] = false;
            left[curRow - i + colNum - 1] = false;
            right[curRow + i] = false;

            //去除该点的列坐标
            path.removeLast();

        }
    }

    public List<String> convertPathToResult(Deque<Integer> path) {
        List<String> result = new ArrayList<>();
        int length = path.size();
        for (Integer i : path) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < length; j++) {
                sb.append(j == i ? "Q" : ".");
            }
            result.add(sb.toString());
        }
        return result;
    }


    public List<List<String>> solveNQueens_20211227(int n) {
        List<List<String>> result = new ArrayList<>();

        /**
         * 当一个棋子放到棋盘上时，会占据该行、该列和该对角线
         * 某一个位置能不能放，需要检查它所在行、所在列以及所在正反对角线是否存在棋子
         */

        int[][] path = new int[n][n];
        dfs(0, n, path, result);

        return result;

    }

    public void dfs(int curRow, int n, int[][] path, List<List<String>> result) {
        if (curRow == n) {
            result.add(changePath2String(path));
            return;
        }

        //遍历该行上的每一个列
        for (int col = 0; col < n; col++) {
            //剪枝操作过滤掉不符合的情况
            boolean isValid = true;

            //检查当前点该列是否存在值
            for (int row = 0; row < n; row++) {
                if (path[row][col] == 1) {
                    isValid = false;
                    break;
                }
            }
            if (!isValid) {
                continue;
            }

            //遍历检查当前点左对角线上方是否存在已经被占用的位置（下方不用考虑，因为还没遍历到）
            int tmpRow = curRow;
            int tmpCol = col;
            while (--tmpRow >= 0 && --tmpCol >= 0) {
                if (path[tmpRow][tmpCol] == 1) {
                    isValid = false;
                    break;
                }
            }
            if (!isValid) {
                continue;
            }

            //遍历检查当前点右对角线上方是否存在已经被占用的位置（下方不用考虑，因为还没遍历到）
            tmpRow = curRow;
            tmpCol = col;
            while (--tmpRow >= 0 && ++tmpCol < n) {
                if (path[tmpRow][tmpCol] == 1) {
                    isValid = false;
                    break;
                }
            }
            if (!isValid) {
                continue;
            }

            path[curRow][col] = 1;
            dfs(curRow + 1, n, path, result);
            path[curRow][col] = 0;
        }
    }

    public List<String> changePath2String(int[][] path) {
        List<String> res = new ArrayList<>();

        //转换逻辑
        for (int i = 0; i < path.length; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < path[i].length; j++) {
                sb.append(path[i][j] == 1 ? 'Q' : '.');
            }
            res.add(sb.toString());
        }

        return res;
    }


}
