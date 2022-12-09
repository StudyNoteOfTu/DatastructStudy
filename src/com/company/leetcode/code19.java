package com.company.leetcode;

import com.company.leetcode.base.ListNode;

/**
 * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 */
public class code19 {
    //快慢指针，一起跳n个节点，直到有一个跳不够了，第n个节点一定在前一个里面
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) return head;
        ListNode realHead = new ListNode(-1);
        realHead.next = head;
        ListNode n1 = realHead;
        ListNode n2 = realHead;
        ListNode n3;
        int j= n;
        int endCount = 0;
        //n2先跳
        while (j>0 && n2.next!=null){
            j--;
            n2 = n2.next;
            endCount++;
        }
        //n2先跳，如果没问题，n1到n2跳前的位置
        while (true){
            endCount = 0;
            j = n;
            n3 = n2;
            //不为空n2才能往前跳，并记录长度
            while (j>0 && n2.next!=null){
                j--;
                n2 = n2.next;
                endCount++;
            }
            //如果n2跳完后，后续还有内容，则可以继续
            if (n2.next!=null){
                n1 = n3;
            }else{
                //如果n2跳不完了,后面剩余几个，n1就跳几个
                int k = endCount;
                while (k>0){
                    k--;
                    n1 = n1.next;
                }
                //删去节点
                n1.next = n1.next.next;
                break;
            }
        }
        return realHead.next;
    }
}
