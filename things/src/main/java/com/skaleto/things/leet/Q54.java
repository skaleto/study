package com.skaleto.things.leet;

import java.util.ArrayList;
import java.util.List;

public class Q54 {

    public static void main(String[] args) {
        Q54 q = new Q54();
//        System.out.println(q.spiralOrder(new int[][]{
//                {1, 2, 3},
//                {4, 5, 6},
//                {7, 8, 9}}
//        ));
        System.out.println(q.spiralOrder(new int[][]{
                {7},
                {9},
                {6}}
        ));
    }

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        int row = matrix.length;
        int col = matrix[0].length;
        int i = 0;
        while (row > 0 && col > 0 && i < matrix.length && i < matrix[0].length) {
            spiral(result, matrix, i, i, row, col);
            row -= 2;
            col -= 2;
            i++;
        }
        return result;
    }

    public void spiral(List<Integer> result, int[][] matrix, int startRow, int startCol, int rows, int cols) {

        for (int j = 0; j < cols; j++) {
            result.add(matrix[startRow][j + startCol]);
        }

        if (rows == 1) {
            return;
        }

        for (int i = 1; i <= rows - 2; i++) {
            result.add(matrix[startRow + i][startCol + cols - 1]);
        }

        for (int j = cols - 1; j >= 0; j--) {
            result.add(matrix[startRow + rows - 1][startCol + j]);
        }

        if (cols == 1) {
            return;
        }

        for (int i = rows - 2; i >= 1; i--) {
            result.add(matrix[startRow + i][startCol]);
        }

    }
}
