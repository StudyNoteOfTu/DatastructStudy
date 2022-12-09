package com.company.leetcode;


import com.company.leetcode.base.ListNode;

/**
 * 将两个升序链表合并为一个新的 升序 链表并返回。
 * 新链表是通过拼接给定的两个链表的所有节点组成的。
 */
public class code21 {
    public ListNode mergeTwoLists(ListNode list1,ListNode list2){
        if (list1==null) return list2;
        ListNode head = new ListNode(-1);
        ListNode cur = head;
        while (list1!=null&&list2!=null){
            if (list1.val<list2.val){
                cur.next = list1;
                list1 = list1.next;
            }else{
                cur.next=list2;
                list2 = list2.next;
            }
            cur = cur.next;
        }
        cur.next = list1==null?list2:list1;
        return head.next;
    }

}
