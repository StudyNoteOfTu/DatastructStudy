package com.company.basic.class06Tree;


import com.company.leetcode.base.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 先序的序列化与反序列化
 *
 * 宽度遍历的序列化与反序列化
 */

public class SerializeAndReconstructBinaryTree {

    /**
     * 先序的方式
     */
    public static String serialByPre(TreeNode head) {
        if (head == null) {
            return "#_";
        }
        String res = head.val + "_";
        res += serialByPre(head.left);
        res += serialByPre(head.right);
        return res;
    }

    public static TreeNode reconByPreString(String preStr){
        String[] values = preStr.split("_");
        Queue<String> queue = new LinkedList<>();
        for (int i = 0; i < values.length; i++) {
            queue.add(values[i]);
        }
        return reconPreOrder(queue);
    }

    private static TreeNode reconPreOrder(Queue<String> queue) {
        String value = queue.poll();
        if (value.equals("#")){
            return null;
        }
        TreeNode head = new TreeNode(Integer.parseInt(value));
        head.left = reconPreOrder(queue);
        head.right = reconPreOrder(queue);
        return head;
    }


}
