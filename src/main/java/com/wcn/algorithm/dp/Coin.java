package com.wcn.algorithm.dp;

/**
 * 使用不同个数的硬币组成固定的面额，计算有多少种组合：
 * 硬币数组 int[] coins，里面的值表示每种硬币的面额，大于0，且不重复，每个面值的硬币不限使用次数
 * int amount 为需要组成的目标金额
 */
public class Coin {
    public static void main(String[] args) {
        int[] coins = new int[]{1, 3, 5, 7, 10};
        int amount = 200;
        System.out.println(method1(coins, amount));
        System.out.println(method2(coins, amount));
        System.out.println(method3(coins, amount));
        System.out.println(method4(coins, amount));
    }


    public static int method1(int coins[], int amount){
        return process1(coins, 0, amount);
    }
    /**
     * 暴力递归实现
     * @param coins
     * @param index 当前使用的是coins里的第几个索引为的面值
     * @param amountLast 剩余目标金额
     * @return 1表示这种组法可行，0表示组不成
     */
    public static int process1(int[] coins, int index, int amountLast){
        if(index==coins.length){
            return amountLast==0?1:0;
        }
        int ret = 0;
        int coinAmount = coins[index];//当前index位置的硬币面值
        //循环个数，表示第index位置的面值硬币使用的个数，个数*面值不能大于剩余目标金额
        for(int i=0;i*coinAmount<=amountLast;i++){
            ret += process1(coins, index+1, amountLast-(i*coinAmount));
        }
        return ret;
    }


    public static int method2(int[] coins, int amount){
        int[][] cache = new int[coins.length+1][amount+1];
        //将cache中的数据初始化为-1，因为0是一个正常的值
        for(int i=0;i<=coins.length;i++){
            for(int j=0;j<=amount;j++){
                cache[i][j] = -1;
            }
        }
        return process2(coins, 0, amount, cache);
    }

    /**
     * 记忆搜索实现
     * 递归计算的过程中，将index和amountLast不同的组合计算结果记录到缓存，如果缓存中有表示计算过，直接拿来用即可。
     * 因为暴力递归的时候，有很多重复的计算，使用缓存可以减少重复计算。
     * @param coins
     * @param index
     * @param amountLast
     * @param cache
     * @return
     */
    public static int process2(int[] coins, int index, int amountLast, int[][] cache){
        if(cache[index][amountLast]!=-1){
            //缓存中有数据，表示该情况以前计算过，直接返回
            return cache[index][amountLast];
        }

        if(index==coins.length){
            cache[index][amountLast] = amountLast==0?1:0;
            return cache[index][amountLast];
        }
        int ret = 0;
        int coinAmount = coins[index];//当前index位置的硬币面值
        //循环个数，表示第index位置的面值硬币使用的个数，个数*面值不能大于剩余目标金额
        for(int i=0;i*coinAmount<=amountLast;i++){
            ret += process1(coins, index+1, amountLast-(i*coinAmount));
        }
        cache[index][amountLast] = ret;
        return ret;
    }


    /**
     * 动态规划实现（有枚举的情况，就是这个面额的硬币从0张主键增加）
     * @param coins
     * @param amount
     * @return
     */
    public static int method3(int[] coins, int amount){
        int[][] dp = new int[coins.length+1][amount+1];
        dp[coins.length][0] = 1;

        for(int index=coins.length-1; index>=0; index--){
            for(int amountLast=0; amountLast<=amount; amountLast++){
                int ret = 0;
                int coinAmount = coins[index];//当前index位置的硬币面值
                //循环个数，表示第index位置的面值硬币使用的个数，个数*面值不能大于剩余目标金额
                for(int i=0;i*coinAmount<=amountLast;i++){
                    ret += dp[index+1][amountLast-(i*coinAmount)];
                }
                dp[index][amountLast] = ret;
            }
        }

        return dp[0][amount];
    }

    /**
     * 动态规划实现(取消掉枚举的情况)
     * @param coins
     * @param amount
     * @return
     */
    public static int method4(int[] coins, int amount){
        int[][] dp = new int[coins.length+1][amount+1];
        dp[coins.length][0] = 1;

        for(int index=coins.length-1; index>=0; index--){
            for(int amountLast=0; amountLast<=amount; amountLast++){
                int coinAmount = coins[index];//当前index位置的硬币面值
                dp[index][amountLast] = dp[index+1][amountLast];
                if(amountLast - coinAmount >= 0) {
                    dp[index][amountLast] += dp[index][amountLast-coinAmount];
                }
            }
        }

        return dp[0][amount];
    }

}
