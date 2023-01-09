package com.company.basic.class06Tree;


/**
 * 折纸问题
 * 请把一段纸条竖着放在桌子上，然后从纸条的下边向上方对折1次，压出折痕后
 * 展开。
 * 此时折痕是凹下去的，即折痕突起的方向指向纸条的背面。
 * 如果从纸条的下边向上方连续对折2次，压出折痕后展开，此时有三条折痕，从
 * 上到下依次是下折痕、下折痕和上折痕。
 * 给定一个输入参数N，代表纸条都从下边向上方连续对折N次。
 * 请从上到下打印所有折痕的方向。
 * 例如:N=1时，打印: down N=2时，打印: down down up
 */
public class PaperFolding {


    public static void printAllFolds(int N){
        printProcess(1,N,true);
    }

    //递归过程，来到了某一个节点
    //i是节点的层数，N一共的层数，down==true 凹 down == false 凸
    //默认了满二叉树
    private static void printProcess(int i, int N, boolean down) {
        //还有孩子
        if (i>N)return;
        //左孩子
        printProcess(i+1,N,true);
        //中序遍历
        System.out.println(down?"凹":"凸");
        //右孩子
        printProcess(i+1,N,false);
    }
}
