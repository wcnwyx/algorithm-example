package com.wcn.algorithm.recursive;

import java.util.Stack;

/**
 * 将一个栈元素颠倒(1->2->3->4 变为 4->3->2->1)，不能使用额外的数据结构，递归实现
 */
public class StackReverse {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        System.out.println(stack);
        process(stack);
        System.out.println(stack);
    }

    public static void process(Stack<Integer> stack){
        if(stack.isEmpty()){
            return ;
        }
        Integer value = getAndDeleteStackHead(stack);
        process(stack);
        stack.push(value);
    }

    //删除并获取栈底的元素
    public static Integer getAndDeleteStackHead(Stack<Integer> stack){
        Integer value = stack.pop();
        if(stack.isEmpty()){
            return value;
        }else{
            int valueFinal = getAndDeleteStackHead(stack);
            stack.push(value);
            return valueFinal;
        }
    }
}
