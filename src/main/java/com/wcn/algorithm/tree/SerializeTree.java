package com.wcn.algorithm.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 树的序列化和反序列化
 */
public class SerializeTree {

    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        node1.left = node2;
        node2.right = node3;

        Queue<Integer> queue = serializeBefore(node1);
        while(!queue.isEmpty()){
            Integer i = queue.poll();
            System.out.print(i+" ");
        }

        Node node = serializeFromBefore(serializeBefore(node1));
        System.out.println();

        queue = serializeWide(node1);
        while(!queue.isEmpty()){
            Integer i = queue.poll();
            System.out.print(i+" ");
        }

        node = serializeFromWide(serializeWide(node1));
        System.out.println();
    }

    static class Node{
        public Integer value;
        public Node left;
        public Node right;

        public Node(Integer value) {
            this.value = value;
        }
    }

    /**
     * 将树序列化(先序)
     * @return
     */
    public static Queue<Integer> serializeBefore(Node root){
        Queue<Integer> queue = new LinkedList<>();
        processBefore(root, queue);
        return queue;
    }

    private static void processBefore(Node node, Queue<Integer> queue){
        if(node==null){
            queue.add(null);
            return ;
        }
        queue.offer(node.value);

        processBefore(node.left, queue);
        processBefore(node.right, queue);
    }

    /**
     * 反序列化（先序）
     * @param queue
     * @return
     */
    public static Node serializeFromBefore(Queue<Integer> queue){
        Integer value = queue.poll();
        if(value==null){
            return null;
        }
        Node node = new Node(value);
        Node left = serializeFromBefore(queue);
        Node right = serializeFromBefore(queue);
        node.left = left;
        node.right = right;
        return node;
    }

    /**
     * 序列化（宽度优先）
     * @param root
     * @return
     */
    public static Queue<Integer> serializeWide(Node root){
        Queue<Integer> queue = new LinkedList<>();
        Queue<Node> nodeQueue = new LinkedList<>();
        nodeQueue.offer(root);
        queue.offer(root.value);
        while(!nodeQueue.isEmpty()){
            Node node = nodeQueue.poll();
            if(node.left!=null){
                nodeQueue.offer(node.left);
                queue.offer(node.left.value);
            }else{
                queue.offer(null);
            }

            if(node.right!=null){
                nodeQueue.offer(node.right);
                queue.offer(node.right.value);
            }else{
                queue.offer(null);
            }
        }
        return queue;
    }

    /**
     * 反序列化（宽度优先）
     * @param queue
     * @return
     */
    public static Node serializeFromWide(Queue<Integer> queue){
        Queue<Node> nodeQueue = new LinkedList<>();
        Node root = new Node(queue.poll());
        nodeQueue.offer(root);
        Node nodeTemp = null;
        while(!nodeQueue.isEmpty()){
            nodeTemp = nodeQueue.poll();

            Integer valueLeft = queue.poll();
            if(valueLeft!=null){
                nodeTemp.left = new Node(valueLeft);
                nodeQueue.offer(nodeTemp.left);
            }

            Integer valueRight = queue.poll();
            if(valueRight!=null){
                nodeTemp.right = new Node(valueRight);
                nodeQueue.offer(nodeTemp.right);
            }
        }
        return root;
    }
}
