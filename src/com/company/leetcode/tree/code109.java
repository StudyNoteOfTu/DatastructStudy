package com.company.leetcode.tree;

import com.company.leetcode.base.ListNode;
import com.company.leetcode.base.TreeNode;

/**
 * 给定一个单链表的头节点  head ，其中的元素 按升序排序 ，将其转换为高度平衡的二叉搜索树。
 *
 * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差不超过 1。
 *
 * 与108题不同的是这里给的是单链表
 *
 */
public class code109 {
    /**
     * 方法：单链表要找到中间节点，可以使用两倍速的快慢指针
     */
    public TreeNode sortedListToBST(ListNode head) {
        //尾结点默认一定是null，第一波不需要快慢指针
        return process(head,null);
    }

    private TreeNode process(ListNode left,ListNode right) {
        //由于左闭右开，两个相等的时候，长度为0
        if (left==right){
            return null;
        }
        ListNode mid = getMidian(left,right);
        TreeNode root= new TreeNode(mid.val);//中间节点为root
        root.left= process(left,mid);
        root.right = process(mid.next,right);
        return root;
    }

    //获取中间节点
    private ListNode getMidian(ListNode left,ListNode right){
        ListNode fast = left;
        ListNode slow = left;
        while (fast!=right && fast.next!=right){
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }
}
