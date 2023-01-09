package com.company.basic.class08prefixTree_GreedyAlgorithms;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 做出的是某种意义上的局部最优解
 * <p>
 * 局部最优 -？-> 整体最优
 * <p>
 * 笔试：
 * 1.实现一个不依靠贪心策略的解法x，可以用最暴力的尝试
 * 2.脑补出贪心策略A，B，C
 * 3.用解法X和对数器，去验证每一个贪心策略，用实验的方式得知哪个贪心策略正确
 * 4.不要去纠结贪心策略的证明
 * <p>
 * <p>
 * 常见题目：会议安排，结局方法：哪个结束时间早就安排哪个，把不能安排的会议删掉
 */
public class GreedyMethod {

    /**
     * 会议安排
     */
    public static class Program {
        public int start;
        public int end;

        public Program(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static class ProgramComparator implements Comparator<Program> {

        @Override
        public int compare(Program o1, Program o2) {
            return o1.end - o2.end;
        }
    }

    /**
     * @param programs  现在有的项目
     * @param timePoint 当前来到的时间点
     * @return 可以安排多少个会议
     */
    public static int bestArrange(Program[] programs, int timePoint) {
        Arrays.sort(programs, new ProgramComparator());
        int result = 0;
        //从左往右依次遍历所有会议
        for (int i = 0; i < programs.length; i++) {
            //还没到开始时间，然后时间来到结束时间
            if (timePoint <= programs[i].start) {
                result++;
                timePoint = programs[i].end;
            }
        }
        return result;
    }


    /**
     * 拼接出最小字典序字符串
     */

    /**
     * 有效比较策略：排序之后只有一种结果（需要有传递性）
     * 反例： 甲乙丙大小关系成环（如石头剪刀布），排不出一个结果
     */
    public static class LowestStringComparator implements Comparator<String> {

        @Override
        public int compare(String o1, String o2) {
            //传递性：
            // a_b <= b_a
            // b_c <= c_b
            // ->  a_c <= c_a
            //证明：str看成k进制，拼接后为更多位的k进制数
            //如 “abc"_"de" =>> "abc"*k^2 + "de"  这里k^2表示移位2位
            // a*m(b)+b <= b*m(a)+a 两遍*c
            // b*m(c)+c <= c*m(b)+b 两遍*a
            // 证明 a*m(c)+c <= c*m(a)+a

            //看看哪个放在前面字典序更小
            return (o1 + o2).compareTo(o2 + o1);
        }
    }

    public static String lowestString(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        Arrays.sort(strs, new LowestStringComparator());
        String res = "";
        for (int i = 0; i < strs.length; i++) {
            res += strs[i];
        }
        return res;
    }
}
