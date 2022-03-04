package com.wcn.algorithm.recursive;

/**
 * 从左往右的尝试模型2
 * 给定两个长度都为N的素组weights和values
 * weights[i]和values[i]分别表示i号物品的重量和价值。
 * 给一定正数bag，表示一个载重bag的背包，装的物品重量不能操作bag。
 * 返回能下下最多的价值是多少
 */
public class Bag {

    public static void main(String[] args) {
        int[] weights = new int[]{1,2,3,4,5};
        int[] values = new int[]{1,2,3,54,50};
        int result = process(weights, values, 6, 0, 0, 0);
        System.out.println(result);
    }

    /**
     * 每个物品选择要和不要
     * @param weights
     * @param values
     * @param bag 背包限制
     * @param index 已经该处理哪个所谓位置的物品
     * @param weightAlready 选择要的物品已经有多重了
     */
    public static int process(int[] weights, int[] values, int bag, int index, int weightAlready, int valuesAlready){
        if(index==weights.length){
            //没有物品了
            if(weightAlready>bag){
                return 0;
            }else{
                return valuesAlready;
            }
        }

        //不要改物品
        int yes = process(weights, values, bag, index+1, weightAlready, valuesAlready);

        //要该物品
        int no = process(weights, values, bag, index+1, weightAlready+weights[index], valuesAlready+values[index]);

        return Math.max(yes, no);
    }
}
