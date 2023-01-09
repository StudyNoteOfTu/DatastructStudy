package com.company.leetcode.tree;

import com.company.leetcode.base.TreeNode;

import java.util.*;

/**
 * 给你二叉树的根节点 root ，返回其节点值的 锯齿形层序遍历 。
 * （即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）
 */
public class code103 {


    //单向队列+栈
    public List<List<Integer>> zigzagLevelOrder1(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        Queue<TreeNode> queue = new LinkedList<>();
        boolean isRightPooling = true;
        if (root !=null){
            stack.push(root);
        }
        while (!stack.isEmpty()){
            while (!stack.isEmpty()){
                //将stack中的倒出到queue
                queue.add(stack.pop());
            }
            //根据方向，将queue中的弹出，并将子内容放入栈中
            while (!queue.isEmpty()){
                int n = queue.size();
                List<Integer> level = new ArrayList<>();
                for (int i = 0; i < n; i++) {
                    TreeNode node = queue.poll();
                    //打印就按出队顺序打印
                    level.add(node.val);
                    //只要queue中有数据
                    if (isRightPooling){
                        //如果先左在右
                        if (node.left!=null){
                            stack.push(node.left);
                        }
                        if (node.right!=null){
                            stack.push(node.right);
                        }
                    }else{
                        if (node.right!=null){
                            stack.push(node.right);
                        }
                        if (node.left!=null){
                            stack.push(node.left);
                        }
                    }
                }
                res.add(level);
            }
            isRightPooling = !isRightPooling;
        }
        return res;
    }

    //双端队列用于逆序存储打印结果
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        Queue<TreeNode> queue = new ArrayDeque<>();
        boolean isOrderRight = true;
        if (root !=null){
            queue.add(root);
        }
        while (!queue.isEmpty()){
            //先用n记录当前层的个数
            int n = queue.size();
            Deque<Integer> level = new LinkedList<>();
            for (int i = 0; i < n; i++) {
                TreeNode node = queue.poll();
                //添加数据
                if (isOrderRight){
                    //如果是往右读，则把queue中的元素按顺序加入到level中
                    level.addLast(node.val);
                }else{
                    //如果是往左读，则把queue中的元素头插法加入到level中
                    level.addFirst(node.val);
                }
                //BFT添加数据
                if (node.left!=null){
                    queue.add(node.left);
                }
                if (node.right!=null){
                    queue.add(node.right);
                }
            }
            res.add(new LinkedList<>(level));
            isOrderRight = !isOrderRight;
        }
        return res;
    }


}
