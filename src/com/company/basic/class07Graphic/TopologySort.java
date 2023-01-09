package com.company.basic.class07Graphic;

import com.company.leetcode.base.graph.Graph;
import com.company.leetcode.base.graph.Node;

import java.util.*;

/**
 * 拓扑排序
 * 情景：项目依赖关系，决定编译的先后顺序
 * 结构：无环有向图
 * 方式；把入度为0的点取出来，把它影响的边都去掉，重复步骤。
 */
public class TopologySort {

    public static List<Node> sortedTopology(Graph graph){
        //key 某一个node
        //value 剩余的入度
        //使用map进行入度记录是为了不破坏原有图结构
        HashMap<Node,Integer> inMap = new HashMap<>();
        //剩余入度为0的点，才能进这个队列
        //1. 使用queue是因为可能会有多个入度为0的点
        //不需要每次找入度为0的点都取遍历一遍map
        //空间换时间，吧遇到的入度为0的点放到queue中即可
        //2.不用Array来存是因为这里只需要增删，链表效率更高O(1)
        Queue<Node> zeroInQueue = new LinkedList<>();
        for (Node node : graph.nodes.values()) {
            inMap.put(node,node.in);
            if (node.in==0){
                zeroInQueue.add(node);
            }
        }
        //拓扑排序结果，依次加入result
        List<Node> result = new ArrayList<>();
        while (!zeroInQueue.isEmpty()){
            //只要还有入度为0的点，就继续
            Node cur = zeroInQueue.poll();
            result.add(cur);//取出来，放到排序结果中
            for (Node next : cur.nexts) {
                //把它所影响到的边给去掉
                // （这里只需要在map中减掉对应的入度即可，不影响原有的图结构）
                inMap.put(next,inMap.get(next)-1);
                if (inMap.get(next)==0){
                    //遇到了新的入度为0的点，放到queue中
                    zeroInQueue.add(next);
                }
            }
        }
        return result;
    }
}
