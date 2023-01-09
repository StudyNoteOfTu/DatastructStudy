package com.company.leetcode.tree;

import com.company.leetcode.base.ListNode;
import com.company.leetcode.base.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 二叉树的中序遍历
 */
public class code94 {

    /**
     * 方法1，递归
     */
    public List<Integer> inorderTraversal1(TreeNode root) {
        List<Integer> print = new ArrayList<>();
        process(root,print);
        return print;
    }

    public void process(TreeNode root,List<Integer> list){
        if (root==null)return;
        if (root.left!=null) process(root.left,list);
        list.add(root.val);
        if (root.right!=null) process(root.right,list);
    }


    /**
     * 非递归，压栈
     * 中序遍历
     * 先压右再压左
     */
    public List<Integer> inorderTraversal(TreeNode root){
        List<Integer>  result = new ArrayList<>();
        if (root == null) return result;
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || root!=null){
            if (root !=null){
                //压自己
                stack.push(root);
                root = root.left;
            }else{
                //如果没了，回弹一下
                TreeNode pop = stack.pop();
                //打印
                result.add(pop.val);
                //变为右树
                root = pop.right;
            }
        }
        return result;
    }
}
