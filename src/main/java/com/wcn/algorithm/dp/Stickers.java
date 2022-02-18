package com.wcn.algorithm.dp;

import java.util.HashMap;
import java.util.Map;

/**
 * 我们有 n 种不同的贴纸。每个贴纸上都有一个小写的英文单词。
 * 您想要拼写出给定的字符串 target ，方法是从收集的贴纸中切割单个字母并重新排列它们。如果你愿意，你可以多次使用每个贴纸，每个贴纸的数量是无限的。
 * 返回你需要拼出 target 的最小贴纸数量。如果任务不可能，则返回 -1 。
 * 链接：https://leetcode-cn.com/problems/stickers-to-spell-word
 */
public class Stickers {
    public static void main(String[] args){
        String[] stickers = new String[]{"swim","love","father","shape","rich","multiply","new","fill","history"};
        String target = "operateform";
        System.out.println(minStickers(stickers, target));
    }
    public static int minStickers(String[] stickers, String target) {
        //判断stickers中的所有字符是否可以包含全target中的所有字符
        for(char c:target.toCharArray()){
            boolean contain = false;
            for(String sticker:stickers){
                if(sticker.contains(c+"")){
                    contain = true;
                    break;
                }
            }
            if(!contain){
                return -1;
            }
        }
        Map<String, Integer> cache = new HashMap<>();
        return process(stickers, target, cache);
    }

    /**
     *
     * @param stickers 所有贴纸
     * @param target 目标还剩下哪些字符
     * @return
     */
    public static int process(String[] stickers, String target, Map<String, Integer> cache){
        Integer cacheResult = cache.get(target);
        if(cacheResult!=null){
            return cacheResult;
        }
        if(target.equals("")){
            return 0;
        }

        int numNextMin = Integer.MAX_VALUE;
        //一次循环每个贴纸，将target中能去掉的字符去掉
        for(String sticker:stickers){
            //判断该贴纸是否可以消除掉target中的至少一个字符，如果一个都不包含，这个贴纸就不用了
            boolean contain = false;
            for(char c:target.toCharArray()){
                if(sticker.contains(c+"")){
                    contain = true;
                    break;
                }
            }
            if(!contain){
                continue;
            }

            //将该贴纸的字符，从target中删除
            String targetNext = target;
            for(char c:sticker.toCharArray()){
                targetNext = targetNext.replaceFirst(c+"", "");
            }

            int numNext = process(stickers, targetNext, cache);
            numNextMin = Math.min(numNextMin, numNext);
        }

        cache.put(target, numNextMin+1);
        return numNextMin+1;
    }
}
