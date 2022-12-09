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
     *
     * 如果改成 先序'
     * 先序：头左右
     * 先序': 头右左
     * 则变成先压左再压右
     *
     * 后序：
     * 1. 弹出，设为cur
     * 2. cur放入收集栈
     * 3. 先压左再压右 （头右左）
     * 4. 循环上述
     * 5. 弹出收集栈 （左右头）
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

    /**
     * 中序（左树走到底，往回走一下，看是否有右树，有的话仍然走到底）
     * 非递归：左右头（左边全都压栈，弹出每个节点的过程中(弹出打印），让每个右树都这么做）
     * 对照先序后续，都是往左树走到底，然后回弹一步，让右树重复前述动作
     *
     * 所有树都可以被左边界分开
     * 中序的理解：
     * 左头（右）
     *      左头（右）
     *           左头(右）
     *  总是左头，不过是右树后做，整体表现为左头右
     */
    public static void inOrderUnRecur(Node head){
        System.out.println("in-order:");
        if (head !=null){
            Stack<Node> stack = new Stack<>();
            while (!stack.isEmpty() || head !=null){
                if (head!=null){
                    //压左树头
                    stack.push(head);
                    head = head.left;
                }else{
                    //如果head为空，也就是没有左树了（左树走到底了，往回弹一下）
                    //弹出 打印
                    head = stack.pop();
                    System.out.println(head.val+" ");
                    //再让对应的右树这么干
                    head = head.right;
                }
            }
        }
        System.out.println();
    }

    /**
     * 后续
     */
    public static void posOrderUnRecur(Node head){
        System.out.println("pos-order");
        if (head != null){
            Stack<Node> stack = new Stack<>();
            Stack<Node> collect = new Stack<>();
            stack.push(head);
            while (!stack.isEmpty()){
                head = stack.pop();
                //放入收集栈
                collect.push(head);
                //先压左
                if (head.left!=null){
                    stack.push(head.left);
                }
                //再压右
                if (head.right!=null){
                    stack.push(head.right);
                }
            }
            while (!collect.isEmpty()){
                System.out.println(collect.pop().val+" ");
            }
        }
        System.out.println();
    }



}
