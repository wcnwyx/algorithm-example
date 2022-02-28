package com.wcn.algorithm.tree;

/**
 * 二叉树中，节点保存了父节点。
 * 给定树中任何一个节点，找到该节点中序遍历的后一个节点
 */
public class FindNextNode {
    static class Node{
        public int value;
        public Node left;
        public Node right;
        public Node parent;
    }

    public static Node find(Node node){
        if(node.right!=null){
            //有右子节点，则下一个节点就是其右子树的最左侧节点
            Node nodeTemp = node.right;
            while(nodeTemp.left!=null){
                nodeTemp = nodeTemp.left;
            }
            return nodeTemp;
        }else{
            //不是父节点的左子节点，则往上一直找到是其父节点的左子节点的节点
            //整棵树的最右节点是没有下个节点的。当node到root时，root.parent是null
            while(node.parent!=null && node.equals(node.parent.right)){
                node = node.parent;
            }
            return node.parent;
        }
    }
}
