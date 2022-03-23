package com.skaleto.things.leet;

public class Q200 {

    public int numIslands(char[][] grid) {
        int islandNum=0;
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[i].length;j++){
                //如果某个格子是陆地，从这个格子开始进行dfs，每搜完一次就产生了一整块大陆
                if(grid[i][j]=='1'){
                    dfs(grid,i,j);
                    islandNum++;
                }
            }
        }
        return islandNum;
    }

    public void dfs(char[][] grid, int row, int col){
        //超出地图边界的点
        if(row<0 || row>=grid.length || col<0 || col>=grid[0].length){
            return;
        }

        //不是未访问的陆地时直接返回
        if(grid[row][col]!='1'){
            return;
        }

        //该陆地标记为已访问
        grid[row][col]='2';

        //上下左右四个格子都搜一遍
        dfs(grid, row, col+1);
        dfs(grid, row, col-1);
        dfs(grid, row+1, col);
        dfs(grid, row-1, col);

    }

}
