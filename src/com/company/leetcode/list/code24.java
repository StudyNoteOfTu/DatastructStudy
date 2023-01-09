package com.company.leetcode.list;


import com.company.leetcode.base.ListNode;

/**
 * 给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。
 * 你必须在不修改节点内部的值的情况下完成本题（即，只能进行节点交换）
 */
public class code24 {

    public ListNode swapPairs(ListNode head){
        if (head==null || head.next==null) return head;
        ListNode pre = new ListNode(0);
        ListNode root = pre;
        pre.next = head;
        ListNode n1 = head;
        ListNode n2 = head.next;
        ListNode next = null;

        while (n1!=null&&n2!=null){
            next = n2.next;
            n2.next = n1;
            n1.next = next;
            pre.next = n2;
            pre= n1;
            n1 = next;
            n2 = n1==null?null:n1.next;
        }
        return root.next;
    }
}
