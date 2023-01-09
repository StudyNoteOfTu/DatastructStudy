package com.company.basic.class06Tree;


import com.company.leetcode.base.TreeNode;

import java.util.Stack;

/**
 * BST ： 搜索二叉树，左树都比自己小，右树都比自己大
 * 判断是否是BST：中序遍历，不降序（如果元素不重复，就是升序）（
 */
public class IsBST {


    public static int preValue = Integer.MIN_VALUE;

    //中序遍历，保证升序，则为搜索二叉树
    public static boolean isBST(TreeNode head) {
        if (head == null) {
            return true;
        }
        boolean isLeftBST = isBST(head.left);
        if (!isLeftBST) {
            return false;
        }
        //中序这里原来是打印动作，把打印变成了处理行为
        if (head.val <= preValue) {//判断是否是递增的（和上一个中序遍历打印的节点比较）
            return false;
        } else {
            preValue = head.val;
        }

        return isBST(head.right);
    }

    //非递归方法
    public static boolean isBST2(TreeNode head) {
        if (head == null) {
            return true;
        }
        int preValue = Integer.MIN_VALUE;
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || head != null) {
            if (head != null) {
                stack.push(head);
                head = head.left;
            } else {
                head = stack.pop();
                //检查时机
                if (head.val <= preValue) {
                    return false;
                } else {
                    preValue = head.val;
                }
                head = head.right;
            }
        }
        return true;
    }


    //方法二：左右树要信息

    /**
     * 1. 左树是搜索二叉树
     * 2. 右树是搜索二叉树
     * 3. 左树max 小于自己
     * 4. 右树min 大于自己
     * 左树： 是否是搜索二叉树，以及最大值
     * 右树： 是否是搜索二叉树，以及最小值
     * 两个内容不一样，如何形成递归： 合并要求，求全集
     */
    public static class ReturnType {
        public boolean isBST;
        public int min;
        public int max;

        public ReturnType(boolean isBST, int min, int max) {
            this.isBST = isBST;
            this.min = min;
            this.max = max;
        }
    }

    public static ReturnType process(TreeNode x) {
        if (x == null) {
            return null;
        }
        ReturnType leftData = process(x.left);
        ReturnType rightData = process(x.right);

        //我自己要向上返回的信息
        boolean isBST = true;
        int min;
        int max;
        min = x.val;
        max = x.val;
        //最小为左树最小，右树最小和自己三个相比
        //最大同理
        if (leftData != null) {
            min = Math.min(min, leftData.min);
            max = Math.max(max, leftData.max);
        }
        if (rightData != null) {
            min = Math.min(min, rightData.min);
            max = Math.max(max, rightData.max);
        }
        //违规条件： 1. 左树不是二叉树 2.左树最大值比x还大
        if (leftData != null && (!leftData.isBST || leftData.max >= x.val)) {
            isBST = false;
        }
        if (rightData != null && (!rightData.isBST || x.val >= rightData.min)) {
            isBST = false;
        }


        return new ReturnType(isBST, min, max);

    }

}
