package com.wcn.algorithm.sort;

/**
 * 归并排序应用：求数组小和
 * 一个数组中，一个数左边比它小的树的总和，叫做该数的小和，所有数的小和加起来即为数组的小和。例如：
 * [1,3,4,2,5]
 * 1的小和为0
 * 3的小和为1
 * 4的小和为1+3
 * 2的小和为1
 * 5的小和为1+3+4+2
 */
public class MergeSort1 {

    public static void main(String[] args) {
        int[] array1 = new int[]{1,3,4,2,5};
        int sum = process1(array1, 0, array1.length-1);
        for(int i:array1){
            System.out.println(i);
        }

        System.out.println("sum="+sum);
    }

    /**
     * 递归实现
     * @param array
     * @param left
     * @param right
     */
    public static int process1(int[] array, int left, int right){
        if(left==right){
            return 0;
        }
        int middle = left + ((right-left)>>1);
        //左半部分排序
        int leftSum = process1(array, left, middle);
        //右半部分排序
        int rightSum = process1(array, middle+1, right);
        //左右两部分merge
        int mergeSum = merge(array, left, middle, right);
        return leftSum+rightSum+mergeSum;
    }

    /**
     * 将左右两部分合并，合并的过程中计算小和
     * @param array
     * @param left
     * @param middle
     * @param right
     */
    public static int merge(int[] array, int left, int middle, int right){
        System.out.println("               merge "+left+" "+middle+" "+right);
        int indexL=left;
        int indexR=middle+1;
        int indexTem = 0;
        int mergeSum = 0;//小和
        int[] temp = new int[right-left+1];
        while(indexL<=middle && indexR<=right){
            //一次取小值放入temp
            if(array[indexL]<=array[indexR]){
                mergeSum += array[indexL]*(right-indexR+1);//计算小和,左边数据*右边数组个数
                temp[indexTem++]=array[indexL++];
            }else{
                temp[indexTem++]=array[indexR++];
            }
        }
        while(indexL<=middle){
            //左侧部分还有剩余
            temp[indexTem++]=array[indexL++];
        }

        while(indexR<=right){
            //右侧部分还有剩余
            temp[indexTem++]=array[indexR++];
        }

        for(int i=0;i<temp.length;i++){
            array[left+i] = temp[i];
        }
        return mergeSum;
    }
}
