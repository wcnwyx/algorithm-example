package com.wcn.algorithm.recursive;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * n皇后问题 研究的是如何将 n个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 *
 * 给你一个整数 n ，返回所有不同的n皇后问题 的解决方案。
 *
 * 每一种解法包含一个不同的皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
 *
 * 链接：https://leetcode-cn.com/problems/n-queens
 */
public class Queue {

    public static void main(String[] args) {
        Queue queue = new Queue();
        List<List<String>> result = queue.solveNQueens(5);
        for(List<String> list:result){
            for(String s:list){
                System.out.println(s);
            }
            System.out.println("---------------------------");
        }
    }

    public List<List<String>> solveNQueens(int n) {
        List<int[]> result = new ArrayList<>();
        process(n, 0, new int[n], result);

        //构建字符串
        List<List<String>> ret = new ArrayList<>();
        List<String> innerList = null;
        StringBuilder line = null;
        for(int[] array:result){
            innerList = new ArrayList<>();
            for(int x=0;x<n;x++){
                line = new StringBuilder();
                for(int y=0;y<n;y++){
                    if(y==array[x]){
                        line.append("Q");
                    }else{
                        line.append(".");
                    }
                }
                innerList.add(line.toString());
            }
            ret.add(innerList);
        }
        return ret;
    }

    /**
     *
     * @param n
     * @param x 当前行数
     * @param queue 每一行皇后的位置
     * @param result 所有可以的位置保存其中
     */
    public void process(int n, int x, int[] queue, List<int[]> result){
        if(x==n){
            //已经检查到了第n行，说明前面的0到n-1行都找到位置了
            result.add(queue);
            return ;
        }
        for(int y=0;y<n;y++){
            if(check(x, y, queue)){
                //x行在该y的坐标位置可以放置皇后，拷贝数组往下传，要不然多次处理的是同一个array对象
                int[] nextQueue = Arrays.copyOf(queue, n);
                nextQueue[x] = y;
                //x+1行继续找
                process(n, x+1, nextQueue, result);
            }
        }
    }

    public boolean check(int x, int y, int[] result){
        if(x==0){
            return true;
        }

        for(int p=0;p<=x-1;p++){
            //循环x行之前的就可以
            int queueX = p;
            int queueY = result[p];
            if(queueX==x || queueY==y || Math.abs(queueX-x)==Math.abs(queueY-y)){
                //有攻击性
                return false;
            }
        }
        return true;
    }
}
