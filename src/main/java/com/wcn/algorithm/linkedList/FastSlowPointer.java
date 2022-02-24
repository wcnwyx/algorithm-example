package com.wcn.algorithm.linkedList;

/**
 * 快慢指针
 * 1。输入链表头节点，奇数长度返回中点，偶数长度返回上中点
 * 2。输入链表头节点，奇数长度返回中点，偶数长度返回上中点
 * 3。输入链表头节点，奇数长度返回中点前一个节点，偶数长度返回上中点前一个
 * 4。输入链表头节点，奇数长度返回中点前一个节点，偶数长度返回下中点前一个
 */
public class FastSlowPointer {
    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(7);
        Node node8 = new Node(8);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = node7;
        System.out.println("奇数个节点输出中点："+test1(node1));
        node7.next = node8;
        System.out.println("偶数个节点输出上中点"+test1(node1));

        node7.next = null;
        System.out.println("奇数个节点输出中点："+test2(node1));
        node7.next = node8;
        System.out.println("偶数个节点输出下中点"+test2(node1));

        node7.next = null;
        System.out.println("奇数个节点输出中点前一个："+test3(node1));
        node7.next = node8;
        System.out.println("偶数个节点输出上中点前一个"+test3(node1));

        node7.next = null;
        System.out.println("奇数个节点输出中点前一个："+test4(node1));
        node7.next = node8;
        System.out.println("偶数个节点输出下中点前一个"+test4(node1));
    }

    static class Node{
        public int value;
        public Node next;

        public Node(int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    '}';
        }
    }

    /**
     * 输入链表头节点，奇数长度返回中点，偶数长度返回上中点
     * 1 2 3 4 5 6 7 返回4
     * 1 2 3 4 5 6 7 8 返回4
     * @param head
     * @return
     */
    public static Node test1(Node head){
        if(head==null || head.next==null || head.next.next==null){
            return null;
        }
        Node slow = head.next;
        Node fast = head.next.next;
        while(fast.next!=null && fast.next.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    /**
     * 输入链表头节点，奇数长度返回中点，偶数长度返回下中点
     * 1 2 3 4 5 6 7 返回4
     * 1 2 3 4 5 6 7 8 返回5
     * @param head
     * @return
     */
    public static Node test2(Node head){
        if(head==null || head.next==null || head.next.next==null){
            return null;
        }
        Node slow = head.next;
        Node fast = head.next;
        while(fast.next!=null && fast.next.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    /**
     * 输入链表头节点，奇数长度返回中点前一个节点，偶数长度返回上中点前一个
     * 1 2 3 4 5 6 7 返回3
     * 1 2 3 4 5 6 7 8 返回3
     * @param head
     * @return
     */
    public static Node test3(Node head){
        if(head==null || head.next==null || head.next.next==null){
            return null;
        }
        Node slow = head;
        Node fast = head.next.next;
        while(fast.next!=null && fast.next.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    /**
     * 输入链表头节点，奇数长度返回中点前一个节点，偶数长度返回下中点前一个
     * 1 2 3 4 5 6 7 返回3
     * 1 2 3 4 5 6 7 8 返回4
     * @param head
     * @return
     */
    public static Node test4(Node head){
        if(head==null || head.next==null || head.next.next==null){
            return null;
        }
        Node slow = head;
        Node fast = head.next;
        while(fast.next!=null && fast.next.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}
