package com.company.leetcode;

import com.company.leetcode.base.ListNode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 给你一个链表数组，每个链表都已经按升序排列。
 * <p>
 * 请你将所有链表合并到一个升序链表中，返回合并后的链表
 */
public class code23 {

    public ListNode mergeKLists1(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        if (lists.length == 1) return lists[0];
        ListNode head = new ListNode();
        ListNode cur = head;
        int min = Integer.MAX_VALUE;
        int minIndex = -1;

        while (true) {
            for (int i = 0; i < lists.length; i++) {
                if (lists[i] == null) continue;
                if (lists[i].val < min) {
                    min = lists[i].val;
                    minIndex = i;
                }
            }
            if (minIndex == -1) {
                break;
            }
            cur.next = lists[minIndex];
            lists[minIndex] = lists[minIndex].next;
            cur = cur.next;

            min = Integer.MAX_VALUE;
            minIndex = -1;
        }

        return head.next;
    }

    //每次都把最小的抠出来加到最新的，最小的不用每次都找，直接用小根堆
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        if (lists.length == 1) return lists[0];

        PriorityQueue<ListNode> queue = new PriorityQueue<>(new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return o1.val-o2.val;
            }
        });

        ListNode head = new ListNode();
        ListNode cur = head;

        for (ListNode node : lists) {
            if (node !=null){
                queue.offer(node);
            }
        }

        //如果小根堆中还有元素，说明还可以加
        while (!queue.isEmpty()){
            //取出最小元素
            ListNode min = queue.poll();
            cur.next = min;
            cur = cur.next;
            if (min.next!=null){
                queue.offer(min.next);
            }
        }
        return head.next;
    }


    public ListNode mergeKLists2(ListNode[] lists){
        return merge(lists,0,lists.length-1);
    }

    public ListNode merge(ListNode[] lists,int l, int r){
        if (l==r){
            return lists[l];
        }
        if (l>r){
            return null;
        }
        int mid = (l+r) >> 1;
        return mergeTwoLists(merge(lists,l,mid),merge(lists,mid+1,r));
    }


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
