package com.wcn.algorithm.linkedList;

/**
 * 双向链表节点
 */
public class NodeDouble<V> {
    private V value;
    private NodeDouble<V> pre;
    private NodeDouble<V> next;

    public NodeDouble(V value) {
        this.value = value;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public NodeDouble<V> getPre() {
        return pre;
    }

    public void setPre(NodeDouble<V> pre) {
        this.pre = pre;
    }

    public NodeDouble<V> getNext() {
        return next;
    }

    public void setNext(NodeDouble<V> next) {
        this.next = next;
    }
}
