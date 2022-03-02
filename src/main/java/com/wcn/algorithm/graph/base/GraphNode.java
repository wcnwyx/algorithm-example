package com.wcn.algorithm.graph.base;

import java.util.ArrayList;

public class GraphNode {
    public String value;//节点编号或者值
    public int in;//入度
    public int out;//出度
    public ArrayList<GraphNode> nexts;//直接邻居（出度的第一个节点）
    public ArrayList<GraphNode> edges;//从自己出发的边

    public GraphNode(String value) {
        this.value = value;
        in = 0;
        out = 0;
        nexts = new ArrayList<>();
        edges = new ArrayList<>();
    }
}
