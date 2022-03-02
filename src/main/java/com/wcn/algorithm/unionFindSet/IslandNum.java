package com.wcn.algorithm.unionFindSet;

/**
 * 给你一个'1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 *
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
 *
 * 此外，你可以假设该网格的四条边均被水包围。
 *
 * 链接：https://leetcode-cn.com/problems/number-of-islands
 */
public class IslandNum {
    /**
     * 感染函数处理
     * 总体思路就是找到一个1，然后将和该1相连的所有1置位0，然后再循环下一个1处理。
     * @param grid
     * @return
     */
    public int numIslands(char[][] grid) {
        int num = 0;
        for(int x=0;x<grid.length;x++){
            char[] array = grid[x];
            for(int y=0;y<array.length;y++){
                if(grid[x][y]=='1'){
                    checkAndSet(x,y,grid);
                    num++;
                }
            }
        }
        return num;
    }

    /**
     * 将 x、y这个点一直往上下左右四个方向延伸，将所有的1变为0，延伸越界返回，延伸到了0也返回。
     * @param x
     * @param y
     * @param grid
     */
    public void checkAndSet(int x, int y, char[][] grid){
        if(x<0 || x>=grid.length || y<0 || y>=grid[x].length){
            //地图越界，返回
            return ;
        }
        if(grid[x][y]=='0'){
            //已经不是‘1’了
            return ;
        }

        grid[x][y] = '0';
        checkAndSet(x-1, y, grid);
        checkAndSet(x+1, y, grid);
        checkAndSet(x, y-1, grid);
        checkAndSet(x, y+1, grid);
    }
}
