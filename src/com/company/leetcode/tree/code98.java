package com.company.leetcode.tree;


import com.company.leetcode.base.TreeNode;

/**
 * 给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。
 *
 * 有效 二叉搜索树定义如下：
 *
 * 节点的左子树只包含 小于 当前节点的数。
 * 节点的右子树只包含 大于 当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 *
 */
public class code98 {

    public boolean firstTime = true;
    public int lastInt = 0;
    public boolean isOK = true;

    //尝试方法：中序遍历递增
    public boolean isValidBST(TreeNode root) {
        process(root);
        return isOK;
    }

    public void process(TreeNode root){
        if (root == null) return;
        process(root.left);
        if (lastInt >= root.val && !firstTime){
            isOK = false;
        }
        if (firstTime) firstTime = false;
        lastInt = root.val;
        process(root.right);
    }
}
