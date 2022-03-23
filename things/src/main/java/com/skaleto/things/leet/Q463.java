package com.skaleto.things.leet;

public class Q463 {

    public int islandPerimeter(int[][] grid) {

        int perimeter=0;
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                if(grid[i][j]==1){
                    perimeter=dfs(grid, i, j);
                    return perimeter;
                }
            }
        }

        return perimeter;

    }

    public int dfs(int[][] grid, int row, int col){
        if(row<0 || col<0 || row>=grid.length || col>=grid[0].length){
            return 0;
        }

        if(grid[row][col]==2 || grid[row][col]==0){
            return 0;
        }

        int peri=0;
        if(grid[row][col]==1){
            grid[row][col]='2';
            if(row-1<0 || grid[row-1][col]==0){
                peri++;
            }
            if(row+1>=grid.length || grid[row+1][col]==0){
                peri++;
            }
            if(col-1<0 || grid[row][col-1]==0){
                peri++;
            }
            if(col+1>=grid[0].length || grid[row][col+1]==0){
                peri++;
            }

            peri+=dfs(grid, row-1, col);
            peri+=dfs(grid, row+1, col);
            peri+=dfs(grid, row, col-1);
            peri+=dfs(grid, row, col+1);
        }

        return peri;

    }
}
