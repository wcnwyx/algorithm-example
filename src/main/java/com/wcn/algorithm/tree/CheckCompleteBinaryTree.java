package com.wcn.algorithm.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 判断是否是完全二叉树
 * 完全二叉树定义：
 * 一棵深度为k的有n个结点的二叉树，对树中的结点按从上至下、从左到右的顺序进行编号，
 * 如果编号为i（1≤i≤n）的结点与满二叉树中编号为i的结点在二叉树中的位置相同，则这棵二叉树称为完全二叉树。
 * 就是说完全二叉树可以比满二叉树少几个节点，但是少的节点都是满二叉树最后一个高度，最右边的节点。
 * 完全二叉树是正在变成成满二叉树
 */
public class CheckCompleteBinaryTree {
    public static void main(String[] args) {
        Node node1 = new Node(1);
        System.out.println(check(node1)+" "+checkLoop(node1));

        Node node2 = new Node(2);
        node1.right = node2;
        System.out.println(check(node1)+"  "+checkLoop(node1));

        node1.left = node2;
        System.out.println(check(node1)+"  "+checkLoop(node1));

        Node node3 = new Node(3);
        node1.right = node3;
        System.out.println(check(node1)+"  "+checkLoop(node1));
    }

    static class Node{
        int value;
        Node left;
        Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    /**
     * 宽度遍历实现
     * @param root
     * @return
     */
    public static boolean check(Node root){
        //利用树的宽度遍历，判断非空节点中间是否夹着空节点
        Queue<Node> queue = new LinkedList<>();
        boolean hasNull = false;
        queue.offer(root);
        while(!queue.isEmpty()){
            Node node = queue.poll();
            if(node!=null){
                if(hasNull){
                    //出现空节点之后，又有了非空节点
                    return false;
                }
                queue.offer(node.left);//不管是否null，都入队列
                queue.offer(node.right);
            }else{
                hasNull = true;
            }
        }
        return true;
    }

    /**
     * 递归实现
     * @param root
     * @return
     */
    public static boolean checkLoop(Node root){
        return process(root).isCBT;
    }

    public static Result process(Node node){
        if(node==null){
            return new Result(0, true, true);
        }
        Result leftResult = process(node.left);
        Result rightResult = process(node.right);

        int height = Math.max(leftResult.height, rightResult.height) + 1;
        boolean isFBT = false;
        boolean isCBT = false;
        if(leftResult.isFBT && rightResult.isFBT && leftResult.height== rightResult.height){
            //子树都是满二叉树，且高度一样，则该节点的树肯定是满二叉树
            isFBT = true;
            isCBT = true;
        }else if(leftResult.isFBT && rightResult.isCBT && leftResult.height==rightResult.height){
            //左为满二叉树，右为完全二叉树，且高度相等，则该节点的树为完全二叉树，但不是满二叉树
            isFBT = false;
            isCBT = true;
        }else if(leftResult.isCBT && rightResult.isFBT && leftResult.height - rightResult.height==1){
            //左为完全二叉树，右为满二叉树，左树比右树高度大1，则node为完全二叉树，但不是满二叉树
            isFBT = false;
            isCBT = true;
        }
        return new Result(height, isFBT, isCBT);
    }

    static class Result{
        int height;
        boolean isFBT;//是否是满二叉树
        boolean isCBT;//是否是完全二叉树

        public Result(int height, boolean isFBT, boolean isCBT) {
            this.height = height;
            this.isFBT = isFBT;
            this.isCBT = isCBT;
        }
    }
}
