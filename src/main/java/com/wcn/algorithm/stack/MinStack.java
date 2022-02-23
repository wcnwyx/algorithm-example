package com.wcn.algorithm.stack;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 请设计一个栈，除了常规栈支持的pop与push函数以外，还支持min函数，该函数返回栈元素中的最小值。
 * 执行push、pop和min操作的时间复杂度必须为O(1)。
 *
 * 链接：https://leetcode-cn.com/problems/min-stack-lcci
 */
public class MinStack {
    private Deque<Integer> dateStack = new LinkedList<>();
    private Deque<Integer> minStack = new LinkedList<>();

    public MinStack() {

    }

    public void push(int x) {
        dateStack.addFirst(x);
        Integer min = minStack.peekFirst();
        minStack.addFirst(min==null?x:Math.min(min, x));
    }

    public void pop() {
        minStack.removeFirst();
        dateStack.removeFirst();
    }

    public int top() {
        return dateStack.peekFirst();
    }

    public int getMin() {
        return minStack.peekFirst();
    }
}
