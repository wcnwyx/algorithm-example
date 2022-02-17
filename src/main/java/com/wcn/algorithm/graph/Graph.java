package com.wcn.algorithm.graph;

import java.util.List;

public class Graph {
    static class Node<V>{
        private V value;
        private int in;
        private int out;
        private List<Node> next;
    }

    static class Edge{
        private int weight;
        private Node from;
        private Node to;
    }
}
