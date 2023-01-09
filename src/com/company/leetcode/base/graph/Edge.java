package com.company.leetcode.base.graph;

/**
 * 有向边
 * 无向边就是两个有向边合起来
 */
public class Edge {
	public int weight;
	public Node from;//从谁出发
	public Node to;//接到谁

	public Edge(int weight, Node from, Node to) {
		this.weight = weight;
		this.from = from;
		this.to = to;
	}

}
