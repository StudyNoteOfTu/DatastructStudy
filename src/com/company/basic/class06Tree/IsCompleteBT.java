package com.company.basic.class06Tree;

import com.company.leetcode.base.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class IsCompleteBT {

    /**
     * 按宽度遍历，判断会否为完全二叉树（最后一层不是满的，也是从左往右依次排开的）
     * 方法：按宽度遍历
     * <p>
     * 1) 遍历过程中，任意节点 如果有右孩子但是没有左孩子，直接返回false
     * 2） 在1）下，遇到了第一个左右孩子不双全的情况，后续遇到的节点只能是叶子节点！
     */
    public static boolean isCBT(TreeNode head) {
        if (head == null) return true;
        Queue<TreeNode> queue = new LinkedList<>();
        //记录是否遇到左右孩子不双全的节点
        boolean leaf = false;
        TreeNode l = null;
        TreeNode r = null;
        queue.add(head);
        while (!queue.isEmpty()) {
            head = queue.poll();
            l = head.left;
            r = head.right;
            //如果遇到了左右不双全，后续还不是叶子节点，  或者有右孩子但是没有左孩子，返回false
            if ((leaf && (l != null || r != null)) || (l == null && r != null)) {
                return false;
            }

            if (l != null) {
                queue.add(l);
            }
            if (r != null) {
                queue.add(r);
            }
            if (l == null || r == null) {
                leaf = true;
            }

        }
        return true;
    }


}
