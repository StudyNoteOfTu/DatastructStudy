package com.company.leetcode.base.graph;

public class GraphGenerator {
    /**
     * matrix be like:
     * Edges
     * weight from to
     * 1      A    B
     * 2      E    C
     * 1      D    A
     * 9      B    C
     */
    public static Graph createGraph(Integer[][] matrix) {
        Graph graph = new Graph();
        for (int i = 0; i < matrix.length; i++) {
            Integer weight = matrix[i][0];
            Integer from = matrix[i][1];
            Integer to = matrix[i][2];
            //检查Graph的点集中这个Node是否出现过，没有就加进去
            if (!graph.nodes.containsKey(from)) {
                graph.nodes.put(from, new Node(from));
            }
            if (!graph.nodes.containsKey(to)) {
                graph.nodes.put(to, new Node(to));
            }
            //建立边
            Node fromNode = graph.nodes.get(from);
            Node toNode = graph.nodes.get(to);
            Edge newEdge = new Edge(weight, fromNode, toNode);
            //将指向信息填好
            fromNode.nexts.add(toNode);
            //出入度更改
            fromNode.out++;
            toNode.in++;
            //添加到fromNode的向外指出的边集 edges里
            fromNode.edges.add(newEdge);
            //最后加到graph里去
            graph.edges.add(newEdge);
        }
        return graph;
    }
}
