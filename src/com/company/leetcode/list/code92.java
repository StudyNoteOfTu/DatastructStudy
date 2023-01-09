package com.company.leetcode.list;

import com.company.leetcode.base.ListNode;

/**
 * 给你单链表的头指针 head 和两个整数 left 和 right ，其中 left <= right 。
 * 请你反转从位置 left 到位置 right 的链表节点，返回 反转后的链表
 *
 */
public class code92 {

    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode pre=null;
        ListNode cur = head;
        while (cur !=null && left>0){
            pre = cur;
            cur = cur.next;
            left--;
            right--;
        }
        if (cur==null)return head;
        ListNode rootPre = pre;
        //index = left,cur is the first node,pre is the pre-node
        ListNode tail = cur;
        ListNode next=null;
        while (right>=0){
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
            right--;
        }
        tail.next=next;
        if (rootPre==null){
            return pre;
        }
        rootPre.next = pre;
        return head;
    }

    //递归实现反转链表
    public static ListNode reverseList(ListNode pre,ListNode cur){
        //如果新的节点为空了，那么返回前一个
        //base case
        if (cur==null){
            return pre;
        }
        //while inner
        ListNode next = cur.next;
        cur.next = pre;
        pre = cur;
        cur = next;
        //once again
        return reverseList(pre,cur);
    }
    //非递归实现反转链表
    public static ListNode reverseList(ListNode head){
        ListNode next;
        ListNode cur = head;
        ListNode pre= null;
        while (cur!=null){
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(4);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(2);
        head.next.next.next.next = new ListNode(2);
        head.next.next.next.next.next = new ListNode(5);

//        ListNode listNode = reverseList(null, head);
        ListNode listNode = reverseList( head);
        while (listNode!=null){
            System.out.print(listNode.val+"-");
            listNode = listNode.next;
        }

    }

}
