package com.wcn.algorithm.tree;

import java.util.HashMap;

public class PrefixTree {
    static class Node{
        public int pass;//从此经过的次数
        public int end;//停留在此处的次数
        public HashMap<Integer, Node> nexts;

        public Node() {
            this.pass = 0;
            this.end = 0;
            nexts = new HashMap<>();
        }

    }

    private Node root;//树根节点
    public PrefixTree(){
        root = new Node();
    }

    public void add(String str){
        char[] chars = str.toCharArray();
        Node node = root;
        for(char c:chars){
            int path = (int)c;
            Node nextNode = node.nexts.get(path);
            if(nextNode==null) {
                //该字符还没有创建路径
                nextNode = new Node();
                node.nexts.put(path, nextNode);
            }

            //pass++，表示多了一个字符串从此经过,切记是下一个节点++
            nextNode.pass++;

            node = nextNode;

        }
        //最后一个节点的end++
        node.end++;
    }

    //根据str路径搜寻节点
    public Node search(String str){
        char[] chars = str.toCharArray();
        Node node = root;
        for(char c:chars){
            int path = (int)c;
            Node nextNode = node.nexts.get(path);
            if(nextNode==null) {
                return null;
            }
            node = nextNode;
        }
        return node;
    }

    //查询所有已加入的字符串中，以str为前缀的有多少个
    public int prefixSearch(String str){
        Node node = search(str);
        return node==null?0:node.pass;
    }

    /**
     * 删除该str
     * @param str
     * @return
     */
    public boolean delete(String str){
        //必须先查询是否有该节点，不能边找边将路过节点的pass--，如果没有改节点，那就不应该将pass--
        Node targetNode = search(str);
        if(targetNode==null || targetNode.end==0){
            return false;
        }

        char[] chars = str.toCharArray();
        Node node = root;
        for(int i=0;i<chars.length;i++){
            int path = (int)chars[i];
            node = node.nexts.get(path);
            node.pass--;//途径节点的pass--
            if(i==chars.length-1 && node.pass==0){
                //最后一个节点，并且pass为0，表示后面不可能再有节点了，置空回收
                node.nexts.remove(path);
                break;
            }
        }
        node.end--;
        return true;
    }

    public static void main(String[] args) {
        PrefixTree tree = new PrefixTree();
        tree.add("abc");
        tree.add("abd");
        tree.add("abe");
        System.out.println(tree.prefixSearch("ab"));

        tree.delete("abc");
        System.out.println(tree.prefixSearch("ab"));
    }
}
