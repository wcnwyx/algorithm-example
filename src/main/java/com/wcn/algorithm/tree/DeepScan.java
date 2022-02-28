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

        System.out.print("  递归先序:");
        scan(node0, Type.before);
        System.out.println();

        System.out.print("  递归中序:");
        scan(node0, Type.middle);
        System.out.println();

        System.out.print("  递归后序:");
        scan(node0, Type.after);
        System.out.println();

        System.out.print("stack先序:");
        scanStackBefore(node0);
        System.out.println();

        System.out.print("stack中序:");
        scanStackMiddle(node0);
        System.out.println();

        System.out.print("stack后序:");
        scanStackAfter(node0);
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
     * @param root
     * @param type
     */
    public static void scan(Node root, Type type){
        if(root==null){
            return ;
        }

        //所谓的什么序，就是根节点在什么位置处理

        if(type.equals(Type.before)){
            System.out.print(root.value+" ");//在这个位置处理就是先序 根-左-右
        }
        scan(root.left, type);
        if(type.equals(Type.middle)){
            System.out.print(root.value+" ");//在这个位置处理就是中序 左-根-右
        }
        scan(root.right, type);
        if(type.equals(Type.after)){
            System.out.print(root.value+" ");//在这个位置处理就是后序 左-右-根
        }
    }

    /**
     * 非递归实现 先序
     * 1. 弹出再处理（打印）
     * 2. 先压入右子节点，再压入左子节点
     * @param root
     */
    public static void scanStackBefore(Node root){
        Stack<Node> stack = new Stack();
        stack.push(root);
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

    /**
     * 非递归实现后续
     * 1. 弹出再处理（压入另一个栈）
     * 2. 先压入左子节点，再压入右子节点
     * 3. 再从第二个栈中弹出处理即可
     * @param root
     */
    public static void scanStackAfter(Node root){
        Stack<Node> stack1 = new Stack<>();
        Stack<Node> stack2 = new Stack<>();
        stack1.push(root);
        while(!stack1.isEmpty()){
            Node node = stack1.pop();
            stack2.push(node);
            //先压入左再压入右
            if(node.left!=null){
                stack1.push(node.left);
            }
            if(node.right!=null){
                stack1.push(node.right);
            }
        }
        while(!stack2.isEmpty()){
            System.out.print(stack2.pop().value+" ");
        }
    }

    /**
     * 中序遍历
     * 1. 根节点的整条左边界依次入栈
     * 2. 弹出一个节点并处理，然后再将该节点的右子节点当作跟再循环1步骤
     * @param root
     */
    public static void scanStackMiddle(Node root){
        Stack<Node> stack = new Stack<>();
        while(!stack.isEmpty() || root!=null){
            if(root!=null){
                stack.push(root);
                root = root.left;
            }else{
                root = stack.pop();
                System.out.print(root.value+" ");
                root = root.right;
            }
        }
    }
}
