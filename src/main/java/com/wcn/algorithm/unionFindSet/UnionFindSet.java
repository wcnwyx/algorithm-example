package com.wcn.algorithm.unionFindSet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 并查集
 */
public class UnionFindSet<V> {
    static class Node<V>{
        public V value;

        public Node(V value) {
            this.value = value;
        }
    }

    //每个具体的值V对应的节点Node
    private Map<V, Node<V>> nodeMap = new HashMap<>();
    //每个节点对应的父节点
    private Map<Node<V>, Node<V>> parentMap = new HashMap<>();
    //每个头节点下面有几个子节点（包括自己）
    private Map<Node<V>, Integer> sizeMap = new HashMap<>();

    //初始化
    public UnionFindSet(List<V> list) {
        for(V v:list){
            Node<V> node = new Node<>(v);
            nodeMap.put(v, node);
            sizeMap.put(node, 1);
        }
    }

    /**
     * 查找头节点
     * @param node
     * @return
     */
    private Node<V> findParent(Node<V> node){
        Node<V> parent = node;
        List<Node<V>> list = new ArrayList<>();
        while(parentMap.containsKey(parent)){
            list.add(parent);
            parent = parentMap.get(parent);
        }

        if(list.size()>1){
            //如果整条链过长，将所有节点的头节点直接执行头，省去每次链表多个节点依次往上查询
            for(int i=0;i<list.size()-1;i++){
                parentMap.put(list.get(i), parent);
            }
        }
        return parent;
    }

    /**
     * 两个值是否在同一集合中
     * @param v1
     * @param v2
     * @return
     */
    public boolean isSameSet(V v1, V v2){
        Node<V> node1 = nodeMap.get(v1);
        if(node1==null){
            return false;
        }
        Node<V> node2 = nodeMap.get(v2);
        if(node2==null){
            return false;
        }
        Node<V> node1Parent = findParent(node1);
        Node<V> node2Parent = findParent(node2);
        return node1Parent.equals(node2Parent);
    }

    /**
     * 合并另个节点所在的集合
     * @param v1
     * @param v2
     * @return
     */
    public boolean union(V v1, V v2){
        Node<V> node1 = nodeMap.get(v1);
        if(node1==null){
            return false;
        }
        Node<V> node2 = nodeMap.get(v2);
        if(node2==null){
            return false;
        }
        Node<V> node1Parent = findParent(node1);
        Node<V> node2Parent = findParent(node2);
        int node1ParentSize = sizeMap.get(node1Parent);
        int node2ParentSize = sizeMap.get(node2Parent);
        if(node1ParentSize>=node2ParentSize){
            //node1集合个数大于node2集合个数，将node2集合合并到node1集合
            parentMap.put(node2Parent, node1Parent);
            sizeMap.remove(node2Parent);
            sizeMap.put(node1Parent, node1ParentSize+node2ParentSize);
        }else{
            parentMap.put(node1Parent, node2Parent);
            sizeMap.remove(node1Parent);
            sizeMap.put(node2Parent, node1ParentSize+node2ParentSize);
        }
        return true;
    }

    /**
     * 返回集合数量
     * @return
     */
    public int getSetNum(){
        return sizeMap.size();
    }
}
