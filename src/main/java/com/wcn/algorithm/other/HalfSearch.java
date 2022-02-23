package com.wcn.algorithm.other;

/**
 * 二分查找:
 * 1.在升序的素组中查找指定值是否存在
 * 2.在升序数组中，找到>=指定数最左侧位置
 * 3.山峰数组
 */
public class HalfSearch {

    public static void main(String[] args) {
        HalfSearch halfSearch = new HalfSearch();

        boolean findResult = halfSearch.search1(new int[]{0,10,20,30,40,50,60,70,80,90}, 60);
        System.out.println("findResult:"+findResult);

        findResult = halfSearch.search1(new int[]{0}, 0);
        System.out.println("findResult:"+findResult);

        int findResult2 = halfSearch.search2(new int[]{1,1,2,2,2,3,3,3,4}, 2);
        System.out.println("findResult2:"+findResult2);

        int peak = halfSearch.peakIndexInMountainArray(new int[]{3,5,3,2,0});
        System.out.println("peak:"+peak);
    }

    /**
     * 在升序的素组中查找指定值是否存在
     * @param array
     * @param value
     * @return
     */
    public boolean search1(int[] array, int value){
        if(array==null || array.length==0 || value<array[0] || value>array[array.length-1]){
            //比最小的小，比最大的大，则直接返回
            return false;
        }
        int left = 0, middle = 0, right = array.length-1;
        while(left<=right){
            middle = left + ((right-left)>>1);
            System.out.print(left+" "+middle+" "+right);
            if(value==array[middle]){
                return true;
            }else if(value<array[middle]){
                right = middle-1;
            }else {
                left = middle+1;
            }
            System.out.println("  >>>>  "+left+" "+right);
        }
        return false;
    }

    /**
     * 在升序数组中，找到>=指定数最左侧位置
     * @param array
     * @param value
     * @return 索引位置
     */
    public int search2(int[] array, int value){
        if(array==null || array.length==0 || value>array[array.length-1]){
            //比最大的大，则直接返回
            return -1;
        }
        int left = 0, middle = 0, right = array.length-1;
        int index = -1;
        while(left<=right){
            middle = left + ((right-left)>>1);
            System.out.print(left+" "+middle+" "+right);
            if(array[middle]>=value){
                index = middle;
                right = middle-1;
            }else{
                left = middle+1;
            }
            System.out.println("  >>>>  "+left+" "+right);
        }
        return index;
    }


    /**
     * 符合下列属性的数组 arr 称为 山峰数组（山脉数组） ：
     * arr.length >= 3
     * 存在 i（0 < i < arr.length - 1）使得：
     * arr[0] < arr[1] < ... arr[i-1] < arr[i]
     * arr[i] > arr[i+1] > ... > arr[arr.length - 1]
     * 给定由整数组成的山峰数组 arr ，返回任何满足 arr[0] < arr[1] < ... arr[i - 1] < arr[i] > arr[i + 1] > ... > arr[arr.length - 1] 的下标 i即山峰顶部。
     * 链接：https://leetcode-cn.com/problems/B1IidL
     * @param array
     * @return 索引位置
     */
    public int peakIndexInMountainArray(int array[]){
        if(array==null || array.length<3){
            return -1;
        }
        int left = 0, middle = 0, right = array.length-1;
        while(left<=right){
            middle = left + ((right-left)>>1);
            if(array[middle-1]<array[middle] && array[middle]>array[middle+1]){
                //middle位置正好是峰顶
                return middle;
            }else if(array[middle-1]>array[middle] && array[middle]>array[middle+1]){
                //middle-1 到 middle位置已经开始下降了
                right = middle;
            }else if(array[middle-1]<array[middle] && array[middle]<array[middle+1]){
                //middle-1 middle middle+1位置还在上升
                left = middle;
            }
        }
        return middle;
    }
}
