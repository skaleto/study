package com.skaleto.things.leet;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Q827 {

    public int largestIsland(int[][] grid) {

        //染色用的数组
        List<Integer> area = new ArrayList<>();

        int max = 0;

        area.add(0);
        area.add(0);
        int color = 2;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    int cur = dfsLand(grid, i, j, color);
                    max = Math.max(max, cur);
                    area.add(cur);
                    color++;
                }
            }
        }

        if (max == 0) {
            return 1;
        }

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 0) {
                    max = Math.max(max, dfsOcean(grid, i, j, area));
                }
            }
        }


        return max;

    }

    public int dfsLand(int[][] grid, int row, int col, int color) {
        if (row < 0 || col < 0 || row >= grid.length || col >= grid[0].length) {
            return 0;
        }

        if (grid[row][col] != 1) {
            return 0;
        }

        //染色
        grid[row][col] = color;
        int area = 1;
        area += dfsLand(grid, row - 1, col, color);
        area += dfsLand(grid, row + 1, col, color);
        area += dfsLand(grid, row, col - 1, color);
        area += dfsLand(grid, row, col + 1, color);

        return area;

    }

    public int dfsOcean(int[][] grid, int row, int col, List<Integer> area) {
        Set<Integer> colorSet = new HashSet<>();

        if (row - 1 >= 0) {
            colorSet.add(grid[row - 1][col]);
        }

        if (row + 1 < grid.length) {
            colorSet.add(grid[row + 1][col]);
        }

        if (col - 1 >= 0) {
            colorSet.add(grid[row][col - 1]);
        }

        if (col + 1 < grid[0].length) {
            colorSet.add(grid[row][col + 1]);
        }

        int result = 1;
        for (Integer i : colorSet) {
            result += area.get(i);
        }

        return result;
    }
}
