package com.wcn.algorithm.recursive;

/**
 * 范围上尝试的模型
 * 给定一个整数型数组arr，代表数值不通的纸牌排成一条线。
 * 玩家A和B一次拿走每张纸牌，规定玩家A先拿牌，玩家B后拿，
 * 但是每个玩家每次只能拿走最左或者最右的纸牌，
 * 玩家A和玩家B都是绝顶聪明。请返回最后或者这的分数。
 */
public class PlayingCard {

    public static void main(String[] args) {
//        int[] array = new int[]{1,100,1};
        int[] array = new int[]{1,100,1,70};

        //先手拿和后手拿取最大值即可
        int first = processFirst(array, 0, array.length-1);
        int second = processSecond(array, 0, array.length-1);
        System.out.println(first+"  "+second);
    }

    /**
     * 先手拿牌逻辑
     * @param array 所有的卡牌
     * @param left 剩余的卡牌左侧索引位置
     * @param right 剩余的卡牌右侧索引位置
     * @return
     */
    public static int processFirst(int[] array, int left, int right){
        if(left==right){
            //最后一张牌，因为是先手，就拿走这张牌
            return array[left];
        }

        //拿了左侧的牌，后续该后手拿牌
        int resultLeft = array[left] + processSecond(array, left+1, right);

        //拿了右侧的牌，后续该后手拿牌
        int resultRight = array[right] + processSecond(array, left, right-1);
        return Math.max(resultLeft, resultRight);
    }


    /**
     * 后手拿牌逻辑
     * @param array
     * @param left
     * @param right
     * @return
     */
    public static int processSecond(int[] array, int left, int right){
        if(left==right){
            //最后一张牌，因为是后手，没得拿
            return 0;
        }

        //先手拿完牌了，其实就该后手作为先手来拿牌了
        int resultLeft = processFirst(array, left+1, right);//先手的人拿走了left，后手只能从left+1到right拿。left的牌面后手的人不能加上去
        int resultRight = processFirst(array, left, right-1);//先手的人拿走了right，后手的只能从left到right-1拿，right的牌面后手的人不能加上去。

        return Math.min(resultLeft, resultRight);//因为是后手，先手肯定是让你拿到最小的值
    }
}
