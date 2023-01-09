package com.company.leetcode.tree;

import com.company.leetcode.base.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 给你两棵二叉树的根节点 p 和 q ，编写一个函数来检验这两棵树是否相同。
 * <p>
 * 如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的
 */
public class code100 {

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if ((p == null && q != null) || (p != null && q == null)) {
            return false;
        }
        if (p == null) return true;
        if (p.val != q.val) return false;
        return isSameTree(p.left, q.left) &&
                isSameTree(p.right, q.right);
    }


    public static void main(String[] args) {
        TreeNode p = new TreeNode(1);
        p.left = new TreeNode(2);

        TreeNode q = new TreeNode(1);
        q.right = new TreeNode(2);

        new code100().isSameTree(p, q);
    }
}
