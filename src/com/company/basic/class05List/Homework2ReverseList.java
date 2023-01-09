package com.company.basic.class05List;

public class Homework2ReverseList {

    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    public static Node reverseList(Node head){
        Node pre = null;
        Node next = null;
        while (head !=null){
            //head->[1]->[2]->[3]->[4]->null
            next = head.next;//先拿到head后一个元素
            head.next = pre;//让head指向前一个元素
            //  head->[1]->pre->null   next->[2]->[3]->[4]
            pre = head;//把原来的head作为将被指向的前一个元素
            //  pre->[1]->null
            //  head->[1]->null
            //  next->[2]->[3]->[4]->null
            head = next;//把当前的next设置为最新的链头
            // pre->[1]->null
            // head->[2]->[3]->[4]->null
        }
        return pre;
    }
}
