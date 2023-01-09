package com.company.basic.class05List;

import java.util.HashMap;

/**
 * rand指针是单链表节点结构中新增的指针，rand可能指向链表中的任意一个节
 * 点，也可能指向null。给定一个由Node节点类型组成的无环单链表的头节点
 * head，请实现一个函数完成这个链表的复制，并返回复制的新链表的头节点。
 */
public class CloneList {

    public static class Node{
        int val;
        Node next;
        Node rand;
        public Node(int val){
            this.val = val;
        }
    }

    /**
     * 使用hashmap
     */
    public static Node getCloneList1(Node head){
        HashMap<Node,Node> map = new HashMap<>();
        Node cur = head;
        //克隆，并存入map
        while (cur!=null){
            map.put(cur,new Node(cur.val));
            cur = cur.next;
        }
        cur = head;
//        while (cur!=null){
//            Node clone = map.get(cur);
//            clone.next = cur.next;
//            clone.rand = cur.rand;
//            cur = cur.next;
//        }
        while (cur != null) {
            map.get(cur).next = map.get(cur.next);
            map.get(cur).rand = map.get(cur.rand);
            cur = cur.next;
        }
        return map.get(head);
    }

    /**
     * 不使用map
     * 借助
     * [1]->[1']->[2]->[2']的链表前后信息进行克隆赋值
     */
    public static Node getCloneList2(Node head){
        if (head==null){
            return null;
        }
        Node cur = head;
        Node next = null;
        while (cur!=null){
            next = cur.next;
            cur.next = new Node(cur.val);
            cur.next.next= next;
            cur = next;
        }
        cur = head;
        Node curCopy = null;
        while (cur!=null){
            next = cur.next.next;//cur.next一定不为null
            curCopy = cur.next;
            curCopy.rand =cur.rand!=null?cur.rand.next:null;
            cur = next;
        }
        //分裂
        Node res = head.next;
        cur = head;
        while (cur!=null){
            next = cur.next.next;
            curCopy = cur.next;
            cur.next = next;//恢复原链表
            curCopy.next = next!=null?next.next:null;//连接克隆链表
            cur = next;
        }
        return res;
    }


}
