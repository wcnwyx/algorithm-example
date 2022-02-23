package com.wcn.algorithm.linkedList;

/**
 * 单向链表节点类
 * @param <V>
 */
public class NodeSingle<V> {
    private V value;
    private NodeSingle<V> next;

    public NodeSingle(V value) {
        this.value = value;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public NodeSingle<V> getNext() {
        return next;
    }

    public void setNext(NodeSingle<V> next) {
        this.next = next;
    }
}
