package com.wcn.algorithm.sort;

/**
 * 计数排序
 * 一个数组，值都小于K的正数，初始化一个桶（数组），元素组位置的值就表示桶的索引位置。
 * 循环元素组的值，桶中响应的位置就表示该元素出现多少次。
 */
public class CountSort {

    public static void main(String[] args) {
        int[] array = new int[]{9,5,7,4,4,6,8,3,2,4,7,8,9,6,4,2,3,5,7,9,2,1,4,0,6,4,0};
        int[] result = sort(array, 10);
        for(int i:result){
            System.out.print(i+"  ");
        }
    }

    public static int[] sort(int array[], int k){
        //help的索引表示array内的值，help索引位置的数值表示：该索引表示的值再array中出现了几次
        int[] help = new int[k];
        for(int i:array){
            help[i]++;
        }

        int[] result = new int[array.length];

        int index = 0;
        for(int i=0;i<help.length;i++){
            for(int j=0;j<help[i];j++){
                result[index] = i;
                index++;
            }
        }
        return result;
    }
}
