package com.company.basic.class09violantRecursion;


import java.util.Stack;

/**
 * 给你一个栈，请你逆序这个栈，不能申请额外的数据结构，只能使用递归函数。
 * 如何实现?
 *
 * 方法：递归=系统自带一个栈
 * f1[ return=1 , last=f2]
 * f2[ return=2, last =f3]
 * f3[ return=3, last= return]
 * 结果 stack[ 1 2] f1返回3
 * 效果：移除栈底元素并返回
 */
public class ReverseStackUsingRecursive {
    public static void reverse(Stack<Integer> stack) {
        if (stack.isEmpty()) {
            return;
        }
        int i = getAndRemoveLastElement(stack);
        reverse(stack);
        stack.push(i);
    }

    /**
     *
     * [1 2 3]
     * 1> result=1 last=
     *                  2> result=2 last=
     *                                   3>result=3 return 3
     *                                  2## stack.push(2)
     *                   1## stack.push(1)
     */
    public static int getAndRemoveLastElement(Stack<Integer> stack) {
        int result = stack.pop();
        if (stack.isEmpty()) {
            return result;
        } else {
            int last = getAndRemoveLastElement(stack);
            stack.push(result);
            return last;
        }
    }
}

