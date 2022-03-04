package com.wcn.algorithm.graph;

import com.wcn.algorithm.graph.base.Graph;
import com.wcn.algorithm.graph.base.GraphEdge;
import com.wcn.algorithm.graph.base.GraphNode;

import java.util.HashMap;
import java.util.Map;

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

    public static void main(String[] args) {
        int[][] array = new int[][]{{1,2,1},{1,3,7},{1,4,6},{2,3,2},{2,5,100},{3,4,2},{3,5,50},{4,5,4}};
        Graph graph = new Graph(array);
        Map<GraphNode, Integer> map = search(graph.nodes.get(1));//从1节点开始
        for(Map.Entry<GraphNode, Integer> entry:map.entrySet()){
            System.out.println("node:"+entry.getKey().value+" ("+entry.getValue()+")");
        }
    }

    public static Map<GraphNode, Integer> search(GraphNode firstNode){
        Map<GraphNode, Integer> result = new HashMap<>();

        //最小距离表
        Map<GraphNode, Integer> distanceMap = new HashMap<>();
        //先将源点到自己记录到距离表
        distanceMap.put(firstNode, 0);

        while(!distanceMap.isEmpty()){
            Map.Entry<GraphNode, Integer> minEntry = getMinGraphNode(distanceMap);
            for(GraphEdge edge: minEntry.getKey().edges){
                //一个节点距离表中已经放入的距离和（其它点的最小距离+两点之间的距离）比较大小
                Integer curMinDistance = distanceMap.get(edge.to);
                if(curMinDistance==null){
                    distanceMap.put(edge.to, minEntry.getValue()+edge.weight);
                }else{
                    distanceMap.put(edge.to, Math.min(curMinDistance, (minEntry.getValue()+edge.weight)));
                }
            }

            //每个节点处理一次，处理完了移除掉
            distanceMap.remove(minEntry.getKey());
            result.put(minEntry.getKey(), minEntry.getValue());
        }
        return result;
    }

    /**
     * 获取距离表中最小的节点
     * @param distanceMap
     * @return
     */
    private static Map.Entry<GraphNode, Integer> getMinGraphNode(Map<GraphNode, Integer> distanceMap){
        Integer minDistance = null;
        Map.Entry<GraphNode, Integer> ret = null;
        for(Map.Entry<GraphNode, Integer> entry: distanceMap.entrySet()){
            if(minDistance==null || entry.getValue()<minDistance){
                minDistance = entry.getValue();
                ret = entry;
            }
        }
        return ret;
    }
}
