package com.wcn.algorithm.sort;

/**
 * 插入排序算法 O(N^2)：
 * 从第二个开始往前比，如果比自己小则交换
 * 第三张依次对比前两张
 * 后续循环对比自己前面所有的
 * 想象摆扑克牌的逻辑
 */
public class InsertionSort {
    public static void main(String[] args) {
        int[] array = new int[]{1, 7, 3, 5, 4, 9, 6};
        InsertionSort insertionSort = new InsertionSort();
        insertionSort.sort(array);
        for(int i:array){
            System.out.println(i);
        }
    }

    private void sort(int[] array){
        if(array==null || array.length==1){
            return;
        }

        for(int i=1;i<array.length;i++){
            for(int j=i;j>=1;j--){
                if(array[j]<array[j-1]){
                    swap(array, j, j-1);
                }else{
                    break;
                }
            }
        }
    }

    private void swap(int[] array, int i, int j){
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
