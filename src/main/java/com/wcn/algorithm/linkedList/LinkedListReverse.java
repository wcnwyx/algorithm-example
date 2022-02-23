package com.wcn.algorithm.linkedList;

/**
 * 链表反转（单和双）
 */
public class LinkedListReverse {

    public static void main(String[] args) {
        LinkedListReverse linkedListReverse = new LinkedListReverse();
        NodeSingle<Integer> nodeSingle1 = new NodeSingle<>(1);
        NodeSingle<Integer> nodeSingle2 = new NodeSingle<>(2);
        NodeSingle<Integer> nodeSingle3 = new NodeSingle<>(3);
        NodeSingle<Integer> nodeSingle4 = new NodeSingle<>(4);
        nodeSingle1.setNext(nodeSingle2);
        nodeSingle2.setNext(nodeSingle3);
        nodeSingle3.setNext(nodeSingle4);
        NodeSingle nodeSingleResult = linkedListReverse.reverseSingle(nodeSingle1);
        while(nodeSingleResult!=null){
            System.out.println(nodeSingleResult.getValue());
            nodeSingleResult = nodeSingleResult.getNext();
        }


        NodeDouble<Integer> nodeDouble1 = new NodeDouble<>(11);
        NodeDouble<Integer> nodeDouble2 = new NodeDouble<>(22);
        NodeDouble<Integer> nodeDouble3 = new NodeDouble<>(33);
        NodeDouble<Integer> nodeDouble4 = new NodeDouble<>(44);
        nodeDouble1.setNext(nodeDouble2);
        nodeDouble2.setNext(nodeDouble3);
        nodeDouble3.setNext(nodeDouble4);
        nodeDouble4.setPre(nodeDouble3);
        nodeDouble3.setPre(nodeDouble2);
        nodeDouble2.setPre(nodeDouble1);
        NodeDouble nodeDoubleResult = linkedListReverse.reverseDouble(nodeDouble1);
        while(nodeDoubleResult!=null){
            System.out.println(nodeDoubleResult.getValue());
            nodeDoubleResult = nodeDoubleResult.getNext();
        }
    }

    /**
     * 单项链表反转
     * @param node
     * @return
     */
    public NodeSingle reverseSingle(NodeSingle node){
        NodeSingle next = null;
        NodeSingle pre = null;
        while(node!=null){
            next = node.getNext();
            node.setNext(pre);
            pre = node;
            node = next;
        }
        return pre;
    }

    /**
     * 双向链表反转
     * @param node
     * @return
     */
    public NodeDouble reverseDouble(NodeDouble node){
        NodeDouble next = null;
        NodeDouble pre = null;
        while(node!=null){
            pre = node.getPre();
            next = node.getNext();
            node.setNext(pre);
            node.setPre(next);

            node = next;
        }
        return pre.getPre();
    }
}
