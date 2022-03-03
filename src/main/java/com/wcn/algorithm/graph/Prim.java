package com.wcn.algorithm.graph;

import com.wcn.algorithm.graph.base.Graph;
import com.wcn.algorithm.graph.base.GraphEdge;
import com.wcn.algorithm.graph.base.GraphNode;

import java.util.*;

/**
 * 最小生成树Prim(普里姆算法 P算法)
 * 最小生成树参考Kruskal.java
 *
 */
public class Prim {

    public static void main(String[] args) {
        int[][] array = new int[][]{{1,2,1},{2,3,2},{3,4,3},{2,4,50},{4,1,100}};
        Graph graph = new Graph(array);
        List<GraphEdge> list = create(graph.nodes.get(1));//模拟从1节点处理
        for(GraphEdge edge:list){
            System.out.println(edge.from.value+"->"+edge.to.value+" ("+ edge.weight+")");
        }
    }

    /**
     * 整体思路：
     * 1. 随便从一个点出发，解锁该点和改点的所有边
     * 2. 从解锁的边集合里挑选一个最小权重的边。
     * 3. 如果该边两边的节点都已解锁，则忽略该边，继续找一个最小权重的边，知道有新的未解锁的点出现，则使用该边。
     * 4. 将新找到的边重复3的步骤，知道所有边处理完了即可。
     * @param nodeStart 随机一个开始处理的点
     * @return
     */
    public static List<GraphEdge> create(GraphNode nodeStart){
        HashSet<GraphNode> unLockNodes = new HashSet<>();
        HashSet<GraphEdge> unLockEdges = new HashSet<>();//标记所有解锁的边，防止队列中重复添加边
        PriorityQueue<GraphEdge> queue = new PriorityQueue<>(new Comparator<GraphEdge>() {
            @Override
            public int compare(GraphEdge o1, GraphEdge o2) {
                return o1.weight - o2.weight;
            }
        });
        List<GraphEdge> result = new ArrayList<>();
        unLockNodes.add(nodeStart);
        unLockEdges.addAll(nodeStart.edges);
        queue.addAll(nodeStart.edges);

        while(!queue.isEmpty()){
            GraphEdge edge = queue.poll();
            boolean useFlag = false;

            if(!unLockNodes.contains(edge.from)){
                //未解锁from点，将from点解锁
                unLockNodes.add(edge.from);
                for(GraphEdge edge1:edge.from.edges){
                    if(!unLockEdges.contains(edge1)){
                        //以前未解锁过该边，则解锁
                        unLockEdges.add(edge1);
                        queue.add(edge1);
                    }
                }
                useFlag = true;
            }
            if(!unLockNodes.contains(edge.to)){
                //未解锁to点，将to点解锁
                unLockNodes.add(edge.to);
                for(GraphEdge edge1:edge.to.edges){
                    if(!unLockEdges.contains(edge1)){
                        //以前未解锁过该边，则解锁
                        unLockEdges.add(edge1);
                        queue.add(edge1);
                    }
                }
                useFlag = true;
            }
            if(useFlag){
                result.add(edge);
            }
        }
        return result;
    }

}
