package com.wcn.algorithm.recursive;

import java.util.ArrayList;
import java.util.List;

/**
 * 递归实现一个字符串的所有排序， 如：
 * ab 所有排列有： ab ba
 * abc 所有排列有： abc acb bac bca cab cba
 * 比如说abc，一个三个字符，第一步决定第一个字符，可以有三个分支（a\b\c）,然后再将可选分内容删除掉已选择的，再次做第二次选择。
 * 可以想成树，根节点有三个路径（a\b\c）,在下面的节点都有两个路径（要删除掉已选择的路径）
 */
public class StringAllOrder {
    public static void main(String[] args) {
        String str = "abc";
        List<String> result = new ArrayList<>();
        process(str.toCharArray(), 0, result);
        for(String e:result){
            System.out.println(e);
        }

        System.out.println("---------------------------");
        List<String> result1 = new ArrayList<>();
        process1(str.toCharArray(), "", result1);
        for(String e:result1){
            System.out.println(e);
        }
    }

    /**
     * 一共处理字符串长度的次数：
     * 第一个字符将所有的可能都选择一遍，然后将可选字符数组中删除掉，再进行后续选择
     * @param array 还剩余可选的字符串
     * @param path 已经组好的字符串
     * @param result 所有的结果
     */
    public static void process1(char[] array, String path, List<String> result){
        if(array.length==1){
            result.add(path+array[0]);
            return ;
        }
        for(int i=0;i<array.length;i++){
            char[] nextArray = new char[array.length-1];
            for(int j=0;j<nextArray.length;j++){
                nextArray[j] = array[j<i?j:j+1];
            }
            process1(nextArray, path+array[i], result);
        }
    }

    /**
     * 上面的方法充斥着大量的数组拷贝，该方法也是一样的思路，只是实现的技巧好了点。
     * 数组还是原来的数组，第一次决定array[0]是哪个字符，然后交换到0位置；后续依次再决定1、2、3等位置是哪个字符
     * @param array 字符串字符数组
     * @param index 处理到了第几个索引位置
     * @param result
     */
    public static void process(char[] array, int index, List<String> result){
        if(index==array.length-1){
            result.add(new String(array));
        }
        for(int i=index;i<array.length;i++){
            swap(array, index, i);
            process(array, index+1, result);
            swap(array, index, i);//再次交换过来还原现场
        }
    }

    private static void swap(char[] array, int index1, int index2){
        char c1 = array[index1];
        array[index1] = array[index2];
        array[index2] = c1;
    }
}
