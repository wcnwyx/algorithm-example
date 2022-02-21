package com.wcn.algorithm.sort;

/**
 * 冒泡排序算法 O(N^2)：
 * 依次从0-N选择最大的，放到N
 * 依次从0-N-1选择最大的，放到N-1
 *
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] array = new int[]{1, 7, 3, 5, 4, 9, 6};
        BubbleSort bubbleSort = new BubbleSort();
        bubbleSort.sort(array);
        for(int i:array){
            System.out.println(i);
        }
    }

    private void sort(int[] array){
        if(array==null || array.length==1){
            return;
        }

        for(int i=array.length-1;i>0;i--){
            for(int j=0;j<i;j++){
                if(array[j]>array[j+1]){
                    swapOxr(array, j, j+1);
                }
            }
        }
    }

    private void swap(int[] array, int i, int j){
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private void swapOxr(int[] array, int i, int j){
        array[i] = array[i] ^ array[j];
        array[j] = array[i] ^ array[j];
        array[i] = array[i] ^ array[j];
    }
}
