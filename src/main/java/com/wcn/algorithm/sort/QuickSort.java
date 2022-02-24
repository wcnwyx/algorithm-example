package com.wcn.algorithm.sort;

import com.wcn.algorithm.other.Partitions;

import java.util.Random;

/**
 * 快速排序：利用分区处理
 * 时间复杂度O(N*logN) 额外空间复杂度O(logN)
 * 1.划分值也靠近中间，性能越好，越靠近两边，性能越差。
 * 2.随机选一个数进行划分的目的就是让好情况和坏情况都变成概率事件
 * 3.把每一种情况都列出来，会有每种情况下的时间复杂度，但概率都是1/N
 * 4.那么所有情况都考虑，时间复杂度就是这种概率模型下的长期期望
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] array = new int[]{9,4,5,3,4,2,6,1,4,8,10,1,2};
        process1(array, 0, array.length-1);
        for(int i:array){
            System.out.print(i+" ");
        }

        System.out.println();
        array = new int[]{9,4,5,3,4,2,6,1,4,8,10,1,2};
        process2(array, 0, array.length-1);
        for(int i:array){
            System.out.print(i+" ");
        }
    }

    /**
     * 快速排序1：利用分区逻辑递归，先分一次，再左边分+右边分，再次递归循环
     * 差情况是每次right位置都是最大的，效率就很低
     * @param array
     * @param left
     * @param right
     */
    public static void process1(int[] array, int left, int right){
        if(left==right){
            return ;
        }
        //按照array[right]的值进行分区
        int[] positions = Partitions.process(array, left, right, array[right]);
        if(positions[0]-1>left){
            //左侧至少还有2个元素，再次处理左侧部分
            process1(array, left, positions[0]-1);
        }
        if(positions[1]+1<right){
            //右侧至少还有2个元素，再次处理右侧部分
            process1(array, positions[1]+1, right);
        }
    }

    /**
     * 区分process1的区别在于不是固定拿该区间的最后一个数值，而是随机拿一个数值
     * @param array
     * @param left
     * @param right
     */
    public static void process2(int[] array, int left, int right){
        if(left==right){
            return ;
        }
        //按照array[right]的值进行分区
        int randomIndex = left + new Random().nextInt(right-left);
        int[] positions = Partitions.process(array, left, right, array[randomIndex]);
        if(positions[0]-1>left){
            //左侧至少还有2个元素，再次处理左侧部分
            process2(array, left, positions[0]-1);
        }
        if(positions[1]+1<right){
            //右侧至少还有2个元素，再次处理右侧部分
            process2(array, positions[1]+1, right);
        }
    }
}
