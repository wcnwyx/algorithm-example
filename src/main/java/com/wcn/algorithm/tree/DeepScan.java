package com.wcn.algorithm.tree;

import java.util.Stack;

/**
 * 深度优先遍历
 */
public class DeepScan {
    public static void main(String[] args) {
        Node node0 = new Node("0");

        Node node0L = new Node("0L");
        Node node0R = new Node("0R");

        Node node0LL = new Node("0LL");
        Node node0LR = new Node("0LR");

        Node node0RL = new Node("0RL");
        Node node0RR = new Node("0RR");

        node0.left = node0L;
        node0.right = node0R;

        node0L.left = node0LL;
        node0L.right = node0LR;

        node0R.left = node0RL;
        node0R.right = node0RR;

        System.out.print("递归先序:");
        scan(node0, Type.before);
        System.out.println();

        System.out.print("递归中序:");
        scan(node0, Type.middle);
        System.out.println();

        System.out.print("递归后序:");
        scan(node0, Type.after);
        System.out.println();

        System.out.print("stack先序:");
        scanStackBefore(node0);

    }

    enum Type{
        before,
        middle,
        after;
    }

    static class Node{
        public String value;
        public Node left;
        public Node right;

        public Node(String value) {
            this.value = value;
        }
    }

    /**
     * 递归实现遍历
     * @param head
     * @param type
     */
    public static void scan(Node head, Type type){
        if(head==null){
            return ;
        }

        //所谓的什么序，就是根节点在什么位置处理

        if(type.equals(Type.before)){
            System.out.print(head.value+" ");//在这个位置处理就是先序 根-左-右
        }
        scan(head.left, type);
        if(type.equals(Type.middle)){
            System.out.print(head.value+" ");//在这个位置处理就是中序 左-根-右
        }
        scan(head.right, type);
        if(type.equals(Type.after)){
            System.out.print(head.value+" ");//在这个位置处理就是后序 左-右-根
        }
    }

    /**
     * 非递归实现 先序
     * @param head
     */
    public static void scanStackBefore(Node head){
        Stack<Node> stack = new Stack();
        stack.push(head);
        while(!stack.isEmpty()){
            Node node = stack.pop();
            System.out.print(node.value+" ");
            //先压入右再压入左
            if(node.right!=null){
                stack.push(node.right);
            }
            if(node.left!=null){
                stack.push(node.left);
            }
        }
    }
}
