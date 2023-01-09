package com.company.basic.class08prefixTree_GreedyAlgorithms;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 输入：
 * 正数数组costs
 * 正数数组profits
 * 正数k
 * 正数m
 *
 * 含义：
 * costs[i]表示i号项目的花费
 * profits[i]表示i号项目在扣除花费之后还能挣到的钱(利润)
 * k表示你只能 串行地 最多做k个项目
 * m表示你初始的资金
 *
 * 说明：
 * 你每做完一个项目，马上获得的收益，可以支持你去做下一个项目。
 * 输出：
 * 你最后获得的最大钱数。
 *
 * 解决办法：做可以做的，而且利润最大的
 * 小根堆（花费） - 被锁住的项目
 * 大根堆（利润） - 由小根堆中弹出的项目
 */
public class MaxMoney {

    public static class Program{
        public int cost;
        public int profit;

        public Program(int pay, int profit) {
            this.cost = pay;
            this.profit = profit;
        }
    }

    public static class MinCostComparator implements Comparator<Program>{

        @Override
        public int compare(Program o1, Program o2) {
            return o1.cost - o2.cost;//升序
        }
    }

    public static class MaxProfitComparator implements Comparator<Program>{

        @Override
        public int compare(Program o1, Program o2) {
            return o2.profit-o1.profit;//降序
        }
    }

    public static int findMaximizedCapital(int k,int w, int[] Profits,int[] Capital){
        //小根堆
        PriorityQueue<Program> minCostQ = new PriorityQueue<>(new MinCostComparator());
        //大根堆
        PriorityQueue<Program> maxProfitQ = new PriorityQueue<>(new MaxProfitComparator());

        for (int i = 0; i < Profits.length; i++) {
            minCostQ.add(new Program(Profits[i],Capital[i]));
        }
        for (int i = 0; i < k; i++) {
            //进行k轮
            //能力所及的项目，全都解锁
            while (!minCostQ.isEmpty() && minCostQ.peek().cost <= w){
                maxProfitQ.add(minCostQ.poll());
            }
            //还有精力，但是没有项目可以挑了（提前return）（不一定能做足k个项目）
            if (maxProfitQ.isEmpty()){
                return w;
            }
            //如果最大利润的大根堆不空
            w += maxProfitQ.poll().profit;
        }
        return w;
    }
}
