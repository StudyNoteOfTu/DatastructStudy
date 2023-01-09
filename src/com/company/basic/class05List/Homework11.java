package com.company.basic.class05List;

/**
 * 两个单链表相交的一系列问题
 * 【题目】给定两个可能有环也可能无环的单链表，头节点head1和head2。请实
 * 现一个函数，如果两个链表相交，请返回相交的 第一个节点。如果不相交，返
 * 回null
 * 【要求】如果两个链表长度之和为N，时间复杂度请达到O(N)，额外空间复杂度
 * 请达到O(1)
 */
public class Homework11 {

    public static class Node {
        int val;
        Node next;

        public Node(int val) {
            this.val = val;
        }
    }

    /**
     * 题目前戏：求第一个入环节点
     * 是否有环： 快指针到null，无环， 快指针与慢指针相遇，有环
     * 第一个入环： 相遇的时候，让快指针从头开始进行，变为只走一步（变为慢指针），
     * 此时快慢指针相遇的节点就是第一个入环节点
     */
    public static Node getLoopNode(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }
        Node F = head.next.next;
        Node S = head.next;
        while (F != S) {
            //如果F快指针走到头了，说明无环
            if (F.next == null || F.next.next == null) {
                return null;
            }
            S = S.next;
            F = F.next.next;
        }
        F = head;//回到头 变为只走一步
        while (F != S) {
            F = F.next;
            S = S.next;
        }
        return F;
    }

    /**
     * 题目前戏：
     * 如果确保链表无环，找到交点
     * 1. 遍历两个链表，并记录长度（不是判断为空停下，而是判断来到最后一个节点停下）
     * 2. 判断链表末端是否一样，如果一样，说明有相交
     * 3. 长链表减去短链表长度，两遍指针同时向前走，首先相遇的地方就是交点。
     */
    //...
    public static Node noLoop(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        Node cur1 = head1;
        Node cur2 = head2;
        int n = 0;
        while (cur1.next != null) {
            n++;
            cur1 = cur1.next;
        }
        while (cur2.next != null) {
            n--;
            cur2 = cur2.next;
        }
        if (cur1 != cur2) {
            return null;
        }
        cur1 = n > 0 ? head1 : head2;
        cur2 = cur1 == head1 ? head2 : head1;
        n = Math.abs(n);
        while (n != 0) {
            n--;
            cur1 = cur1.next;
        }
        while (cur1 != cur2) {
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        return cur1;
    }

    /**
     * 如果有环，必然两个链表都有环
     * 情况: 各自成环， 或者共用环（分为入环节点为同一个，入环节点不同）
     * loop1为第一个链表的入环节点
     * loop2为第二个链表的入环节点
     */
    public static Node bothLoop(Node head1, Node loop1, Node head2, Node loop2) {
        Node cur1 = null;
        Node cur2 = null;
        if (loop1 == loop2) {//如果入环节点相同，我现在要找交点
            cur1 = head1;
            cur2 = head2;
            int n = 0;
            while (cur1 != loop1) {
                n++;
                cur1 = cur1.next;
            }
            while (cur2 != loop2) {
                n--;
                cur2 = cur2.next;
            }
            //通过入环结点计算出差值步数
            cur1 = n > 0 ? head1 : head2;
            cur2 = cur1 == head1 ? head2 : head1;
            n = Math.abs(n);
            //长链表先走一段
            while (n!=0){
                n--;
                cur1 = cur1.next;
            }
            //然后一起走，找到第一个交点
            while (cur1!=cur2){
                cur1 = cur1.next;
                cur2=  cur2.next;
            }
            return cur1;
        }else{
            //如果loop1 != loop2 也就是入环结点不同
            cur1 = loop1.next;
            //cur1一直往下走，如果cur1在绕圈回来后都遇不到loop2，说明两个环不相交，两链表不相交
            while (cur1 != loop1){
                if (cur1 == loop2){
                    return loop1;
                }
                cur1 = cur1.next;
            }
            return null;
        }
    }

    //最终结果：
    public static Node getIntersectNode(Node head1, Node head2){
        if (head1 == null || head2 == null) return null;
        Node loop1 = getLoopNode(head1);
        Node loop2 = getLoopNode(head2);
        if (loop1==null && loop2 == null){
            //寻找两个无环链表的交点
            return noLoop(head1,head2);
        }
        if (loop1 !=null && loop2 !=null){
            //如果两个都有环，寻找第一个交点
            return bothLoop(head1,loop1,head2,loop2);
        }
        return null;
    }



}
