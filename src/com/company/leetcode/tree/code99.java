package com.company.leetcode.tree;

import com.company.leetcode.base.TreeNode;
import com.sun.org.apache.bcel.internal.classfile.Code;

/**
 * 给你二叉搜索树的根节点 root ，该树中的 恰好 两个节点的值被错误地交换。
 * 请在不改变其结构的情况下，恢复这棵树 。
 */
public class code99 {

    public int lastInt = -Integer.MAX_VALUE;
    public int p1= -Integer.MAX_VALUE;
    public int p2=-Integer.MAX_VALUE;

    public void recoverTree(TreeNode root) {
        process1(root);
        process2(root);
    }

    private void process2(TreeNode root) {
        if (root == null) return;
        process2(root.left);
        if (root.val==p1){
            root.val = p2;
        }else if (root.val == p2){
            root.val = p1;
        }
        process2(root.right);
    }

    //避免二次遍历，可以在判断的时候把出问题的node记下来，后续直接改node，不用再遍历一遍
    private void process1(TreeNode root) {
        if (root == null)return;
        process1(root.left);
        if (lastInt > root.val){
            p1 = p1==-Integer.MAX_VALUE?lastInt:p1;
            p2 = root.val;
        }
        lastInt = root.val;
        process1(root.right);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(3);
        root.left.right = new TreeNode(2);
        printTree(root);
        new code99().recoverTree(root);
        printTree(root);
    }
    public static void printTree(TreeNode root){
        if (root == null) return;
        printTree(root.left);
        System.out.print(root.val+"-");
        printTree(root.right);
    }
}
