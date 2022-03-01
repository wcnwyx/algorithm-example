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
        System.out.println(check(node1));

        Node node2 = new Node(2);
        node1.right = node2;
        System.out.println(check(node1));

        node1.left = node2;
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
}
