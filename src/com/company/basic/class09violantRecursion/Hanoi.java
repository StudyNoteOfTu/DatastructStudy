package com.company.basic.class09violantRecursion;


/**
 * 暴力递归就是尝试
 * 1，把问题转化为规模缩小了的同类问题的子问题
 * 2，有明确的不需要继续进行递归的条件(base case)
 * 3，有当得到了子问题的结果之后的决策过程
 * 4，不记录每一个子问题的解


 * 汉诺塔问题
 * 1        |           |           |
 * ...      |           |           |
 * i        |           |           |
 *         from        other       to
 */
public class Hanoi {
    public static void hanoi(int n) {
        if (n > 0) {
            func(n, "左", "右", "中");
        }
    }

    //1~i 圆盘，目标是from->to ,other是另外一个
    //减小规模：从i的问题拆到了i-1
    public static void func(int i, String from, String to, String other) {
        if (i == 1) {
            //base case: 只剩下一个圆盘了
            System.out.println("Move 1 from " + from + " to " + to);
        } else {
            //把1~i-1的圆盘从from放到other上去
            func(i-1,from,other,to);
            //单独把i放到 to 上去，保证这一步不违规，那么所有子问题都不会违规，
            // 只要考虑局部拆的是对的，整体就不会出问题，不要用全局到底怎么运作的来考虑递归
            System.out.println("Move "+i +" from "+from+" to "+to);
            //把1~i-1从other上挪回到from
            func(i-1,other,to,from);
        }
    }

    public static void main(String[] args) {
        int n = 3;
        hanoi(3);
    }
}
