package com.company.leetcode.list;


import com.company.leetcode.base.ListNode;

import java.util.List;

/**
 * 给你一个链表的头节点 head 和一个特定值 x ，请你对链表进行分隔，
 * 使得所有 小于 x 的节点都出现在 大于或等于 x 的节点之前。
 * <p>
 * 你应当 保留 两个分区中每个节点的初始相对位置。
 */
public class code86 {

    public static ListNode partition(ListNode head, int x) {
        ListNode p1 = new ListNode();
        ListNode p1Head = new ListNode();
        p1Head.next = p1;
        ListNode p2 = new ListNode();
        ListNode p2Head = new ListNode();
        p2Head.next = p2;
        ListNode cur = head;
        while (cur != null) {
            if (cur.val < x) {
                //插到p1末尾
                p1.next = cur;
                p1 = p1.next;
            } else {
                //插到p2末尾
                p2.next = cur;
                p2 = p2.next;
            }
            cur = cur.next;
        }
        //防止成环
        p1.next=null;
        p2.next=null;
        //最后拼接
        if (p1Head.next.next != null) {
            if (p2Head.next.next != null) {
                p1.next = p2Head.next.next;
            }
            return p1Head.next.next;
        } else {
            return p2Head.next.next;
        }
    }

    //for test
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(4);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(2);
        head.next.next.next.next = new ListNode(2);
        head.next.next.next.next.next = new ListNode(5);

        ListNode result = partition(head,3);
        while (result!=null){
            System.out.print(result.val+"-");
            result= result.next;
        }
    }
}
