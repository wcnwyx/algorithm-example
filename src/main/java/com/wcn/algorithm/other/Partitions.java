package com.wcn.algorithm.other;

/**
 * 分组问题：
 * 给定一个int[] 和指定数值num，将小于num的靠左放，等于num的居中放，大于num的靠右放。
 * 例如： int[]{9,4,5,3,4,2,6,1} num为4，结果需要为{3,2,1,4,4,9,5,6}
 *
 * 或者是荷兰国旗问题、三色小球分组问题都是分组来处理的。
 */
public class Partitions {
    public static void main(String[] args) {
        int[] array = new int[]{9,4,5,3,4,2,6,1,4,8,10,1,2};
        process(array, 4);
        for(int i:array){
            System.out.print(i+" ");
        }
    }

    public static void process(int[] array, int num){
        if(array==null || array.length==1){
            return ;
        }
        int less = 0;//小于部分的后一个数据索引
        int more = array.length-1;//大于部分的前一个数据索引
        int index = 0;//当前处理数据的索引
        while(index<=more){
            if(array[index]<num){
                swap(array, index, less);
                less++;
                index++;
            }else if(array[index]>num){
                swap(array, index, more);
                more--;
                //这里index不能加，因为从右侧more换到该index的数据还未处理
            }else{
                index++;
            }
        }
    }

    public static void swap(int[] array, int i, int j){
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

}
