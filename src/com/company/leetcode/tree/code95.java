package com.company.leetcode.tree;

import com.company.basic.class06Tree.BinaryTree;
import com.company.leetcode.base.TreeNode;
import sun.reflect.generics.tree.Tree;

import java.util.*;

/**
 * 给你一个整数 n ，
 * 请你生成并返回所有由 n 个节点组成且节点值从 1 到 n 互不相同的不同 二叉搜索树 。
 * 可以按 任意顺序 返回答案
 */
public class code95 {

    /**
     * 引出其他问题，树的克隆
     */
    public static TreeNode cloneTree(TreeNode root){
        TreeNode newTree = new TreeNode(root.val);
        if (root.left!=null){
            newTree.left = cloneTree(root.left);
        }
        if (root.right!=null){
            newTree.right = cloneTree(root.right);
        }
        return newTree;
    }

    public static void main(String[] args) {
        TreeNode n = new TreeNode(1);
        n.left = new TreeNode(2);
        n.right = new TreeNode(3);
        n.left.left = new TreeNode(4);
        n.left.right = new TreeNode(5);
        printf(n);
        System.out.println(
        );
        printf(cloneTree(n));
    }


    public static void printf(TreeNode n){
        if (n==null)return;
        System.out.print(n.val+"-");
        printf(n.left);
        printf(n.right);
    }





    /**
     * 题解：
     * 左子树的节点集合为 1...i-1
     * 右子树的节点集合为 i+1...n
     * 可以是一个序列长度缩小的问题，用回溯法
     */
    public List<TreeNode> generateTrees(int n){
        if (n == 0){
            return new LinkedList<>();
        }
        return generateTrees(1,n);
    }

    public List<TreeNode>  generateTrees(int start,int end){
        List<TreeNode> allTrees = new LinkedList<>();
        if (start > end){
            allTrees.add(null);
            return allTrees;
        }
        for (int i = start ; i <= end ; i++){
            //获取所有可行的左子树集合
            List<TreeNode> leftTrees = generateTrees(start,i-1);
            //获取所有可行的右子树集合
            List<TreeNode> rightTrees = generateTrees(i+1,end);
            //从左子树集合中挑一个子树，右子树集合中挑一个子树，拼到根节点上
            for (TreeNode leftTree : leftTrees) {
                for (TreeNode rightTree : rightTrees) {
                    TreeNode curTree = new TreeNode(i);
                    curTree.left =leftTree;
                    curTree.right = rightTree;
                    allTrees.add(curTree);
                }
            }
        }
        //向上返回可行的所有子树
        return allTrees;
    }






}
