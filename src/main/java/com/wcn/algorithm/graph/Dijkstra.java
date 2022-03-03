package com.wcn.algorithm.graph;

import com.wcn.algorithm.graph.base.GraphNode;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

/**
 * 迪杰斯特拉算法(Dijkstra)是由荷兰计算机科学家狄克斯特拉于1959年提出的，因此又叫狄克斯特拉算法。
 * 是从一个顶点到其余各顶点的最短路径算法，解决的是有权图中最短路径问题。
 * 迪杰斯特拉算法主要特点是从起始点开始，采用贪心算法的策略，每次遍历到始点距离最近且未访问过的顶点的邻接节点，
 * 直到扩展到终点为止。
 * 1. 必须从一个给定的源点
 * 2. 生成一个远点到各个点的最小距离表，一开始只有一条记录，即原点到自己的最小距离为0，原点到其它所有点的最小距离都是正无穷大。
 * 3. 从距离表里拿出没拿过的最小记录，通过这个点出发的边，更新远点到各个点的最小距离表，不断重复这一步
 * 4. 源点到所有的点记录如果都被拿过一遍，过程停止，最小距离表就得到了
 */
public class Dijkstra {
    public static Map<GraphNode, Integer> search(GraphNode firstNode){
        Map<GraphNode, Integer> result = new HashMap<>();

        //最小距离表，采用了TreeMap直接排序，好获取最小距离的节点信息
        TreeMap<CandidateNode, Integer> distanceMap = new TreeMap<>();
        //先将源点到自己记录到距离表
        distanceMap.put(new CandidateNode(), 0);


        return result;
    }

    static class CandidateNode implements Comparable<CandidateNode>{
        GraphNode node;//图中的节点
        int minDistance;//起点到该节点的最小距离（权重）

        @Override
        public int compareTo(CandidateNode o) {
            return minDistance - o.minDistance;
        }
    }
}
