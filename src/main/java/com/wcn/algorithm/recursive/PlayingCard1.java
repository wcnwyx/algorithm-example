package com.wcn.algorithm.recursive;

/**
 * 范围上尝试的模型
 * 给定一个整数型数组arr，代表数值不通的纸牌排成一条线。
 * 玩家A和B一次拿走每张纸牌，规定玩家A先拿牌，玩家B后拿，
 * 但是每个玩家每次只能拿走最左或者最右的纸牌，
 * 玩家A和玩家B都是绝顶聪明。请返回最后或者这的分数。
 *
 * 这个方法是错误的，这种递归只是找出了所有的拿牌顺序中，先手可以拿到的最大牌
 * 违背了题意（两人都是决定聪明的），比如说1，100，1三张牌，先手第一次拿牌，只能拿左右两边的1，那么后手肯定拿100，肯定是后手赢的。
 */
public class PlayingCard1 {

    public static void main(String[] args) {
//        int[] array = new int[]{1,100,1};
        int[] array = new int[]{1,100,1,70};
        int[] result = process(array, 0, array.length-1);
        System.out.println(result[0]+" "+result[1]);
    }

    /**
     * @param array 所有的卡牌
     * @param left 剩余的卡牌左侧索引位置
     * @param right 剩余的卡牌右侧索引位置
     * @return int[0] 先手的值，int[1]后手的值
     */
    public static int[] process(int[] array, int left, int right){
        if(left+1==right){
            //最后2张牌，先手肯定拿大的，后手只能拿小的
            return new int[]{Math.max(array[left], array[right]), Math.min(array[left], array[right])};
        }else if(left==right){
            //总数为单数张牌，最后就剩下一张牌，先手肯定拿走了，后手就是0
            return new int[]{array[left], 0};
        }


        //先手拿left,后手拿left
        int[] ll = process(array, left+2, right);
        ll[0]+=array[left];
        ll[1]+=array[left+1];

        //先手拿left,后手拿right
        int[] lr = process(array, left+1, right-1);
        lr[0]+=array[left];
        lr[1]+=array[right];

        //先手拿right,后手拿left
        int[] rl = process(array, left+1, right-1);
        rl[0]+=array[right];
        rl[1]+=array[left];

        //先手拿right，后手也拿right
        int[] rr = process(array, left, right-2);
        rr[0]+=array[right];
        rr[1]+=array[right-1];

        //从ll\lr\rl\rr四个组合中挑选一个先手最大的
        int[] max = ll;
        if(lr[0]>max[0]){
            max = lr;
        }
        if(rl[0]>max[0]){
            max = rl;
        }
        if(rr[0]>max[0]){
            max = rr;
        }
        return max;
    }
}
