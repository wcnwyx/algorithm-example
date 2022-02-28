package com.wcn.algorithm.tree;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * 宽度遍历，一层一层的遍历
 */
public class WideScan {
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

        scan(node0);
        int wideMax = wideMax(node0);
        System.out.println("\r\nwideMax="+wideMax);
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
     * 宽度遍历
     * 1.利用队列先进先出
     * 2. head先入队，poll一个节点就处理（打印），有左子节点则入队，有右子节点则入队
     * @param root
     */
    public static void scan(Node root){
        LinkedBlockingQueue<Node> queue = new LinkedBlockingQueue();
        queue.offer(root);
        while(!queue.isEmpty()){
            Node node = queue.poll();
            System.out.print(node.value+" ");
            if(node.left!=null){
                queue.offer(node.left);
            }
            if(node.right!=null){
                queue.offer(node.right);
            }
        }
    }

    /**
     * 计算树的最多节点那一层的节点数
     * @param root
     */
    public static int wideMax(Node root){
        LinkedBlockingQueue<Node> queue = new LinkedBlockingQueue();
        queue.offer(root);
        int max = 1;
        int curLevelNodeNum = 1;
        int curLevelNodeIndex = 0;
        int nextLevelNodeNum = 0;//下一层个数
        while(!queue.isEmpty()){
            Node node = queue.poll();
            curLevelNodeIndex++;
            if(node.left!=null){
                nextLevelNodeNum ++;//下一层节点数增加
                queue.offer(node.left);
            }
            if(node.right!=null){
                nextLevelNodeNum ++;//下一层节点数增加
                queue.offer(node.right);
            }
            if(curLevelNodeIndex==curLevelNodeNum){
                //本层已处理完
                if(nextLevelNodeNum>max){
                    max = nextLevelNodeNum;
                }
                curLevelNodeNum = nextLevelNodeNum;
                curLevelNodeIndex = 0;
                nextLevelNodeNum = 0;
            }
        }
        return max;
    }
}
