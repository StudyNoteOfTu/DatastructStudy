package com.company.leetcode.base.graph;

import java.util.ArrayList;

/**
 * 邻接表法：保存直接相连的点（可带权值）
 * 邻接矩阵：各点到另外一点的距离（不直接相连认为是距离无穷远）
 */

/**
 * 这里用的是邻接表法
 */
public class Node {
    public int value;
    public int in;//入度
    public int out;//出度
    public ArrayList<Node> nexts;//直接相连的点（或者有向图指向的邻居点）
    public ArrayList<Edge> edges;//发散出去的边（如果是有向图）

    public Node(int value) {
        this.value = value;
        in = 0;
        out = 0;
        nexts = new ArrayList<>();
        edges = new ArrayList<>();
    }
}