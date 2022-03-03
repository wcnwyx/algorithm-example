package com.wcn.algorithm.graph;

import com.wcn.algorithm.graph.base.Graph;
import com.wcn.algorithm.graph.base.GraphEdge;
import com.wcn.algorithm.graph.base.GraphNode;
import com.wcn.algorithm.unionFindSet.UnionFindSet;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 最小生成树之Kruskal(克鲁斯卡尔算法 K算法)
 * 1. 总是从权值最小的边开始考虑，一次考虑权值依次变大的边
 * 2. 当前的边要么进入最小生成树的集合，要么丢弃
 * 3. 如果当前的边进入最小生成树的集合中不会形成环，就要当前边，否则不要
 * 4. 考察完所有的边之后，最小生成树的集合就得到了
 * 最小生成树：保持整个图的连通性，边的权重最小
 *
 */
public class Kruskal {

    public static void main(String[] args) {
        int[][] array = new int[][]{{1,2,1},{2,3,2},{3,4,3},{2,4,50},{4,1,100}};
        Graph graph = new Graph(array);
        List<GraphEdge> list = create(graph);
        for(GraphEdge edge:list){
            System.out.println(edge.from.value+"->"+edge.to.value+" ("+ edge.weight+")");
        }
    }

    /**
     * 利用并查集来处理
     * 1. 将所有边排序，权重从小到大
     * 2. 所有节点初始化并查集里
     * 3. 查一次处理边，两边的点不在一个集合就合并，在一个集合了就忽略
     * @param graph
     */
    public static List<GraphEdge> create(Graph graph){
        //将所有的边按照权重排序放入小根堆
        PriorityQueue<GraphEdge> queue = new PriorityQueue<>(new Comparator<GraphEdge>() {
            @Override
            public int compare(GraphEdge o1, GraphEdge o2) {
                return o1.weight - o2.weight;
            }
        });
        queue.addAll(graph.edges);

        //将节点生成并查集
        UnionFindSet<GraphNode> unionFindSet = new UnionFindSet(graph.nodes.values());
        List<GraphEdge> result = new ArrayList<>();

        //从边的队列里一次按照权重从小到大获取
        while(!queue.isEmpty()){
            GraphEdge edge = queue.poll();
            //判断边的两头节点是否在一个结合，不在就合并，依次将所有点合并到一个集合就完事了
            if(!unionFindSet.isSameSet(edge.from, edge.to)){
                unionFindSet.union(edge.from, edge.to);
                result.add(edge);
            }
        }

        return result;
    }
}
