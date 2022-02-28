package com.wcn.algorithm.tree;

/**
 * 获取一棵树的最大距离，距离即一个节点到另外一个节点，每次走一个节点，需要走多少步
 */
public class CheckMaxDistance {

    static class Node{
        public int value;
        public Node left;
        public Node right;
    }

    static class Result{
        public int maxDistance;//最大距离
        public int height;//高度

        public Result(int maxDistance, int height) {
            this.maxDistance = maxDistance;
            this.height = height;
        }
    }

    public static Result process(Node node){
        if(node==null){
            return new Result(0, 0);
        }
        Result leftResult = process(node.left);
        Result rightResult = process(node.right);

        int height = Math.max(leftResult.height, rightResult.height)+1;
        //如果最大距离经过该节点node，则最大距离就是 leftResult.height + rightResult.height + 1
        //如果最大距离不经过该节点node，最大距离就是左子树或者右子树的最大距离
        int maxDistance = Math.max(Math.max(leftResult.maxDistance, rightResult.maxDistance), leftResult.height + rightResult.height + 1);
        return new Result(maxDistance, height);
    }
}
