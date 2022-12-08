package com.company.basic.class06;

import java.util.Stack;

public class BinaryTree {

    public static class Node{
        public int val;
        public Node left;
        public Node right;
        public Node (int data){
            this.val = data;
        }
    }

    //递归遍历
    public static void f(Node head){
        //1
        if (head == null)return;
        //第一次探寻到本体
        //1
        f(head.left);
        //2
        //第一次回到本体
        //2
        f(head.right);
        //3
        //第二次回到本体
        //3
    }

    /**
     * 先序：（头左右） 对每个子树来说，都先打印自己头结点，再打印左子树，再打印右子树
     * 中序：（左头右） 对每个子树来说，都先打印左子树，在打印自己头结点，在打印右子树
     * 后续：（左右头） 对每个子树来说，都先打印左子树，再打印右子树，再打印自己头结点
     */

    //非递归实现（自己来压栈）
    /**
     * 先序：
     * 1. 从栈中弹出一个节点cur
     * 2. 打印（处理） cur
     * 3. 先右再左（如果有）
     * 4. 循环上述
     */
    public static void preOrderUnRecur(Node head){
        System.out.println("pre-order:");
        if (head!=null){
            Stack<Node> stack = new Stack<Node>();
            stack.add(head);
            while (!stack.isEmpty()){
                head= stack.pop();
                System.out.println(head.val+" ");
                if (head.right!=null){
                    stack.push(head.right);
                }
                if (head.left!=null){
                    stack.push(head.left);
                }
            }
        }
        System.out.println();
    }
}
