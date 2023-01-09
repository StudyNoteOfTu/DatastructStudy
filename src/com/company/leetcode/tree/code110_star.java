package com.company.leetcode.tree;

import com.company.leetcode.base.TreeNode;

/**
 * 给定一个二叉树，判断它是否是高度平衡的二叉树。
 *
 * 本题中，一棵高度平衡二叉树定义为：
 *
 * 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1 。
 */
public class code110_star {

    public boolean isBalanced(TreeNode root) {
        return process(root);
    }

    //高度差的绝对值不超过1
    //方法1：自顶向下；获取左右子树高度，做差，对左右子树分别进行这个操作
    private boolean process(TreeNode root) {
        if (root==null) return true;
        if (Math.abs(getHeight(root.left)-getHeight(root.right))>1){
            return false;
        }
        return process(root.left) && process(root.right);
    }

    private int getHeight(TreeNode root){
        if (root == null) return 0;
        return 1+Math.max(getHeight(root.left),getHeight(root.right));
    }


    //方法2：自底向上
    //先递归判断左右子树是否平衡，在判断当前节点为根的子树是否平衡。
    // 如果一科子树是平衡的，则返回高度，否则返回-1，如果存在一颗子树不平衡，
    //则整棵二叉树一定不平衡
    public boolean isBalanced2(TreeNode root){
        return height(root) >=0;
    }

    private int height(TreeNode root) {
        if (root == null) return 0;//子树高度为0
        int leftHeight = height(root.left);
        int rightHeight = height(root.right);
        //如果底下返回的数据是-1，表示有子树不平衡，继续往上返回-1
        if (leftHeight==-1||rightHeight==-1||Math.abs(leftHeight-rightHeight)>1){
            return -1;
        }else{
            //如果一切正常，则返回当前这个节点作为根的树的高度，供上层计算是否平衡
            return Math.max(leftHeight, rightHeight) + 1;
        }
    }
}
