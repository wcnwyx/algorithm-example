package com.wcn.algorithm.heap;

import java.util.*;

/**
 * 堆结构
 * 1.大根堆还是小根堆根据比较器来判断
 * 2.支持堆内元素修改（修改后堆位置要调整）
 */
public class Heap2<V> {

    //因为要支持泛型，采用list代替数组
    private List<V> values;
    //元素所在的list中所以记录下来，该节点内容修改后，才可以调整
    private Map<V, Integer> positionMap;
    //当前堆元素个数
    private int size;
    //元素比较器
    private Comparator<V> comparator;

    public Heap2(Comparator comparator){
        values = new ArrayList();
        positionMap = new HashMap<>();
        this.size = 0;
        this.comparator = comparator;
    }

    /**
     * 往堆中添加新元素
     * @param v
     */
    public void push(V v){
        values.add(size, v);
        positionMap.put(v, size);//记录位置信息
        heapInsert(size);
        size++;
    }

    /**
     * 弹出堆顶
     * 弹出后，剩余的数据亦然要保持大根堆结构
     */
    public V pop(){
        V result = values.get(0);//堆顶
        swap(0, size-1);//将最后一个元素挪到堆顶
        size--;//堆大小减1
        heapify(0);
        positionMap.remove(result);//位置信息也要移除
        return result;
    }

    /**
     * 插入元素后移动到恰当的位置，
     * @param index 新插入节点的位置
     */
    private void heapInsert(int index){
        while(index>0 && comparator.compare(values.get(index), values.get(getParentIndex(index))) > 0){
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
            if(rightChildIndex<size && comparator.compare(values.get(leftChildIndex), values.get(rightChildIndex)) < 0){
                //有右子节点，并且右子节点的值比左大
                largestIndex = rightChildIndex;
            }

            if(comparator.compare(values.get(index), values.get(largestIndex))>0){
                //如果index比子节点都大，直接退出
                return;
            }
            swap(index, largestIndex);
            index = largestIndex;
            leftChildIndex = getLeftChildIndex(index);
            rightChildIndex = getRightChildIndex(index);
        }
    }

    //重置某一个元素，因为元素内部参与排序的属性发生了变化
    public boolean reset(V v){
        Integer index = positionMap.get(v);
        if(index==null){
            return false;
        }
        //要不heapInsert导致往上移动，要不heapify导致往下沉，不要不用动，只会处理一种逻辑
        heapInsert(index);
        heapify(index);
        return true;
    }

    private void swap(int i,int j){
        V iValue = values.get(i);
        V jValue = values.get(j);
        values.set(i, jValue);
        values.set(j, iValue);

        //位置信息也要变更
        positionMap.put(iValue, j);
        positionMap.put(jValue, i);
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

    public static void main(String[] args) {
        Comparator<A> comparator = new Comparator<A>() {
            @Override
            public int compare(A o1, A o2) {
                if(o1.getA()!=o2.getA()){
                    return o1.getA()-o2.getA();
                }else{
                    return o1.getB()-o2.getB();
                }
            }
        };

        Heap2<A> heap2 = new Heap2(comparator);
        heap2.push(new A(5,5));
        heap2.push(new A(4,4));
        heap2.push(new A(6,6));
        heap2.push(new A(3,3));
        heap2.push(new A(7,7));
        heap2.push(new A(7,6));
        heap2.push(new A(7,8));
        while(heap2.size>0){
            System.out.println(heap2.pop());
        }

        System.out.println("-----------------");
        heap2.push(new A(5,5));
        heap2.push(new A(4,4));
        heap2.push(new A(6,6));
        A a3 = new A(3, 3);
        heap2.push(a3);
        heap2.push(new A(7,7));
        heap2.push(new A(7,6));
        heap2.push(new A(7,8));
        for(int i=0;i<=2;i++){
            System.out.println(heap2.pop());
        }
        a3.setA(10); a3.setB(10);//改变a3的值,调整到10后会到堆顶，下次会优先打印
        heap2.reset(a3);
        while(heap2.size>0){
            System.out.println(heap2.pop());
        }
    }

    static class A{
        private int a;
        private int b;

        public A(int a, int b) {
            this.a = a;
            this.b = b;
        }

        public void setA(int a) {
            this.a = a;
        }

        public void setB(int b) {
            this.b = b;
        }

        public int getA() {
            return a;
        }

        public int getB() {
            return b;
        }

        @Override
        public String toString() {
            return "A{" +
                    "a=" + a +
                    ", b=" + b +
                    '}';
        }
    }
}
