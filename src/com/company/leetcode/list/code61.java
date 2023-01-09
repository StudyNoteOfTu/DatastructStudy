package com.company.leetcode.list;


import com.company.leetcode.base.ListNode;

/**
 * 给你一个链表的头节点 head ，旋转链表，将链表每个节点向右移动 k 个位置。
 */
public class code61 {

    //先接为一个单向循环链表，然后根据k来计算分割圆环的位置
    public ListNode rotateRight(ListNode head, int k) {
        if (head==null || head.next==null) return head;
        //get lenth
        MyReturn myReturn = getLenth(head);
        myReturn.tail.next = head;
        ListNode tail = myReturn.tail;
        int prePartitionLength = myReturn.length - k % myReturn.length;
        while (prePartitionLength>0){
            tail = head;
            head = head.next;
            prePartitionLength--;
        }
        tail.next =null;
        return head;
    }

    public static class MyReturn{
        public ListNode tail;
        public int length;

        public MyReturn(ListNode tail, int length) {
            this.tail = tail;
            this.length = length;
        }
    }

    private MyReturn getLenth(ListNode head) {
        int length =0;
        ListNode cur = head;
        if (cur==null) return new MyReturn(null,length);
        while (true){
            length++;
            if (cur.next==null) break;
            cur = cur.next;
        }
        return new MyReturn(cur,length);
    }

}
