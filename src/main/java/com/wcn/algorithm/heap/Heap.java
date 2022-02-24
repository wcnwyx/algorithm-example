package com.wcn.algorithm.heap;

/**
 * 堆（大根堆）
 */
public class Heap {
    public static void main(String[] args) {
        Heap heap = new Heap(10);
        for(int i=0;i<10;i++){
            heap.push(i);
        }
        while(heap.size>0){
            System.out.println(heap.pop());
        }
    }

    int[] values = null;//素组存储堆内元素
    int size;//对内目前有几个元素

    public Heap(int limit){
        values = new int[limit];
        size = 0;
    }

    /**
     * 往堆中添加新元素
     * @param v
     */
    public void push(int v){
        if(size==values.length){
            throw new RuntimeException("堆满了");
        }
        values[size] = v;
        heapInsert(size);
        size++;
    }

    /**
     * 弹出堆顶
     * 弹出后，剩余的数据亦然要保持大根堆结构
     */
    public int pop(){
        int result = values[0];//堆顶
        swap(0, size-1);//将最后一个元素挪到堆顶
        size--;//堆大小减1
        heapify(0);
        return result;
    }

    /**
     * 插入元素后移动到恰当的位置，
     * 比如说小根堆，就和父节点对比，如果比父节点小，就和父节点调换位置
     * @param index 新插入节点的位置
     */
    private void heapInsert(int index){
        while(index>0 && values[index] >values[getParentIndex(index)]){
            //没有到根，并且index比其父节点大，则和父节点交换然后继续
            swap(index, getParentIndex(index));
            index = getParentIndex(index);
        }
    }

    /**
     * 从指定位置往下看，如果子节点比自己大，则和最大的子节点交换，使该值下沉，直到子节点都不比自己大或者没有字节点
     * @param index
     */
    private void heapify(int index){
        int leftChildIndex = getLeftChildIndex(index);
        int rightChildIndex = getRightChildIndex(index);

        //左子节点越界了，右子节点肯定也月结，只判断左就好了
        while(leftChildIndex<size){
            //有子节点就循环

            int largestIndex = leftChildIndex;
            if(rightChildIndex<size && values[leftChildIndex]<values[rightChildIndex]){
                //有右子节点，并且右子节点的值比左大
                largestIndex = rightChildIndex;
            }

            if(values[index]>=values[largestIndex]){
                //如果index比子节点都大，直接退出
                return;
            }
            swap(index, largestIndex);
            index = largestIndex;
            leftChildIndex = getLeftChildIndex(index);
            rightChildIndex = getRightChildIndex(index);
        }
    }

    private void swap(int i,int j){
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
