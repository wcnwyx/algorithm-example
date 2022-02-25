package com.wcn.algorithm.linkedList;

import java.util.HashMap;

/**
 * 单向链表，其节点内部多了一个random指针，该指针可以随便指向节点，可以空，也可以自己
 * 要求时间复杂度O(N)，额外空间复杂度O(1)，复制该链表
 */
public class CopyRandom {
    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        node1.next = node2;
        node1.random = node1;
        node2.next = node3;
        node3.random=node1;

        System.out.println("原始串");
        print(node1);
        Node copyHead = copyWithHashMap(node1);
        System.out.println("复制串");
        print(copyHead);
        Node copyHead1 = copy(node1);
        System.out.println("复制串");
        print(copyHead1);
    }

    public static void print(Node node){
        Node temp = node;
        while(temp!=null){
            System.out.print(temp.value+"("+(temp.random==null?"":temp.random.value)+") ");
            temp = temp.next;
        }
        System.out.println();
    }

    static class Node{
        public int value;
        public Node next;
        public Node random;//随机节点，可以为空，也可以为自己

        public Node(int value) {
            this.value = value;
        }
    }

    /**
     * 采用map实现，这种不满之额外空间复杂度O(1)，但是最简单
     * @param head
     * @return
     */
    public static Node copyWithHashMap(Node head){
        Node temp = head;
        HashMap<Node, Node> hashMap = new HashMap();
        while(temp!=null){
            Node nodeNew = new Node(temp.value);
            hashMap.put(temp, nodeNew);
            temp = temp.next;
        }

        temp = head;
        while(temp!=null){
            Node copyNode = hashMap.get(temp);
            copyNode.next = hashMap.get(temp.next);
            copyNode.random = hashMap.get(temp.random);
            temp = temp.next;
        }
        return hashMap.get(head);
    }

    /**
     * @param head
     * @return
     */
    public static Node copy(Node head){
        //将复制节点放到该节点和其next节点中间
        Node temp = head;
        while(temp!=null){
            Node copy = new Node(temp.value);
            copy.next = temp.next;
            temp.next = copy;

            temp = copy.next;
        }

        System.out.print("debug-------------------");
        print(head);

        //依次将node和copy都拿出来，那么copy.random 就是node.random.next,
        //因为node.random.next 就是 node.random的复制节点
        temp = head;
        Node tempCopy = temp.next;
        Node copyHead = head.next;
        while(temp!=null){
            if(temp.random!=null){
                tempCopy.random = temp.random.next;
            }
            temp = temp.next.next;

            // temp.next = tempCopy.next; 不能再次回复原链表，要不然后面的node找前面的copyNode就找不到了
            if(tempCopy.next!=null){
                //不是最后一个节点
                tempCopy.next = tempCopy.next.next;
            }

            tempCopy = tempCopy.next;
        }

        return copyHead;
    }
}
