package com.company.basic.class06Tree;

import com.company.leetcode.base.TreeNode;

public class IsFullBT {
    /**
     * 1. 求最大深度l
     * 2. 求节点数n
     * 3. 上述满足 n = 2^l -1 则为满二叉树
     */
    /**
     * 树形DP套路 获取底层信息 自己处理 向上返回
     *
     * 不符合情况：需要整棵树来看的，不是单独看左右树信息可以得出来的。
     */
    public static class Info{
        public int height;
        public int nodes;

        public Info(int height, int nodes) {
            this.height = height;
            this.nodes = nodes;
        }
    }

    public static Info process(TreeNode x){
        if (x==null) return new Info(0,0);
        Info leftData = process(x.left);
        Info rightData=  process(x.right);
        //处理信息
        int height= Math.max(leftData.height,rightData.height)+1;
        int nodes = leftData.nodes + rightData.nodes+1;
        return new Info(height,nodes);
    }

    public static boolean isFullBT(TreeNode head){
        if (head == null) return true;
        Info data = process(head);
        return data.nodes == (2^data.height-1);
    }
}
