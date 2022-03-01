package com.wcn.algorithm.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 给定一组字符串，找出一种拼接顺序，拼接后字典序最小
 */
public class StringAppend {
    public static void main(String[] args) {
        String[] array = new String[]{"bc", "ab", "a"};
        sort(array);
        for(String s:array){
            System.out.print(s+" ");
        }

        System.out.println("\r\n--------------------");
        array = new String[]{"bc", "ab", "a"};
        System.out.println(sort1(array));

    }

    /**
     * 贪心点：先拿字典序最小的
     * @param str
     */
    public static void sort(String[] str){
        Arrays.sort(str, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return (o1+o2).compareTo(o2+o1);
            }
        });
    }

    /**
     * 暴力递归
     * @param str
     */
    public static String sort1(String[] str){
        List<String> allResult = new ArrayList<>();
        process(str, new ArrayList<Integer>(), "", allResult);
//        for(String s:allResult){
//            System.out.println(s);
//        }
        String[] array = new String[allResult.size()];
        allResult.toArray(array);
        Arrays.sort(array, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        return array[0];
    }

    /**
     *
     * @param array 原始左右字符串的数组
     * @param used 使用过了数组中哪个索引的字符串
     * @param currentStr 当前拼接结果
     * @param allResult 所有的拼接结果
     */
    private static void process(String[] array, List<Integer> used, String currentStr, List<String> allResult){
        if(used.size()==array.length){
            allResult.add(currentStr);
            return ;
        }
        for(int i=0;i<array.length;i++){
            if(!used.contains(i)){
                List<Integer> usedNext = new ArrayList<>();
                usedNext.addAll(used);
                usedNext.add(i);
                process(array, usedNext, currentStr+array[i], allResult);
            }
        }
    }
}
