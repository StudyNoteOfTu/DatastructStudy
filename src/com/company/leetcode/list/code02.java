package com.company.leetcode.list;

import com.company.leetcode.base.ListNode;

/**
 * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
 *
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 *
 * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 *
 *1
 */
public class code02 {//pass

    //改进：直接在一个链表上完成加法操作，不用另外开空间，无需反转链表
    //1..加法运算
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2){
        if (l1==null || l2==null) return l1==null? l2:l1;
//        l1 = reverseLinkList(l1);
//        l2 = reverseLinkList(l2);

        ListNode head = new ListNode((l1.val+l2.val)%10);
        ListNode cur = head;
        int carry = (l1.val+l2.val)/10;
        l1 = l1.next;
        l2 = l2.next;
        while (l1!=null && l2!=null){
            head.next = new ListNode((l1.val+l2.val+carry)%10);
            carry = (l1.val+l2.val+carry)/10;
            l1 = l1.next;
            l2 = l2.next;
            head = head.next;
        }
        //总有一个先到null，如果l2先到null，把l1的后续全都加上
        while (l1!=null){
            head.next = new ListNode((l1.val+carry)%10);
            carry= (l1.val+carry)/10;
            l1 = l1.next;
            head = head.next;
        }
        while (l2 != null) {
            head.next = new ListNode((l2.val+carry)%10);
            carry= (l2.val+carry)/10;
            l2 = l2.next;
            head = head.next;
        }
        if (carry!=0){
            head.next = new ListNode(1);
        }
//        cur = reverseLinkList(cur);
        return cur;
    }


    public static ListNode reverseLinkList(ListNode head){
        ListNode pre = null;
        ListNode next = null;
        if (head==null)return null;
        while (head!=null){
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }
}
