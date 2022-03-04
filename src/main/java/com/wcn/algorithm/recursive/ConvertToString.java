package com.wcn.algorithm.recursive;

import java.util.ArrayList;
import java.util.List;

/**
 * 从左往右的尝试模型1
 * 规定1和A对应、2和B对应、3和C对应。。。。
 * 那么一个数字字符串比如“111“就可以转化为 "AAA"、"KA"、"AK"
 * 给定一个只有数字组成的字符串str，返回有多少种转化结果
 */
public class ConvertToString {

    public static void main(String[] args) {
        String num = "11111";
        List<String> result = new ArrayList<>();
        process(num, "", result);
        for(String s:result){
            System.out.println(s);
        }
    }

    /**
     *
     * @param str 前面处理过后还剩余的数字字符串
     * @param path 前面挑选过后已经组成的字符串
     * @param result 最终的结果
     */
    public static void process(String str, String path, List<String> result){
        if(str==null || str.length()==0){
            result.add(path);
            return ;
        }

        //处理一个字符
        char one= (char)('A'-1+Integer.valueOf(str.substring(0,1)).intValue());
        process(str.substring(1), path+one, result);

        //处理2个字符
        if(str.length()>1){
            char two = (char)('A'-1+Integer.valueOf(str.substring(0,2)).intValue());
            if(Integer.valueOf(str.substring(0, 2))<=26){
                //只有两位数字小于等于26才可能转换成字母
                process(str.substring(2), path+two, result);
            }
        }
    }
}
