package com.company.leetcode.tree;

import com.company.leetcode.base.TreeNode;

//给你一个二叉树的根节点 root ， 检查它是否轴对称。
public class code101 {

    //遍历，对称操作
    public boolean isSymmetric(TreeNode root) {
        if (root==null)return true;
        return process(root.left,root.right);
    }

    private boolean process(TreeNode left,TreeNode right) {
        if (left==null && right==null) return true;
        if (left==null || right==null){
            return false;
        }
        if (left.val!=right.val){
            return false;
        }
        return process(left.left,right.right)&&
        process(left.right,right.left);
    }


}
