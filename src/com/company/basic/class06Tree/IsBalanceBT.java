package com.company.basic.class06Tree;

import com.company.leetcode.base.TreeNode;

public class IsBalanceBT {
    /**
     * 1. 左子树 平√
     * 2. 右子树 平√
     * 3. |左高 - 右高| <=1
     * <p>
     * 需要信息： 是否平，高度是多少，左右树提供的一样，这是递归套路
     */
    public static boolean isBalanced(TreeNode head) {
        return process(head).isBalanced;
    }

    //返回两个东西
    public static class ReturnType {
        public boolean isBalanced;
        public int height;

        public ReturnType(boolean isBalanced, int height) {
            this.isBalanced = isBalanced;
            this.height = height;
        }
    }

    public static ReturnType process(TreeNode x) {
        if (x == null) return new ReturnType(true, 0);

        ReturnType leftData = process(x.left);
        ReturnType rightData = process(x.right);

        //加上自己的高度，加工出我自己的高度
        int height = Math.max(leftData.height,rightData.height)+1;
        //平衡条件
        boolean isBalanced = leftData.isBalanced && rightData.isBalanced &&
                Math.abs(leftData.height - rightData.height)<2;
        return new ReturnType(isBalanced,height);
    }

}
