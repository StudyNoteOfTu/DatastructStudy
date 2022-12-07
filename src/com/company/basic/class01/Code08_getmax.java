package com.company.basic.class01;

public class Code08_getmax {

    public static int getMax(int[] arr) {
        return process(arr, 0, arr.length - 1);
    }

    //arr[L...R]范围伤最大值，这里L，R均为闭
    public static int process(int[] arr, int L, int R) {
        if (L == R) {//范围上只有一个数，直接返回
            return arr[L];
        }
        int mid = L + ((R - L) >> 1);//求中点，防溢出，右移表示除以2
        int leftMax = process(arr, L, mid);
        int rightMax = process(arr, mid + 1, R);
        return Math.max(leftMax, rightMax);
    }

    /**
     * maser公式，估计递归行为时间复杂度,用于子问题规模是等规模的
     * T(N) = a*T(N/b) + O(N ^ d)
     * T(N)母问题的规模
     * T(N/b) 子问题的规模 (看调用自己的地方）
     * a是子问题被调用的次数
     * O(N ^ d)除了子问题调用之外的时间复杂度
     *
     *      1) log(b,a) > d -> 复杂度为O(N^log(b,a))
     *      2) log(b,a) = d -> 复杂度为O(N^d * logN)
     *      3) log(b,a) < d -> 复杂度为O(N^d)
     *
     * process()：
     * T(N) = 2 * T(N/2)+O(1)
     *
     * 拓展：如果吧范围变成左边2/3 和 右侧2/3做process()也可以
     * 最大值结果总是对的，不过是时间复杂度变了
     * 子问题规模是等规模的 N/(2/3)，所以这个情况也能用master公式
     *
     */



}
