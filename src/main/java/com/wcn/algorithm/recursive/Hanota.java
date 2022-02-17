package com.wcn.algorithm.recursive;

import java.util.ArrayList;
import java.util.List;

/**
 * 汉诺塔递归实现，List的尾部作为汉诺塔的上方
 */
public class Hanota {
    public static void main(String[] args) {
        List<Integer> a = new ArrayList<>();
        a.add(3);
        a.add(2);
        a.add(1);
        List<Integer> b = new ArrayList<>();
        List<Integer> c = new ArrayList<>();
        process(a.size(), a, "L", c, "R", b, "M");
    }

    public static void process(int n, List<Integer> from, String fromName , List<Integer> to, String toName, List<Integer> other, String otherName){
        if(n==1){
            System.out.print("move "+from.get(from.size()-1)+" from " + fromName+ from + " to " + toName+to);
            to.add(from.remove(from.size()-1));
            System.out.println(" | " + fromName + from + " " + toName + to);
        }else{
            //第一步现将上面的n-1个从from移动到other
            process(n-1, from, fromName, other, otherName, to, toName);
            //第二步将最后一个从from直接移动到to
            process(1, from, fromName, to, toName, other, otherName);
            //再将第一步的n-1个从other移动到to
            process(n-1, other, otherName, to,toName, from, fromName);
        }
    }
}
