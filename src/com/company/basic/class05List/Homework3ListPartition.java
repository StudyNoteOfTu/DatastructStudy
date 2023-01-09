package com.company.basic.class05List;

public class Homework3ListPartition {
    /**
     * 给一个值，partition三个区域
     */
    public static class Node {
        public int value;
        public Node next;

        public Node(int val) {
            value = val;
        }
    }

    public static Node partition(int val,Node head){
        Node lessH =null;
        Node lessT = null;
        Node equalH = null;
        Node equalT = null;
        Node moreH = null;
        Node moreT = null;
        while (head!=null){
            if (head.value<val){
                if (lessH==null)lessH = lessT = head;
                else{
                    lessT.next = head;
                    lessT= head;
                }
            }else if (head.value==val){
                if (equalH == null) equalH = equalT = head;
                else{
                    equalT.next = head;
                    equalT = head;
                }
            }else{
                if (moreH==null) moreH = moreT = head;
                else {
                    moreT.next = head;
                    moreT = head;
                }
            }
            head= head.next;
        }
        //讨论边界
        if (lessT!=null){
            //如果有小于区域
            lessT.next = equalH;
            equalT= equalT==null? lessT:equalT;//谁去连大于区域的头，谁就变成equalT
            //如果原先的equalT为空，则没有equal区域，就拿lessT当做equalT
        }
        if (equalT!=null){
            //如果小于区域和等于区域不是都没有
            equalT.next = moreH;
        }
        //从哪个区域开始有就返回哪个区域的头
        return lessH!=null? lessH : (equalH!=null? equalH:moreH);
    }
}
