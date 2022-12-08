package com.company.basic.class05;

/**
 * 快慢指针
 * 快指针走两步，慢指针走一步，当快指针越界的时候，慢指针来到链表中间位置
 * 同理
 * 快指针走三步，慢指针走一步，。。。来到1/3位置
 *
 */
public class Homework {

    public static class Node {
        public int value;
        public Node next;

        public Node(int val) {
            value = val;
        }
    }

    /**
     * 题目：
     * 我希望当元素为奇数个的时候，慢指针指向正中
     * 我希望当元素为偶数个的时候，慢指针指向中间前一个
     */
    public static Node getCenter1(Node head){
        if (head == null) return null;
        Node f = null;
        Node s = null;
        if (head.next==null || head.next.next==null){
            //就一个或者两个元素，直接返回第一个
            return head;
        }
        //不止两个元素
        f = head.next;
        s = head;
        //只要f后续还有两个元素（没出界），就继续往后走
        while (f.next!=null && f.next.next!=null){
            f = f.next.next;
            s = s.next;
        }
        //如果f之后没有元素，说明一共只有偶数个元素，s所处位置在中间两元素的前一个
        if (f.next==null){
            return s;
            //如果要两元素后一个，则返回s.next
        }
        //如果f之后还有元素，说明一共有奇数个元素，让s多走一步，走到正中心
        return s.next;
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
//        head.next.next.next = new Node(4);
//        head.next.next.next.next = new Node(5);
//        head.next.next.next.next.next = new Node(6);
        System.out.println(getCenter1(head).value);
    }
}
