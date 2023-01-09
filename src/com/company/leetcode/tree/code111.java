package com.company.leetcode.tree;


import com.company.leetcode.base.TreeNode;

/**
 * 给定一个二叉树，找出其最小深度。
 *
 * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
 *
 * 说明：叶子节点是指没有子节点的节点
 */

public class code111 {

    //方法1: DFS 深度优先
    //方法2：BFS 找到的第一个叶子节点的深度就是最小深度
    public int minDepth(TreeNode root) {
        if (root==null)return 0;
        return process(root);
    }

    //向上返回左右子树最小深度,只有遇到叶子节点才向上返回
    private int process(TreeNode root) {
        if (root.left==null && root.right==null){
            //如果遇到了叶子节点
            return 1;
        }
        //如果不是叶子节点,就继续计算
        int leftHeight = Integer.MAX_VALUE;
        int rightHeight = Integer.MAX_VALUE;
        if (root.left!=null){
            leftHeight = process(root.left);
        }
        if (root.right!=null){
            rightHeight = process(root.right);
        }

        return 1+Math.min(leftHeight,rightHeight);
    }
}
