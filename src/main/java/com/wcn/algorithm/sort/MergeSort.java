package com.wcn.algorithm.sort;

/**
 * 归并排序：
 * 整体是递归（也可以非递归实现），左边排好序+右边排好序+merge左边和右边
 * 从小打到排序
 */
public class MergeSort {

    public static void main(String[] args) {
        int[] array1 = new int[]{9,4,3,2,1,8,7,6,5};
        process1(array1, 0, array1.length-1);
        for(int i:array1){
            System.out.println(i);
        }
        System.out.println("------------------------------------------");

        int[] array2 = new int[]{9,4,3,2,1,8,7,6,5};
        process2(array2);
        for(int i:array2){
            System.out.println(i);
        }
    }

    /**
     * 递归实现
     * @param array
     * @param left
     * @param right
     */
    public static void process1(int[] array, int left, int right){
        if(left==right){
            return ;
        }
        int middle = left + ((right-left)>>1);
        //左半部分排序
        process1(array, left, middle);
        //右半部分排序
        process1(array, middle+1, right);
        //左右两部分merge
        merge(array, left, middle, right);
    }

    /**
     * 非递归实现
     * @param array
     */
    public static void process2(int[] array){
        int step = 1;//左右两边各多少个,依次2倍增长
        while(step<array.length){
            int left = 0, middle = 0, right = 0;
            while(left < array.length){
                middle = left+step-1;
                if(middle>array.length-1){
                    middle = array.length-1;
                }
                right = middle+step;
                if(right>array.length-1){
                    right = array.length-1;
                }
                merge(array, left, middle, right);
                left = right +1;
            }
            step = step<<1;
        }
    }

    /**
     * 将左右两部分合并
     * @param array
     * @param left
     * @param middle
     * @param right
     */
    public static void merge(int[] array, int left, int middle, int right){
        System.out.println("               merge "+left+" "+middle+" "+right);
        int indexL=left;
        int indexR=middle+1;
        int indexTem = 0;
        int[] temp = new int[right-left+1];
        while(indexL<=middle && indexR<=right){
            //一次取小值放入temp
            if(array[indexL]<=array[indexR]){
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
    }
}
