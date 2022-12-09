package com.company.basic.class06;


import com.company.leetcode.base.TreeNode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * BFT宽度优先遍历，用队列
 * 打印，并把左右子树入队
 */
public class BFT {

    public static void w(TreeNode head){
        if (head !=null){
            return;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(head);
        while (!queue.isEmpty()){
            //弹出打印
            TreeNode cur = queue.poll();
            System.out.println(cur.val);
            if (cur.left!=null){
                queue.add(cur.left);
            }
            if (cur.right!=null){
                queue.add(cur.right);
            }
        }
    }

    /**
     * 求一棵二叉树最大宽度
     * 方法：遍历到接电的时候，知道它在第几层
     */
    public static int w2(TreeNode head){
        if (head ==null){
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(head);
        //记录每个入队的节点在第几层（作为某个节点的孩子，层数为父节点的层数+1）
        HashMap<TreeNode,Integer> levelMap = new HashMap<>();
        levelMap.put(head,1);
        //当前收集的层
        int curLevel = 1;
        //当前层的宽度（出队的时候增加）
        int curlevelNodes = 0;
        int max = Integer.MIN_VALUE;
        while (!queue.isEmpty()){
            //弹出打印
            TreeNode cur = queue.poll();
            //先获取层数
            int curNodeLevel = levelMap.get(cur);
            //首先遇到最新层，一定是上一层全都出队了
            if (curNodeLevel == curLevel){
                curlevelNodes++;//仍然在这一层
            }else{
                //上一层的宽度可以结算了
                max= Math.max(max,curlevelNodes);
                curLevel++;
                curlevelNodes = 0;
            }

            System.out.println(cur.val);
            //进队列的时候就要知道它在哪一层
            if (cur.left!=null){
                queue.add(cur.left);//进了一个左孩子，所以左孩子应该在当前节点的下一层
                levelMap.put(cur.left,curNodeLevel+1);
            }
            if (cur.right!=null){
                queue.add(cur.right);
                levelMap.put(cur.left,curNodeLevel+1);
            }
        }

        max= Math.max(max,curlevelNodes);
        return max;
    }

    public static int getMaxWidth(TreeNode head) {
        if (head == null) {
            return 0;
        }
        int maxWidth = 0;
        int curWidth = 0;
        int curLevel = 0;
        HashMap<TreeNode, Integer> levelMap = new HashMap<>();
        levelMap.put(head, 1);
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(head);
        TreeNode node = null;
        TreeNode left = null;
        TreeNode right = null;
        while (!queue.isEmpty()) {
            node = queue.poll();
            left = node.left;
            right = node.right;
            if (left != null) {
                levelMap.put(left, levelMap.get(node) + 1);
                queue.add(left);
            }
            if (right != null) {
                levelMap.put(right, levelMap.get(node) + 1);
                queue.add(right);
            }
            if (levelMap.get(node) > curLevel) {
                curWidth = 0;
                curLevel = levelMap.get(node);
            } else {
                curWidth++;
            }
            maxWidth = Math.max(maxWidth, curWidth);
        }
        return maxWidth;
    }


    /**
     * 不用哈希表的方法，但是要用队列+当前层最后一个节点+下一层最后一个节点，当前层level
     * TreeNode curEnd = ①
     * TreeNode nextEnd = null;//总把最近进队列的设为nextEnd
     * int curLevelNodes = 0;
     * int max;
     *
     * 当弹出到curEnd的时候，curEnd=nextEnd， nextEnd=null , curLevelNodes = 0
     *
     *
     */

}
