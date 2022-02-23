package com.wcn.algorithm.recursive;

/**
 * 递归实现查找数组中最大的值
 */
public class ArrayGetMax {

    public static void main(String[] args) {
        int[] array = new int []{50,1,2,3,40,5,6,7,8,9};
        int max = process(array, 0, array.length-1);
        System.out.println(max);
    }

    public static int process(int[] array, int l, int r){
        if(l==r){
            return array[l];
        }
        int middle = l + ((r-l)>>1);

        //将素组切两段，每两段求一个最大值，再取两个结果的最大值（当然也可以切3份、4份）
        int leftMax = process(array, l, middle);
        int rightMax = process(array, middle+1, r);
        return Math.max(leftMax, rightMax);
    }
}
