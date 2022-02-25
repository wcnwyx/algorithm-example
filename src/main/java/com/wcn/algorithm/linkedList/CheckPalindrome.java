package com.wcn.algorithm.linkedList;

/**
 * 检查单向链表是否是回文结构（是否是对称的）
 * 比如这两种都是回文结构： 1-2-3-2-1   1-2-3-3-2-1
 */
public class CheckPalindrome {
    public static void main(String[] args) {
        //测试基数个节点
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node22 = new Node(2);
        Node node11 = new Node(3);
        node1.next = node2;
        node2.next = node3;
        node3.next = node22;
        node22.next = node11;
        System.out.println("(奇数节点个数）不是回文结构:"+checkFastSlowPointer(node1));

        node11.value = 1;
        System.out.println("(奇数节点个数）是回文结构:"+checkFastSlowPointer(node1));


        //测试偶数个节点
        node1 = new Node(1);
        node2 = new Node(2);
        node3 = new Node(3);
        Node node33 = new Node(3);
        node22 = new Node(2);
        node11 = new Node(3);
        node1.next = node2;
        node2.next = node3;
        node3.next = node33;
        node33.next = node22;
        node22.next = node11;
        System.out.println("(偶数节点个数）不是回文结构:"+checkFastSlowPointer(node1));

        node11.value = 1;
        System.out.println("(偶数节点个数）是回文结构:"+checkFastSlowPointer(node1));

    }

    static class Node{
        public int value;
        public Node next;

        public Node(int value){
            this.value = value;
        }
    }

    /**
     * 采用快慢指针方案 时间复杂度O(N)，额外空间复杂度O(1)
     * 基数个节点慢指针找到中间节点，偶数个节点慢指针找到上中点
     * @param head
     * @return
     */
    public static boolean checkFastSlowPointer(Node head){
        if(head==null || head.next==null){
            return true;
        }else if(head.next.next==null){
            return head.value==head.next.value;
        }
        Node slow = head;
        Node fast = head;

        Node result = process(fast, slow);
        return result!=null;
    }

    /**
     * 采用递归方式（其实是利用了java虚拟机栈的栈结构）
     * @param fast
     * @param slow
     * @return null 表示已经不一致了，一致的情况下降slow继续往下走的节点返回
     */
    public static Node process(Node fast, Node slow){
        if(fast.next==null){
            //奇数个节点，慢指针停留在中点，不比较
            return slow.next;
        }else if (fast.next!=null && fast.next.next==null){
            //偶数个节点，慢指针停留在了上中点，上中点和下中点直接在此比较，然后将下中点的下一个返回
            if(slow.value==slow.next.value){
                return slow.next.next;
            }else{
                return null;
            }
        }else{
            Node temp = process(fast.next.next, slow.next);
            if(temp==null){
                return null;
            }else{
                if(slow.value==temp.value){
                    //目前比较一致
                    if(temp.next==null){
                        //链表循环完了,返回一个node表示是回文结构
                        return new Node(0);
                    }else{
                        //没处理完，继续退到上一层处理
                        return temp.next;
                    }
                }else{
                    return null;
                }
            }
        }
    }
}
