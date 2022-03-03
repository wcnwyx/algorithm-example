package com.wcn.algorithm.graph.base;

import java.util.HashMap;
import java.util.HashSet;

/**
 * 图的封装结构
 */
public class Graph {
    //图中所有的点
    public HashMap<Integer, GraphNode> nodes = new HashMap<>();
    //图中所有的边
    public HashSet<GraphEdge> edges = new HashSet<>();

    /**
     * [from节点编号，to节点编号，权重]
     * @param array
     */
    public Graph(int[][] array){
        if(array==null){
            return ;
        }

        for(int[] e:array){
            int from = e[0];
            int to = e[1];
            int weight = e[2];

            GraphNode fromNode = nodes.get(from);
            GraphNode toNode = nodes.get(to);
            if(fromNode==null){
                fromNode = new GraphNode(from);
                nodes.put(from, fromNode);
            }
            if(toNode==null){
                toNode = new GraphNode(to);
                nodes.put(to, toNode);
            }
            GraphEdge edge = new GraphEdge(weight, fromNode, toNode);
            edges.add(edge);

            fromNode.out++;
            fromNode.nexts.add(toNode);
            fromNode.edges.add(edge);

            toNode.in++;
        }
    }
}
