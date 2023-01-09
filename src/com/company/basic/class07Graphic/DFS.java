package com.company.basic.class07Graphic;

import com.company.leetcode.base.graph.Node;

import java.util.HashSet;
import java.util.Stack;

/**
 * Deep-first Search深度优先遍历（深搜）
 * 用栈实现
 * 一旦我发现有一条路没走过，我就不走其他路，逮着这条路走到底
 *
 * 图用的是非递归，因为图没有维度的概念，只有是否被探索过的概念
 */
public class DFS {

    //有维度深搜：当前维度逐个遍历，每一个一旦合法，就到下一维度重复上述操作

    //无维度的深搜：图。 核心就是用栈来做递归，同时用set来防重
    public static void dfs(Node node){
        if (node == null){
            return;
        }
        Stack<Node> stack = new Stack<>();
        HashSet<Node> set=  new HashSet<>();
        stack.add(node);
        set.add(node);
        System.out.println(node.value);
        while (!stack.isEmpty()){
            Node cur = stack.pop();
            for (Node next : cur.nexts) {
                if (!set.contains(next)){
                    //把自己放回去
                    stack.push(cur);
                    //把新找到的路也放进去
                    stack.push(next);
                    //防重的set中也记录上它
                    set.add(next);
                    System.out.println(next.value);
                    //拿到第一个就压栈，并打印
                    break;
                }
            }
        }
    }

}
