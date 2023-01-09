package com.company.leetcode.list;

import com.company.basic.class05List.Homework2ReverseList;
import com.company.leetcode.base.ListNode;

import java.util.List;

/**
 * 给你链表的头节点 head ，每 k 个节点一组进行翻转，请你返回修改后的链表。
 * <p>
 * k 是一个正整数，它的值小于或等于链表的长度。
 * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 * <p>
 * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
 */
public class code25 {

    //先写一个链表反转
    //逻辑：2是next，1作为cur指向pre，1成为pre，2(next)成为cur，3成为next
    //最后头结点是pre
    public void reverseList(ListNode listPre, ListNode listNext, ListNode node, int count) {
        //保证至少有两个元素，且长度length至少为2
        if (node == null || node.next == null || count <= 1) return;
        ListNode pre = listNext;
        ListNode next = null;
        while (count > 0) {
            next = node.next;
            node.next = pre;
            pre = node;
            node = next;
            count--;
        }
        if (listPre != null) listPre.next = pre;
    }


    //定义跳跃指针，每次跳k步，把中间的K个元素拿去翻转
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k == 1) return head;
        ListNode root = new ListNode(0);
        root.next = head;
        ListNode n1 = root;
        ListNode n2 = root;
        ListNode next = root.next;
        ListNode listPre=root;
        do {
            int step = 1;
            n1 = next;
            n2 = next;
            do {
                //让n2跳k-1步
                n2 = n2.next;
                step++;//step=1的时候，n2指向第一个元素
            } while (n2 != null && step < k);
            if (n2 != null) {
                next = n2.next;
                ListNode tail = n1;
                reverseList(listPre, next, n1, k);
                listPre = tail;
                printList(root);
            }
        } while (n2 != null && next != null);
        return root.next;
    }

    public void printList(ListNode head) {
        ListNode cur = head;
        while (cur != null) {
            System.out.print(cur.val + "-");
            cur = cur.next;
        }
        System.out.println();
    }
}
