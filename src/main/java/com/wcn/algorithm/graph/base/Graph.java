package com.wcn.algorithm.graph.base;

import java.util.HashMap;
import java.util.HashSet;

/**
 * 图的封装结构
 */
public class Graph {
    //图中所有的点
    public HashMap<String, GraphNode> nodes = new HashMap<>();
    //图中所有的边
    public HashSet<GraphEdge> edges = new HashSet<>();

}
