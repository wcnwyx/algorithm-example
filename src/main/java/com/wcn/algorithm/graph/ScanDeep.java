package com.wcn.algorithm.graph;

import com.wcn.algorithm.graph.base.GraphNode;

import java.util.HashSet;
import java.util.Stack;

/**
 * 图的深度优先遍历
 */
public class ScanDeep {
    public static void scan(GraphNode node){
        if(node==null){
            return ;
        }

        //栈中保存着当前节点所有的路径节点
        Stack<GraphNode> stack = new Stack<>();
        HashSet<GraphNode> hashSet = new HashSet<>();
        stack.push(node);
        hashSet.add(node);
        System.out.println(node.value);
        while(!stack.isEmpty()){
            GraphNode nodeTemp = stack.pop();
            System.out.println(nodeTemp.value);

            for(GraphNode nodeTempNext:nodeTemp.nexts){
                if(!hashSet.contains(nodeTempNext)){
                    stack.push(nodeTemp);//先再次压入父节点
                    stack.push(nodeTempNext);//再压入本节点
                    hashSet.add(nodeTempNext);
                    System.out.println(nodeTempNext.value);

                    break;//只压入一个子节点就跳出
                }
            }
        }
    }
}
