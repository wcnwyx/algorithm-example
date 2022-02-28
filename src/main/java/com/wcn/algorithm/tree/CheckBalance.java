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
        if(leftResult.isBalance && rightResult.isBalance){
            if(Math.abs(leftResult.high- rightResult.high)>1){
                //高度差大于1，不是平衡树
                return new Result(false, 0);//不是平衡树的情况下，高度就没有意义了
            }else{
                return new Result(true, Math.max(leftResult.high, rightResult.high)+1);
            }
        }else{
            return new Result(false, 0);//不是平衡树的情况下，高度就没有意义了
        }
    }


}
