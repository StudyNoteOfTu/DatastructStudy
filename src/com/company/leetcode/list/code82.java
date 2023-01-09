package com.company.leetcode.list;


import com.company.leetcode.base.ListNode;

/**
 * 给定一个已排序的链表的头 head ，
 * 删除原始链表中所有重复数字的节点，只留下不同的数字 。返回 已排序的链表 。
 */
public class code82 {

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) return null;
        ListNode pre = new ListNode();
        ListNode root = pre;
        ListNode cur = head;
        ListNode next = cur;
        //找到第一个不一样的
        while (cur != null) {
            while (next.next != null && next.next.val == cur.val) {
                next = next.next;
            }
            //如果next是自己,即没重复，pre连向它,并更新pre，cur,next
            if (next == cur) {
                pre.next = cur;
                pre = cur;
                cur = pre.next;
                next = cur;
            }else{
                //有重复，pre.next更新(跳过重复的内容），把cur，和next更新
                pre.next = next.next;
                cur=next.next;
                next = cur;
            }
        }
        return root.next;
    }

}
