package com.wcn.algorithm.graph;

import com.wcn.algorithm.graph.base.Graph;
import com.wcn.algorithm.graph.base.GraphNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 图的拓扑排序
 * 1. 在图中找到所有入度为0的点输出
 * 2. 把所有入度为0的点在图中删掉，继续找入度为0的点输出，周而复始
 * 3. 图中所有的点都被删除后，依次输出的顺序就是拓扑排序
 * 要求：有向图且其中没有环
 * 应用：事件安排、编译顺序
 */
public class TopologySort {

    public static void main(String[] args) {
        int[][] array = new int[][]{{1,2,3},{1,3,0},{2,3,0},{3,4,0},{3,6,0},{4,5,0},{5,6,0},{1,7,0},{3,7,0}};
        Graph graph = new Graph(array);
        sort(graph);
    }

    public static void sort(Graph graph){
        Queue<GraphNode> outputQueue = new LinkedList<>();//所有入度为0的节点放入queue中

        while(!graph.nodes.isEmpty()){
            List<GraphNode> nodeDeleteList = new ArrayList<>();
            for(GraphNode node:graph.nodes.values()){
                if(node.in==0){
                    outputQueue.offer(node);
                    nodeDeleteList.add(node);
                }
            }
            for(GraphNode e:nodeDeleteList){
                graph.deleteNode(e);
            }
        }

        while(!outputQueue.isEmpty()){
            GraphNode e = outputQueue.poll();
            System.out.print(e.value+" ");
        }
    }
}
