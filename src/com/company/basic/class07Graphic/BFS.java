package com.company.basic.class07Graphic;

import com.company.leetcode.base.graph.Node;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

//Breadth-First Search 宽度优先遍历

/**
 * 宽度优先遍历用队列
 */
public class BFS {

    /**
     * 核心，不要让有环的东西一直死循环下去（用Set来做去重机制）
     *
     * 如果Node范围已知，例如点就是数字，最多就一千个 0...1000
     * 可以把HashMap改为使用数组结构（减少常数时间，虽然都是O(1)）
     */
    public static void bfs(Node node){
        if (node == null){
            return;
        }
        Queue<Node> queue= new LinkedList<>();
        //用于存下之前已经用过的点，保证Node不会重复进入队列
        HashSet<Node> set=  new HashSet<>();
        queue.add(node);
        set.add(node);
        while (!queue.isEmpty()){
            Node cur = queue.poll();
            //细节定制
            System.out.println(cur.value);

            for (Node next : cur.nexts) {
                //如果找到了一个新的点，就加到set里去
                if (!set.contains(next)){
                    set.add(next);
                    queue.add(next);
                }
            }
        }

    }

}
