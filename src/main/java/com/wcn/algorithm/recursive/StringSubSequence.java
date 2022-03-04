package com.wcn.algorithm.recursive;

import java.util.ArrayList;
import java.util.List;

/**
 * 递归实现一个字符串的所有子序列。（子序列不一定要求字符相领，但是顺序要保持）
 * ab 的子序列有 空、a、b、ab
 * abc 的子序列有 空、a、b、c、ab、ac、cb、abc
 * 可以想象为每一个索引位置的char有两种结果（要和不要），从第一个字符开始生成二叉树，一个分支是要这个字符，一个分支是不要这个字符，
 * 然后每个子节点继续第二个字符生成子节点（要和不要），一次往下生成，所有的路径组成了所有的子序列
 *                         ""
 *            "a"           |         ""
 *
 *       "b"   |     ""         "b"    |    ""
 *
 *    "c" |  ""   "c" |  ""  "c" | ""    "c" |  ""
 */
public class StringSubSequence {
    public static void main(String[] args) {
        String str = "abcd";
        List<String> result = new ArrayList<>();
        process(str.toCharArray(), 0, "", result);
        System.out.println("size="+result.size());
        result.forEach(e -> System.out.println(e));
    }

    public static void process(char[] charArray, int index, String path, List<String> result){
        if(index==charArray.length){
            result.add(path);
            return ;
        }
        //不包含个当前索引位置char的结果
        String notContain = path;
        process(charArray, index+1, notContain, result);

        //包含当前索引位置char的结果
        String yesContain = path+charArray[index];
        process(charArray, index+1, yesContain, result);
    }
}
