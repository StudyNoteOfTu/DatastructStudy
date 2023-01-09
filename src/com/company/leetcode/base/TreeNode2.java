package com.company.leetcode.base;

public class TreeNode2 {
    public int val;
    public TreeNode2 left;
    public TreeNode2 right;
    public TreeNode2 parent;

    public TreeNode2(int val, TreeNode2 left, TreeNode2 right, TreeNode2 father) {
        this.val = val;
        this.left = left;
        this.right = right;
        this.parent = father;
    }
}
