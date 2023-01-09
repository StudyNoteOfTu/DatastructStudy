package com.company.basic.class07Graphic;

import com.company.leetcode.base.graph.Edge;
import com.company.leetcode.base.graph.Node;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class MinPath_Dijkstra {
    /**
     * Dijkstra算法
     * 无向图，权值非负数
     * 从一个选定的出发点，去往其他点的最短路径
     *
     * 逻辑：解锁路径，发现新的点，计算出新的最短路径
     *
     * 初始一张表
     *
     *          A  B  C  D
     *      A   0  ∞  ∞  ∞
     *
     * 如果新找到的边比之前的值更小，改写表
     *
     *          A  B  C  D
     *      A   0  3  5  ∞
     *  现在最小的A距离0锁死，从B点出发（加上原先A到B的距离3），看到各点的距离，改写表
     *  过程中，会计算从B走向A，那么新的A-A距离为3（A-B)+ 3(B-1) = 6 > 0(A-A),比原来的值还大，不改写表
     *
     *          A  B  C  D
     *      A   0  3  4  7
     *
     *  现在最小的A-A距离0锁死， A-B距离3锁死，从距离最小的C点出发（加上原来A到C最短距离4),看到各点的距离，改写表
     *
     *  直到所有点的A-？的距离都锁死，得到结果
     **
     *
     */

    /**
     * 经典解法
     */
    public static HashMap<Node, Integer> dijkstra(Node head) {
        //从head出发到所有点的最小距离
        //key: 从head出发到达key
        //value: 从head出发到达key的最小距离
        //如果在表中，没有T的记录，含义是从head出发到T这个点的距离为正无穷，当然也可以设置为系统最大值
        HashMap<Node, Integer> distanceMap = new HashMap<>();
        distanceMap.put(head, 0);//到自己先设为0
        //以及求过距离的节点（锁死的），存在selectedNodes中，以后再也不碰
        HashSet<Node> selectedNodes = new HashSet<>();
        //在distance中找到一个最小的记录，且该记录不能是已经选过的
        Node minNode = getMinDistanceAndUnselectedNode(distanceMap, selectedNodes);
        while (minNode != null) {
            //基数，后续算权重要加上原有的权值
            int distance = distanceMap.get(minNode);
            for (Edge edge : minNode.edges) {
                Node to = edge.to;
                //如果让之前没路的有路了，就添加
                if (!distanceMap.containsKey(to)) {
                    distanceMap.put(edge.to, distance + edge.weight);
                } else {
                    //如果让之前有路的有了新的权值更小的路，就更新表
                    distanceMap.put(edge.to, Math.min(distanceMap.get(to), distance + edge.weight));
                }
            }
            selectedNodes.add(minNode);
            //选一个没选过的最小距离的点出来，再从这个点开始计算是否要改原来的表
            minNode = getMinDistanceAndUnselectedNode(distanceMap, selectedNodes);
        }
        return distanceMap;

    }

    //这里不用小根堆来取出最小值是因为，在过程中可能会修改原有的值，而系统提供的小根堆，不允许修改原有的值
    //想要用这样的小根堆（原来在堆上的元素某一时刻突然值变大或者变小了），就要自己改写堆结构。
    private static Node getMinDistanceAndUnselectedNode(HashMap<Node, Integer> distanceMap, HashSet<Node> selectedNodes) {
        Node minNode = null;
        int minDistance = Integer.MAX_VALUE;
        for (Map.Entry<Node, Integer> entry : distanceMap.entrySet()) {
            Node node = entry.getKey();
            int distance = entry.getValue();
            //如果距离变小了，那么就修改表
            if (!selectedNodes.contains(node) && distance < minDistance) {
                minNode = node;
                minDistance = distance;
            }
        }
        return minNode;
    }


    //使用自定义的小根堆实现的加速版的Dijkstra算法
    public static class NodeHeap {
        //堆的底层结构就是数组
        private Node[] nodes;
        //用表来表示某个节点在堆的什么位置，通过node来找在堆上的位置
        //元素可能会从堆中出去，但是heapIndexMap中还会有该元素的key存在，而用-1作为其value
        private HashMap<Node, Integer> heapIndexMap;
        //node到head的目前的最短距离
        private HashMap<Node, Integer> distanceMap;
        //这个堆上有多少节点
        private int size;

        public NodeHeap(int size) {
            nodes = new Node[size];
            heapIndexMap = new HashMap<>();
            distanceMap = new HashMap<>();
            this.size = 0;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        //如果这个记录之前没有，就add，如果这个记录存在，但是插入的比之前的距离更小，就update，否则就ignore
        public void addOrUpdateOrIgnore(Node node, int distance) {
            //如果在heap中
            if (inHeap(node)) {
                //更新距离
                distanceMap.put(node, Math.min(distanceMap.get(node), distance));
                //可能会往上冒
                heapInsert(node, heapIndexMap.get(node));
            }
            //没进过堆
            if (!isEntered(node)) {
                nodes[size] = node;
                heapIndexMap.put(node, size);
                distanceMap.put(node, distance);
                heapInsert(node, size++);
            }
            //进过堆而且不在heap中，说明已经计算过了的，将来会放到结果集中的（已经确定最短路径了的）
        }

        public NodeRecord pop(){
            NodeRecord nodeRecord = new NodeRecord(nodes[0],distanceMap.get(nodes[0]));
            //弹出第一步，堆顶和最后一个元素交换
            swap(0,size-1);
            heapIndexMap.put(nodes[size-1],-1);//表示弹出堆
            distanceMap.remove(nodes[size-1]);//把将被删掉的被放到最后一个位置的堆顶的distance信息删掉
            nodes[size-1]=null;//弹出
            heapify(0,--size);
            return nodeRecord;

        }

        //通过 distanceMap 的值来决定元素的上下移动，且只可能往上调整（值不会变的比原来更大）
        public void heapInsert(Node node,int index) {
            while (distanceMap.get(nodes[index]) > distanceMap.get(nodes[(index-1)/2])) {//和父节点比较大小
                swap(index, (index - 1) /2);
                index = (index - 1)/2 ;//比较完，把父节点作为新的子节点继续向上比较
            }
        }

        //复用的heapsort中的代码
        public void heapify(int index, int size) {
            int left = index * 2 + 1;//左孩子下标，判断是否越界
            while (left < size) {//我底下还有孩子
                //两个孩子中，谁的值更大，把下标存在largest
                int largest = left + 1 < size && distanceMap.get(nodes[index+1]) > distanceMap.get(nodes[index]) ? left + 1 : left;
                //父和孩子之间，谁的值大，把下标给largest
                largest = distanceMap.get(nodes[largest]) > distanceMap.get(nodes[index]) ? largest : index;
                //如果父节点本身就最大，就结束heapify调整
                if (largest == index) {
                    break;
                }
                //如果父亲不是最大的，和较大孩子做交换
                swap( largest, index);
                //较大孩子的位置变成新的父节点
                index = largest;
                //更新左孩子下标，准备下一次heapify循环
                left = index * 2 + 1;
            }
        }

        //Node是否进过堆
        private boolean isEntered(Node node){
            return heapIndexMap.containsKey(node);
        }

        //Node是否在堆中
        private boolean inHeap(Node node){
            //进来过，而且没被弹出
            return isEntered(node) && heapIndexMap.get(node)!=-1;
        }

        private void swap(int index1,int index2){
            //改了位置，heapIndexMap也要改
            heapIndexMap.put(nodes[index1],index2);
            heapIndexMap.put(nodes[index2],index1);
            Node tmp = nodes[index1];
            nodes[index1] = nodes[index2];
            nodes[index2] = tmp;
        }
    }

    //distance记录head到node的最短距离
    public static class NodeRecord {
        public Node node;
        public int distance;

        public NodeRecord(Node node, int distance) {
            this.node = node;
            this.distance = distance;
        }

    }

    public static HashMap<Node, Integer> dijkstra2(Node head, int size) {
        NodeHeap nodeHeap = new NodeHeap(size);
        nodeHeap.addOrUpdateOrIgnore(head, 0);
        HashMap<Node, Integer> result = new HashMap<>();
        while (!nodeHeap.isEmpty()) {
            //小根堆堆顶为确认了的最短路径
            NodeRecord record = nodeHeap.pop();
            Node cur = record.node;
            int distance = record.distance;
            //把每一个后续的节点都加进去
            for (Edge edge : cur.edges) {
                nodeHeap.addOrUpdateOrIgnore(edge.to, edge.weight + distance);
            }
            //然后再把弹出的小根堆堆顶放到结果集中
            result.put(cur, distance);
        }
        return result;
    }

}
