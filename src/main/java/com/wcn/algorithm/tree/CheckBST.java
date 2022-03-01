package com.wcn.algorithm.tree;

/**
 * 给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。
 *
 * 有效 二叉搜索树定义如下：
 * 节点的左子树只包含 小于 当前节点的数。
 * 节点的右子树只包含 大于 当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 *
 * 链接：https://leetcode-cn.com/problems/validate-binary-search-tree
 */
public class CheckBST {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int value) {
            this.val = value;
        }
    }

    public boolean isValidBST(TreeNode root){
        return process(root).isBST;
    }

    private Result process(TreeNode node){
        if(node==null){
            return new Result(true, null, null);
        }
        Result leftResult = process(node.left);
        Result rightResult = process(node.right);

        boolean isBST = leftResult.isBST
                && rightResult.isBST
                && (leftResult.max==null?true:node.val>leftResult.max)
                && (rightResult.min==null?true:node.val < rightResult.min);
        int max = rightResult.max==null?node.val:Math.max(rightResult.max, node.val);
        int min = leftResult.min==null?node.val:Math.min(leftResult.min, node.val);
        return new Result(isBST, max, min);
    }

    static class Result{
        boolean isBST;
        Integer max;
        Integer min;

        public Result(boolean isBST, Integer max, Integer min) {
            this.isBST = isBST;
            this.max = max;
            this.min = min;
        }
    }
}
