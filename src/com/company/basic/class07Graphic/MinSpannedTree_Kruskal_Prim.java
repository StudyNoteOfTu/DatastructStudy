package com.company.basic.class07Graphic;

import com.company.leetcode.base.graph.Edge;
import com.company.leetcode.base.graph.Graph;
import com.company.leetcode.base.graph.Node;

import java.util.*;

/**
 * 最小生成树
 * 1.各点连通
 * 2.整体边的总权值最小
 */
public class MinSpannedTree_Kruskal_Prim {

    /**
     * 无向图
     * Kruskal:
     * 取最小的边，判断是否形成环，如果没形成环就行。
     * 判断是否成环：集合查询（并查集）
     * Prim：
     *
     */
    /**
     * 集合查询
     * 假设一开始大家都是自己的集合
     * 如果要成边的两个点都在一个集合里，说明会成环，舍去
     * 否则，生成边了，把两者所在的集合合在一起
     * <p>
     * 方法1：并查集
     * 方法2：好理解的版本
     */
    //好理解的版本
    public static class MySets {

        //一个点对应的自己的集合是谁
        public HashMap<Node, List<Node>> setMap;

        public MySets(List<Node> nodes) {
            setMap = new HashMap<>();
            for (Node cur : nodes) {
                List<Node> set = new ArrayList<>();
                set.add(cur);
                setMap.put(cur, set);
            }
        }

        //判断是否在同一个集合里
        // (通过判断内存地址即可，这就要求合并的时候同一个集合
        // 这意味着他们的集合都指向同一个内存地址）
        public boolean isInSameSet(Node from, Node to) {
            List<Node> fromSet = setMap.get(from);
            List<Node> toSet = setMap.get(to);
            return fromSet == toSet;//内存地址一样就是同一个集合
        }

        //把两个集合合并
        public void union(Node from, Node to) {
            List<Node> fromSet = setMap.get(from);
            List<Node> toSet = setMap.get(to);
            //吧to中所有的node都加到from中去
            for (Node node : toSet) {
                fromSet.add(node);
                setMap.put(node, fromSet);//现在from节点和to节点的集合都在fromSet，内存地址一致，同一个集合
            }
        }

    }

    /**
     * edge比较器
     */
    public static class EdgeComparator implements Comparator<Edge>{

        @Override
        public int compare(Edge o1, Edge o2) {
            return o1.weight-o2.weight;
        }
    }

    /**
     * Kruskal:
     *      * 取最小的边，判断是否形成环，如果没形成环就行。
     *      * 判断是否成环：集合查询（并查集）
     */
    public static Set<Edge> kruskalMST(Graph graph){
        MySets mySets = new MySets(new ArrayList<Node>(graph.nodes.values()));
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(new EdgeComparator());
        for (Edge edge : graph.edges) {//M条边
            priorityQueue.add(edge);//小根堆，每次可以拿到最小的edge
        }
        //最后留下的最小生成树的边存在Set中
        Set<Edge> result = new HashSet<>();
        while (!priorityQueue.isEmpty()){//M条边
            //每次取出小根堆中最小的边
            Edge edge = priorityQueue.poll();//O(logM)
            if (!mySets.isInSameSet(edge.from,edge.to)){
                //如果不在同一个集合里（不会形成环）
                result.add(edge);
                //合并两个集合
                mySets.union(edge.from,edge.to);
            }

        }
        return result;
    }

    /**
     * Prim算法
     * 任选一个点开始，解锁它的边，从它的边中选出一个最小的，标记为MST的一条边
     * 再把选出最小边的另一个点的边解锁，从所有未被标记的边中，且连到的点不在已经过的路径，选一个最小权值的，标记为MST的一条边
     */
    public static Set<Edge> primMST(Graph graph){
        //解锁的边进入小根堆
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(new EdgeComparator());

        //考察过的点都放到这个set中
        HashSet<Node> set = new HashSet<>();//防止重复用的标记Set

        Set<Edge> result = new HashSet<>();//依次挑选小的边在result里

        for (Node node : graph.nodes.values()) {//随便挑一个点 （这个for循环是为了防止有多个区域之间不连通，for循环的结果就是生成不连通的好几个MST）
            //node是开始点
            if (!set.contains(node)){
                set.add(node);
                for (Edge edge : node.edges) {//由一个点，解锁所有相连的边
                    priorityQueue.add(edge);
                }
                //吧小根堆中不断取出最小的边做判断
                //是否成环看该取出来的边的另一头是否被标记过（标记在set防重集合里过）
                while (!priorityQueue.isEmpty()){
                    Edge edge = priorityQueue.poll();
                    if (!set.contains(edge.to)){
                        //如果未被标记过，把它连到的新的点放到set中，并将该边收为MST的一条边
                        //并且将该点解锁的新的边加入到小根堆中做后续比较
                        result.add(edge);
                        set.add(edge.to);
                        for (Edge nextEdge : edge.to.edges) {
                            //过滤掉不合理的边，减少小根堆的处理时间
                            if (!set.contains(nextEdge.to)){
                                priorityQueue.add(nextEdge);
                            }
                        }
                    }
                }
            }
        }
        return result;
    }


}
