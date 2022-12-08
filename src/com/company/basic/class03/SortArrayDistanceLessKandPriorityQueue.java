package com.company.basic.class03;

import java.util.PriorityQueue;

/**
 * 局部大小排序关系（堆）
 *
 * @题目：
 * 已知一个几乎有序的数组，几乎有序是指，如果把数组排好顺序的话，每个元
 * 素移动的距离可以不超过k，并且k相对于数组来说比较小。请选择一个合适的
 * 排序算法针对这个数据进行排序。
 *
 * K=6
 * 小根堆放七个数，取最小一个数放在这七个数最前端（能保证这个数是最小的）
 * 数组快结束的时候，把小根堆的内容依次弹出填入数组即可
 *
 * 小根堆--PriorityQueue优先级队列
 */
public class SortArrayDistanceLessKandPriorityQueue {

    public void sortedArrDistanceLessK(int[] arr, int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int index = 0;
        for (; index <= Math.min(arr.length, k); index++) {
            heap.add(arr[index]);
        }
        int i = 0;
        for (; index < arr.length; i++, index++) {
            heap.add(arr[index]);
            arr[i] = heap.poll();
        }
        while (!heap.isEmpty()) {
            arr[i++] = heap.poll();
        }
    }

    public static void priorityQueue(){
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        heap.add(4);
        heap.add(4);
        heap.add(3);
        heap.add(6);
        heap.add(10);
        heap.add(2);

        //heap sort每次扩容都是*2 扩容代价 O(logN)
        //如果需要用到小根堆，且只需要加入数字，弹出数字，可以用PriorityQueue

        //可以添加比较器 实现Comparator<>接口
        //PriorityQueue<Integer> heap = new PriorityQueue<>(new MyComparator());
        //比较器中，
        // 返回负数，认为第一个参数应该放在前面（小根堆放上面）
        // 返回整数，认为第一个参数应该放在后面（小根堆放下面）
        //比较器的使用：
        //1）比较器的实质就是重载比较运算符
        //2）比较器可以很好的应用在特殊标准的排序上
        //3）比较器可以很好的应用在根据特殊标准排序的结构上

    }

}
