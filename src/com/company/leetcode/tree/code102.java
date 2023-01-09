package com.company.leetcode.tree;

import com.company.leetcode.base.TreeNode;

import java.lang.reflect.Array;
import java.util.*;

/**
 * 给你二叉树的根节点 root ，返回其节点值的 层序遍历 。
 * （即逐层地，从左到右访问所有节点）。
 */
public class code102 {




    /**
     * 宽度优先遍历BFS
     * 使用队列:一个出来，把左右孩子放入
     * 分层方式1：hashMap
     * 分层方式2：标记记录本层最后一个元素
     * 分层方法3：记录queue的现有数量，来表示本层数量
     *
     * 深搜使用栈
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        Queue<TreeNode> queue = new ArrayDeque<>();
        if (root !=null){
            queue.add(root);
        }
        while (!queue.isEmpty()){
            //先记下当前层的数量
            int n = queue.size();
            List<Integer> level = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                TreeNode node = queue.poll();
                level.add(node.val);
                if (node.left!=null){
                    queue.add(node.left);
                }
                if (node.right!=null){
                    queue.add(node.right);
                }
            }
            res.add(level);
        }
        return res;
    }
    public List<List<Integer>> levelOrder2(TreeNode root) {
        TreeNode curEnd = root;
        TreeNode nextEnd = root;

        List<List<Integer>> list = new ArrayList<>();
        if (root == null) return list;
        int level=0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            TreeNode poll = queue.poll();
            //添加数据
            if (list.size()-1<level){
                list.add(new ArrayList<>());
            }
            list.get(level).add(poll.val);

            //入队
            if (poll.left!=null){
                queue.add(poll.left);
                nextEnd = poll.left;
            }
            //入队
            if (poll.right!=null){
                queue.add(poll.right);
                nextEnd = poll.right;
            }

            //判断是否到了当前层的最后一个
            //如果是，将nextEnd设置为新的当前层最后一个。层数+1
            if (poll==curEnd){
                curEnd = nextEnd;
                nextEnd = null;
                level++;
            }
        }

        return list;
    }
}
