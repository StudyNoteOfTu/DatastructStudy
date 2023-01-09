package com.company.basic.class06Tree;

import com.company.leetcode.base.TreeNode;

import java.util.HashMap;
import java.util.HashSet;

/**
 * 给定两个二叉树的节点node1和node2，找到他们的最低公共祖先节点
 */
public class GetPublicAncientNode {

    //用HashMap+遍历一棵树来构造父节点关系（用于回溯）

    //o1 o2一定属于head为头的树
    public static TreeNode lowestCommonAncestor(TreeNode head, TreeNode o1, TreeNode o2){
        //方法1：生成父节点的表
        HashMap<TreeNode,TreeNode> fatherMap = new HashMap<>();
        fatherMap.put(head,head);
        process(head,fatherMap);
        //记录o1整条链的父节点
        HashSet<TreeNode> set1 = new HashSet<>();
        TreeNode cur = o1;
        while (cur!=fatherMap.get(cur)){
            set1.add(cur);
            cur = fatherMap.get(cur);
        }
        set1.add(head);
        //在让o2往上回溯，每回溯一个，都看一下在不在set1中，如果在，就是最近祖先
        TreeNode cur2 = o2;
        while (cur2!=fatherMap.get(cur2)){
            if (set1.contains(cur2))return cur2;
            cur2 = fatherMap.get(cur2);
        }

        //记录o2整条链的父节点

        return null;
    }
    //先序遍历
    private static void process(TreeNode head, HashMap<TreeNode, TreeNode> fatherMap) {
        if (head == null)return;
        fatherMap.put(head.left,head);
        fatherMap.put(head.right,head);
        process(head.left,fatherMap);
        process(head.right,fatherMap);
    }


    //方法2

    /**
     * 情况1： o1是o2的最低公共祖先，或者反过来。
     * 情况2： 彼此不互为最低公共祖先，通过往上汇聚才能找到最低公共祖先
     */
    public static TreeNode lowestAncestor(TreeNode head, TreeNode o1,TreeNode o2){
        if (head==null || head==o1 || head==o2) return head;
        TreeNode left = lowestAncestor(head.left,o1,o2);
        TreeNode right = lowestAncestor(head.right,o1,o2);
        //如o1和o2都在左树上，假设left返回o1，right返回null，最后返回o1（情况1 下面这个if绝对不会成功）
        //（情况2 当左树返回值右树返回值都不为空的时候，也就是一边o1一边o2)我把自己返回回去，
        // 所有回溯到的父节点将把它一路往上扔，因为其他半边都是null
        if (left!=null && right!=null){
            return head;
        }
        //左右两棵树，并不都有返回值
        //返回不空的那一半
        return left!=null?left:right;
    }
}
