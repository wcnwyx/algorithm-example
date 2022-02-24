package com.wcn.algorithm.sort;

/**
 * 堆排序
 * 1. 先让整个数组变成大根堆结构，建立堆的过程：
 *      1.1：从上到下的方法（heapInsert）,时间复杂度是O(N*logN)
 *      1.2: 从下往上的方法（heapify），时间复杂度是O(N)
 *  2. 把堆的最大值（堆顶）和堆尾的值交换，然后堆大小减1，再去调整堆，一直循环处理，时间复杂度是O(N*logN)
 *  3. 堆大小减小到0之后，排序完成。
 */
public class HeapSort {

    public static void main(String[] args) {
        int[] array = new int[]{1,3,5,7,9,0,2,4,6,8};
        HeapSort heapSort = new HeapSort();
        heapSort.sort(array);
        for(int i:array){
            System.out.print(i+" ");
        }
    }

    public void sort(int[] array){
        //将数组构建成大根堆
//        for(int i=0;i<array.length;i++){//O(N)
//            heapInsert(array, i);//O(logN)
//        }

        //将数组构建成大根堆,这种将数组构建成大根堆要比上面的效果好
        //这种是从堆底部开始调整，二叉树最底层的元素最多，但是却都不需要操作处理
        for(int i=array.length-1;i>=0;i--){
            heapify(array, i, array.length);
        }

        int heapSize = array.length;
        for(int i=0;i<array.length;i++){//O(N)
            //依次将堆顶元素放到数组最后面，然后heapify重新调整大根堆
            //比如索引位置是0-4的大根堆
            //将0位置和4位置交换，然后将0-3调整好
            //将0位置和3位置交换，然后将0-2调整好
            //将0位置和2位置交换，然后将0-1调整好
            //将0位置和1位置交换
            swap(array, 0, heapSize-1);
            heapSize--;
            heapify(array, 0, heapSize);//O(logN)
        }
    }

    /**
     * 插入元素后移动到恰当的位置，
     * 比如说小根堆，就和父节点对比，如果比父节点小，就和父节点调换位置
     * @param index 新插入节点的位置
     */
    private void heapInsert(int[] values, int index){
        while(index>0 && values[index] >values[getParentIndex(index)]){
            //没有到根，并且index比其父节点大，则和父节点交换然后继续
            swap(values, index, getParentIndex(index));
            index = getParentIndex(index);
        }
    }

    private void heapify(int[] values, int index, int heapSize){
        int leftChildIndex = getLeftChildIndex(index);
        int rightChildIndex = getRightChildIndex(index);

        //左子节点越界了，右子节点肯定也月结，只判断左就好了
        while(leftChildIndex<heapSize){
            //有子节点就循环

            int largestIndex = leftChildIndex;
            if(rightChildIndex<heapSize && values[leftChildIndex]<values[rightChildIndex]){
                //有右子节点，并且右子节点的值比左大
                largestIndex = rightChildIndex;
            }

            if(values[index]>=values[largestIndex]){
                //如果index比子节点都大，直接退出
                return;
            }
            swap(values, index, largestIndex);
            index = largestIndex;
            leftChildIndex = getLeftChildIndex(index);
            rightChildIndex = getRightChildIndex(index);
        }
    }

    private void swap(int[] values, int i,int j){
        int temp = values[i];
        values[i] = values[j];
        values[j] = temp;
    }

    private int getParentIndex(int index){
        return (index-1)>>1;
    }
    private int getLeftChildIndex(int index){
        return (index<<1)+1;
    }
    public int getRightChildIndex(int index){
        return (index<<1)+2;
    }
}
