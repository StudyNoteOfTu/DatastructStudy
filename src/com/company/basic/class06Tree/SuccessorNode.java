package com.company.basic.class06Tree;

import com.company.leetcode.base.TreeNode2;

/**
 * 在K二MP叉算树法中扩找展到题一目个节二点的后继节点
 * 【题目】 现在有一种新的二叉树节点类型如下:
 * public class Node {
 * public int value;
 * public Node left;
 * public Node right;
 * public Node parent;
 * public Node(int val) {
 * value = val;
 * }
 * }
 * 该结构比普通二叉树节点结构多了一个指向父节点的parent指针。
 * 假设有一棵Node类型的节点组成的二叉树，树中每个节点的parent指针都正确地指向自己的父节点，头节
 * 点的parent指向null。
 * 只给一个在二叉树中的某个节点node，请实现返回node的后继节点的函数。
 * 在二叉树的中序遍历的序列中， node的下一个节点叫作node的后继节点。
 * <p>
 * <p>
 * <p>
 * 后继节点：中序遍历的下一个
 * 前驱节点：中序遍历的上一个
 * <p>
 * 要更简化：从结构上来分析
 * 1) x有右树： 右树上的最左节点为x的后继
 * 2）x没有右树： 往上走（判断我是不是我父亲的左孩子）（原理，中序遍历打印左树最后一个节点后的下一个就是对应左述条件的头结点）
 * 3）整棵树的最右节点，它的后继为null
 */

public class SuccessorNode {

    public static TreeNode2 getSuccessor(TreeNode2 node) {
        if (node == null) {
            return node;
        }
        if (node.right !=null){
            return getLeftMost(node.right);
        }else{//没有右子树
            TreeNode2 parent = node.parent;
            while (parent!=null && parent.left!=node){//当前节点是其父亲节点的右孩子，继续网上找
                //如果是最右叶子，那么结束条件将会是parent==null，最后返回parent(null)即可
                node  = parent;
                parent = node.parent;
            }
            //直到找到第一个node为其左树节点的父节点
            return parent;

        }
    }

    public static TreeNode2 getLeftMost(TreeNode2 node){
        if (node == null){
            return node;
        }
        while (node.left!=null){
            node = node.left;
        }
        return node;
    }
}
