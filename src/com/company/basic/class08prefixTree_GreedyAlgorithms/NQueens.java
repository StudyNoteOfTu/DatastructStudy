package com.company.basic.class08prefixTree_GreedyAlgorithms;


/**
 * N皇后问题是指在N*N的棋盘上要摆N个皇后，要求任何两个皇后不同行、不同列，
 * 也不在同一条斜线上。
 * 给定一个整数n，返回n皇后的摆法有多少种。
 * n=1，返回1。
 * n=2或3，2皇后和3皇后问题无论怎么摆都不行，返回0。
 * n=8，返回92。
 * <p>
 * n<32,否则超过int表示范围
 * <p>
 * 方法：按行往下深度优先遍历，考虑不能同一列同一斜线
 * 有维度的深搜：当前维度逐个遍历，每一个一旦合法，就到下一维度重复上述操作，可以用递归
 * <p>
 * <p>
 * 优化：位运算
 */
public class NQueens {

    public static int num1(int n) {
        if (n < 1) {
            return 0;
        }
        int[] record = new int[n];// record[1] -> i行的皇后，放在了第几列
        return process1(0, record, n);
    }

    /**
     * @param i      当前来到第几行
     * @param record 我之前摆的皇后都在record里，虽然record也有后续的皇后，但是不被参考
     * @param n      一共有n个皇后
     * @return 合法的摆法有多少种
     */
    private static int process1(int i, int[] record, int n) {
        //退出程序的条件是走到了第n+1行（不合法）
        //在此之前record[0...i-1]的皇后，任何两个皇后一定都不共行共列共斜线
        if (i == n) {
            return 1;
        }
        int res = 0;
        for (int j = 0; j < n; j++) {//当前在第i行，尝试第i行所有的列j
            //是否能够在这一列放下，需要做如下检查：
            //当前第i行的皇后，放在第j列，不会和之前的皇后共行共列共斜线
            //如果是，认为无效
            //如果不是，认为有效
            if (isValid(record, i, j)) {
                record[i] = j;
                //放好后，深度搜索，往下一层继续做
                res += process1(i + 1, record, n);
            }
        }
        return res;
    }

    private static boolean isValid(int[] record, int i, int j) {
        //无需检查是否共行
        for (int k = 0; k < i; k++) {
            //如果共列
            //或者共斜线：行距列距相等（斜率45度、135度）
            if (j == record[k] || Math.abs(record[k] - j) == Math.abs(i - k)) {
                return false;
            }
        }
        return true;
    }

    //位运算
    public static int num2(int n) {
        if (n < 1 || n > 32) {
            return 0;
        }
        //用位信息来充当数组，如8皇后，就申请 0...011111111
        int limit = n == 32 ? -1 : (1 << n) - 1;
        return process2(limit, 0, 0, 0);
    }

    /**
     * 用位运算来代替record
     * 例如
     * 0行：  00001000
     * 那么第1行：
     * 列限制  ：00001000
     * 左斜限制：00010000
     * 右斜限制：00000100
     * 求或   :  00011100
     * <p>
     * 假设第1行点在了 01000000
     * 列限制直接添加： 01001000
     * 左斜限制，先左移1位，然后把第1行的左斜限制点上： 10100000
     * 右斜限制, 先右移1位，然后把第1行的右斜限制点上； 00100010
     */
    private static int process2(int limit, int colLim, int leftDiaLim, int rightDiaLim) {
        //如果全都被限制住了，也就是前面全都摆过了，退出本次递归
        if (colLim == limit){
            return 1;
        }
        int pos = 0;
        int mostRightOne = 0;
        //用limit来限制能在哪些位上做位运算
        pos = limit & (~(colLim | leftDiaLim | rightDiaLim));
        int res = 0;
        while (pos!=0){
            mostRightOne = pos & (~pos+1);
            pos = pos - mostRightOne;
            res += process2(limit, colLim | mostRightOne,
                    (leftDiaLim | mostRightOne) << 1,
                    (rightDiaLim | mostRightOne) >>> 1);
        }
        return res;
    }

}
