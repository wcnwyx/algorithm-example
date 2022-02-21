package com.wcn.algorithm.sort;

/**
 * 选择排序算法 O(N^2)：
 * 从0-N选择最小值放到0
 * 从1-N选择最小是放到1
 * 依次选择
 */
public class SelectionSort {
    public static void main(String[] args) {
        int[] array = new int[]{1, 7, 3, 5, 4, 9, 6};
        SelectionSort selectionSort = new SelectionSort();
        selectionSort.sort(array);
        for (int i:array){
            System.out.println(i);
        }
    }

    private void sort(int[] array){
        if(array==null || array.length==1){
            return;
        }

        //用于记录最小值的索引
        int minIndex = 0;
        for(int i=0;i<array.length;i++){
            minIndex = i;
            for(int j=i+1;j<array.length;j++){
                if(array[minIndex]>array[j]){
                    minIndex = j;
                }
            }
            if(minIndex!=i){
                swap(array, i, minIndex);
            }
        }
    }

    private void swap(int[] array, int i, int j){
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
