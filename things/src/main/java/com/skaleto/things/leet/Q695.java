package com.skaleto.things.leet;

public class Q695 {
    public int maxAreaOfIsland(int[][] grid) {

        int max=0;
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                if(grid[i][j]==1){
                    max=Math.max(max,dfs(grid, i, j));
                }
            }
        }

        return max;

    }

    public int dfs(int[][] grid, int row, int col){
        if(row<0 || col<0 || row>=grid.length || col>=grid[0].length){
            return 0;
        }

        if(grid[row][col]==2 || grid[row][col]==0){
            return 0;
        }

        int area=0;
        if(grid[row][col]==1){
            grid[row][col]='2';
            area++;

            area+=dfs(grid, row-1, col);
            area+=dfs(grid, row+1, col);
            area+=dfs(grid, row, col-1);
            area+=dfs(grid, row, col+1);
        }

        return area;

    }
}
