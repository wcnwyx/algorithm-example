package com.wcn.algorithm.linkedList;

import java.util.HashSet;

/**
 * 给定一个单链表的头节点head，判断该链表是否为回文结构（是否又环）
 */
public class CheckCircle {
    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(7);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = node7;

        System.out.println("Hash无回文结构："+checkHash(node1));
        node7.next = node4;
        System.out.println("Hash有回文结构："+checkHash(node1));

        node7.next = null;
        System.out.println("FastSlowPoint无回文结构："+checkFastSlowPoint(node1));
        node7.next = node4;
        System.out.println("FastSlowPoint有回文结构："+checkFastSlowPoint(node1));
    }
    static class Node{
        public int value;
        public Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    /**
     * 采用hash表判断
     * @param head
     * @return
     */
    public static boolean checkHash(Node head){
        Node temp = head;
        HashSet<Node> set = new HashSet<>();
        while(temp!=null){
            if(set.contains(temp)){
                //set中已经有了该node
                return true;
            }
            set.add(temp);
            temp = temp.next;
        }
        return false;
    }

    /**
     * 快慢指针实现
     * @param head
     * @return
     */
    public static boolean checkFastSlowPoint(Node head){
        if(head==null || head.next==null){
            return false;
        }
        Node slow = head;
        Node fast = head.next;
        while(fast.next!=null && fast.next.next!=null){
            if(slow.equals(fast)){
                return true;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return false;
    }
}
