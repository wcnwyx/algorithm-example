package com.wcn.algorithm.graph;

import com.wcn.algorithm.graph.base.Graph;
import com.wcn.algorithm.graph.base.GraphNode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 图的宽度优先遍历
 */
public class ScanWide {

    public static void scan(GraphNode node){
        if(node==null){
            return ;
        }
        Queue<GraphNode> queue = new LinkedList<>();
        HashSet<GraphNode> hashSet = new HashSet<>();
        queue.add(node);
        hashSet.add(node);
        while(!queue.isEmpty()){
            GraphNode nodeTemp = queue.poll();
            System.out.println(nodeTemp.value);

            for(GraphNode e: nodeTemp.nexts){
                if(!hashSet.contains(e)){
                    //该节点没处理过才加入队列处理，防止环形结构
                    queue.add(e);
                    hashSet.add(e);
                }
            }
        }
    }
}
