package com.company.leetcode.tree;

import com.company.leetcode.base.TreeNode;

import java.util.HashMap;

/**
 * 给定两个整数数组 preorder 和 inorder ，
 * 其中 preorder 是二叉树的先序遍历， inorder 是同一棵树的中序遍历，
 * 请构造二叉树并返回其根节点。
 */
public class code105 {
    /**
     * 方法：
     * 前序遍历：【根节点，【左子树的前序遍历结果】，【右子树的前序遍历结果】】
     * 中序遍历：【【左子树的中序遍历结果】，根节点，【右子树的中序遍历结果】】
     *
     * 1. 获取前序遍历第一个节点，为根节点
     * 2. 中序遍历相应区间定位到根节点，分出左右子树长度
     * 3. 前序遍历中找到子树头结点，不断向上返回父节点
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        valueIndexMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            valueIndexMap.put(inorder[i],i);
        }

        int preL = 0;
        int inL = 0;
        int preR = preorder.length-1;
        int inR = inorder.length-1;
        return process(preorder,preL,preR,inorder,inL,inR);
    }

    //L、R左闭右闭
    private TreeNode process(int[] preorder, int preL, int preR, int[] inorder, int inL, int inR) {
        if (preL>preR) return null;
        int curRootNum = preorder[preL];
        TreeNode curRoot = new TreeNode(curRootNum);
        if (preL==preR){
            //只剩下一个，没有子树，是叶子节点
            return curRoot;
        }
        //还有左右子树
        //先通过中序遍历获取左右子树区间
        //int index = getIndexInInOrder(inorder,inL,inR,curRootNum);
        int index = getIndexInInOrder(curRootNum);
        int length = index-inL;
        curRoot.left = process(preorder,preL+1,preL+length,inorder,inL,index-1);
        curRoot.right = process(preorder,preL+length+1,preR,inorder,index+1,inR);
        return curRoot;
    }

//    private int getIndexInInOrder(int[] inorder,int inL,int inR,int num){
//        for (int i = inL; i <= inR; i++) {
//            if (inorder[i]==num){
//                return i;
//            }
//        }
//        return -1;
//    }

    //由于inorder与preorder无重复元素，所以可以使用hashmap来做，否则只能上述注释掉的来做
    private int getIndexInInOrder(int num){
        return valueIndexMap.get(num);
    }

    //每次获取根节点不需要都遍历，用hashmap来空间换时间,超过99%
    private HashMap<Integer,Integer> valueIndexMap ;


}
