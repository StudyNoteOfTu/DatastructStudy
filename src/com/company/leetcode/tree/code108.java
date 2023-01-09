package com.company.leetcode.tree;

import com.company.leetcode.base.TreeNode;

/**
 * 给你一个整数数组 nums ，其中元素已经按 升序 排列，
 * 请你将其转换为一棵 高度平衡 二叉搜索树。
 *
 * 高度平衡 二叉树是一棵满足「每个节点的左右两个子树的高度差的绝对值不超过 1 」的二叉树。
 *
 */
public class code108 {

    //左闭右开做法：
    private TreeNode process2(int[] nums, int L, int R){
        if (L==R){
            return null;
        }
        int midIndex = (L+R)/2;
        TreeNode root = new TreeNode(nums[midIndex]);
        root.left = process2(nums,L,midIndex);
        root.right = process2(nums,midIndex+1,R);
        return root;
    }


    //由于已经有序，就可以构建左右子树
    public TreeNode sortedArrayToBST(int[] nums) {
        return process2(nums,0,nums.length);
    }

    //先拿中间那值，左边给左子树，右边右子树，只有一个就为叶子
    //左闭右闭
    private TreeNode process(int[] nums,int L, int R) {
        if (L>R){
            return null;//空
        }
        if (L==R){//叶子节点
            return new TreeNode(nums[L]);
        }
        //还有子树
        //先获取中间值
        int midIndex = (R+L)/2;
        TreeNode root = new TreeNode(nums[midIndex]);
        //构建左右子树
        root.left =process(nums,L,midIndex-1);
        root.right = process(nums,midIndex+1,R);
        return root;
    }

}
