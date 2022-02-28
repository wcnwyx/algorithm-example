package com.wcn.algorithm.tree;

/**
 * 请把一段纸条竖着放在桌子上，然后从纸条的下边向上方对折1次，压出折痕后展开。
 * 此时折痕是凹下去的，即折痕凸起的方向指向纸条的背面。如果从纸条的下边向上方连续对折2此，压出折痕后展开，
 * 此时有三条折痕，从上到下依次是下折痕、下折痕和上折痕。
 * 给定一个输入参数N，代表纸条都从下边向上方连续对折N次。请从上到下打印所有折痕的方向。
 * 例如：N=1 打印：down N=2时，打印down down up
 *
 * 最终结构可以构建出来一棵满二叉树，N即为层数，根节点是down，然后每个节点的左子节点都是down，右子节点是up，输出顺序即为该树的中序遍历。
 * 递归实现更简单：每次折叠后，下次折痕在本次折痕的上方固定是down，本次折痕的下方固定是up
 */
public class PaperFold {

    public static void main(String[] args) {
        print(1, 3, true);
    }

    /**
     * 树的中序遍历实现
     * @param i 第几次折叠
     * @param N
     * @param isDown
     */
    private static void print(int i, int N, boolean isDown){
        if(i>N){
            return ;
        }
        print(i+1, N, true);//本次折痕的上方都是down，即处理树的左节点
        System.out.print(isDown?"down ":"up ");
        print(i+1, N, false);//本次折痕的下方都是up，即处理树的右节点
    }
}
