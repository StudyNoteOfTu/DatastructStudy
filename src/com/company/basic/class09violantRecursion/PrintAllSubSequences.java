package com.company.basic.class09violantRecursion;


import java.util.ArrayList;
import java.util.List;

/**
 * 打印一个字符串的全部子序列，包括空字符串
 * 就是求子集
 * 方法：从左到右每一个字符都考虑要或者不要
 */
public class PrintAllSubSequences {

    public static void printAllSubsquence(String str) {
        char[] chs = str.toCharArray();
        process(chs, 0);
    }

    //也是要和不要走两条路，但是之前的选择所形成的结果，是可变的String，而不用List<Character>来才存
    public static void process(char[] str, int i) {
        if (i == str.length) {
            System.out.println(String.valueOf(str));
            return;
        }
        //要当前字符的路
        process(str, i + 1);
        char tmp = str[i];
        //把该字符抹去（打印时候为空格）
        str[i] = 0;
        //不要当前字符的路
        process(str, i + 1);
        //恢复
        str[i] = tmp;
        //由于递归会回到这里，后续使用还是同一个str对象，所以需要恢复
    }

    public static void function(String str) {
        char[] chs = str.toCharArray();
        process(chs, 0, new ArrayList<Character>());
    }

    //当前来到i位置，要和不要走两条路
    // res表示之前的选择所形成的列表
    public static void process(char[] chs, int i, List<Character> res) {
        //所有字符都走过了
        if(i == chs.length) {
            printList(res);
        }
        List<Character> resKeep = copyList(res);
        //要的当前字符的路，当前字符加进去，做后序过程
        resKeep.add(chs[i]);
        process(chs, i+1, resKeep);
        List<Character> resNoInclude = copyList(res);
        //不要当前字符的路
        process(chs, i+1, resNoInclude);
    }

    public static void printList(List<Character> res) {
        // ...;
    }

    public static List<Character> copyList(List<Character> list){
        return null;
    }


    public static void main(String[] args) {
        String test = "abc";
        printAllSubsquence(test);
    }
}
