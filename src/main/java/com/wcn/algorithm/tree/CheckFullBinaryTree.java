package com.wcn.algorithm.tree;

/**
 * 检查是否是满二叉树
 * 满二叉树：除了最后一层节点，所有节点都有左右两个孩子
 */
public class CheckFullBinaryTree {
    public static void main(String[] args) {
        Node node1 = new Node(1);
        System.out.println(check(node1));

        Node node2 = new Node(2);
        node1.left = node2;
        System.out.println(check(node1));

        Node node3 = new Node(3);
        node1.right = node3;
        System.out.println(check(node1));

        Node node21 = new Node(21);
        Node node22 = new Node(22);
        node2.left = node21;
        node2.right = node22;
        System.out.println(check(node1));

        Node node31 = new Node(31);
        Node node32 = new Node(32);
        node3.left = node31;
        node3.right = node32;
        System.out.println(check(node1));
    }
    static class Node{
        int value;
        Node left;
        Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static boolean check(Node root){
        Result result = process(root);
        //2^高度-1==节点数
        return (1<<result.height)-1==result.nodeNum;
    }

    private static Result process(Node node){
        if(node==null){
            return new Result(0, 0);
        }
        Result leftResult = process(node.left);
        Result rightResult = process(node.right);

        int nodeNum = leftResult.nodeNum + rightResult.nodeNum + 1;
        int height = Math.max(leftResult.height, rightResult.height) +1;
        return new Result(nodeNum, height);
    }

    static class Result{
        int nodeNum;//节点个数
        int height;//树的高数

        public Result(int nodeNum, int height) {
            this.nodeNum = nodeNum;
            this.height = height;
        }
    }
}
