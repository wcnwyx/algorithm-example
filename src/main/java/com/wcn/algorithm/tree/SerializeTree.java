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

        Queue<Integer> queue = serialize(node1);
        while(!queue.isEmpty()){
            Integer i = queue.poll();
            System.out.print(i+" ");
        }

        Node node = serializeFrom(serialize(node1));
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
    public static Queue<Integer> serialize(Node root){
        Queue<Integer> queue = new LinkedList<>();
        process(root, queue);
        return queue;
    }

    private static void process(Node node, Queue<Integer> queue){
        if(node==null){
            queue.add(null);
            return ;
        }
        queue.offer(node.value);

        process(node.left, queue);
        process(node.right, queue);
    }

    /**
     * 反序列化（先序）
     * @param queue
     * @return
     */
    public static Node serializeFrom(Queue<Integer> queue){
        Integer value = queue.poll();
        if(value==null){
            return null;
        }
        Node node = new Node(value);
        Node left = serializeFrom(queue);
        Node right = serializeFrom(queue);
        node.left = left;
        node.right = right;
        return node;
    }

}
