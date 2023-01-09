package com.company.leetcode.tree;

import com.company.leetcode.base.TreeNode;

import java.util.*;

/**
 * 给你二叉树的根节点 root ，返回其节点值 自底向上的层序遍历 。
 * （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
 */
public class code107 {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        Queue<TreeNode> queue = new ArrayDeque<>();
        Deque<List<Integer>> list = new ArrayDeque<>();
        if (root !=null){
            queue.add(root);
        }
        while (!queue.isEmpty()){
            //层序遍历即BFT宽度优先遍历
            int n = queue.size();
            List<Integer> level = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                TreeNode node = queue.poll();
                level.add(node.val);
                if (node.left!=null) queue.add(node.left);
                if (node.right!=null) queue.add(node.right);
            }
            list.addFirst(level);
        }
        return new ArrayList<>(list);
    }
}
