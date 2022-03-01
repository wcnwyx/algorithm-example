package com.wcn.algorithm.tree;

/**
 * 二叉树，给定任意两个节点，找到最低的公共祖先
 */
public class LowestParent {
    public static void main(String[] args) {
        Node node1 = new Node(1);
        System.out.println("1 "+ getLoop(node1, node1, node1).value);

        Node node2 = new Node(2);
        Node node3 = new Node(3);
        node1.left = node2;
        node1.right = node3;
        System.out.println("1 "+ getLoop(node1, node2, node3).value);

        Node node21 = new Node(21);
        Node node22 = new Node(22);
        node2.left = node21;
        node2.right = node22;
        System.out.println("2 "+ getLoop(node1, node21, node22).value);
        System.out.println("2 "+ getLoop(node1, node2, node22).value);
        System.out.println("1 "+ getLoop(node1, node3, node22).value);
        System.out.println("1 "+ getLoop(node1, node1, node22).value);
    }

    static class Node{
        int value;
        Node left;
        Node right;

        public Node(int value) {
            this.value = value;
        }
    }


    public static Node get(Node root, Node node1, Node node2){
        /**
         * 1:从root遍历，构建map<node, parentNode>
         * 2:将node1及其所有父节点放到HashSet中
         * 3：循环node2及其所有父节点，从2步骤中的HashSet中找是否有，第一个找到的就是最低公共祖先
         */
        return null;
    }

    /**
     * 递归实现
     * @param root
     * @param node1
     * @param node2
     * @return
     */
    public static Node getLoop(Node root, Node node1, Node node2){
        Result result = process(root, node1, node2);
        return result.ret;
    }

    /**
     * 递归实现，每个节点向其孩子节点要Result中的信息
     * @param node 当前检查节点
     * @param node1
     * @param node2
     * @return
     */
    public static Result process(Node node, Node node1, Node node2){
        if(node==null){
            return new Result(null, false, false);
        }
        Result leftResult = process(node.left, node1, node2);
        Result rightResult = process(node.right, node1, node2);

        Node ret = null;
        boolean hasNode1 = leftResult.hasNode1 || rightResult.hasNode1;
        boolean hasNode2 = leftResult.hasNode2 || rightResult.hasNode2;
        if(leftResult.ret!=null){
            ret = leftResult.ret;//左子节点已经找到了
        }else if(rightResult.ret!=null){
            ret = rightResult.ret;//右子节点已经找到了
        }else if(leftResult.hasNode1 && rightResult.hasNode2){
            //左子树包含node1，并且右子树包含node2，则该节点就是最低公共祖先
            ret = node;
        }else if(leftResult.hasNode2 && rightResult.hasNode1){
            //左子树包含node2，并且右子树包含node1，则该节点就是最低公共祖先
            ret = node;
        }else{
            if(node.equals(node1)){
                hasNode1 = true;
            }
            if(node.equals(node2)){
                hasNode2 = true;
            }
            if(hasNode1 && hasNode2){
                ret = node;
            }
        }
        return new Result(ret, hasNode1, hasNode2);
    }

    static class Result{
        Node ret;//最低公共祖先
        boolean hasNode1;//是否包含节点1
        boolean hasNode2;//是否包含节点2

        public Result(Node ret, boolean hasNode1, boolean hasNode2) {
            this.ret = ret;
            this.hasNode1 = hasNode1;
            this.hasNode2 = hasNode2;
        }
    }
}
