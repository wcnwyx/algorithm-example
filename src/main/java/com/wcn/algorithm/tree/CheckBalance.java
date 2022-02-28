package com.wcn.algorithm.tree;

/**
 * 判断一棵二叉树是否是平衡树
 * 每棵子树的左树高度和右树高度差不大于1
 *
 * https://leetcode-cn.com/problems/balanced-binary-tree/submissions/
 */
public class CheckBalance {
    static class Node{
        public int value;
        public Node left;
        public Node right;
    }

    static class Result{
        public boolean isBalance;//是否平衡
        public int high;//最大高度

        public Result(boolean isBalance, int high) {
            this.isBalance = isBalance;
            this.high = high;
        }
    }

    public static Result process(Node node){
        if(node==null){
            return new Result(true, 0);
        }
        Result leftResult = process(node.left);
        Result rightResult = process(node.right);

        int high = Math.max(leftResult.high, rightResult.high)+1;
        boolean isBalance = leftResult.isBalance && rightResult.isBalance && Math.abs(leftResult.high- rightResult.high)<=1;
        return new Result(isBalance, high);
    }


}
