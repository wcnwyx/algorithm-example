package com.wcn.algorithm.graph;

import com.wcn.algorithm.graph.base.Graph;
import com.wcn.algorithm.graph.base.GraphNode;

import java.util.HashSet;
import java.util.Stack;

/**
 * 图的深度优先遍历
 */
public class ScanDeep {
    public static void main(String[] args) {
        int[][] array = new int[][]{{1,2,0},{1,3,0},{2,3,0},{2,4,0},{3,5,0},{5,6,0},{6,7,0},{3,4,0}};
        Graph graph = new Graph(array);
        scan1(graph.nodes.get(1));
    }

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

    public static void scan1(GraphNode node){
        if(node==null){
            return ;
        }

        //栈中保存着当前节点所有的路径节点
        Stack<GraphNode> stack = new Stack<>();
        HashSet<GraphNode> hashSet = new HashSet<>();
        stack.push(node);
        hashSet.add(node);
        while(!stack.isEmpty()){
            GraphNode nodeTemp = stack.pop();
            System.out.println(nodeTemp.value);

            for(GraphNode nodeTempNext:nodeTemp.nexts){
                if(!hashSet.contains(nodeTempNext)){
                    stack.push(nodeTempNext);//再压入本节点
                    hashSet.add(nodeTempNext);
                }
            }
        }
    }
}
