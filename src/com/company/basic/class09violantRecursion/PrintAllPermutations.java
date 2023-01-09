package com.company.basic.class09violantRecursion;

import java.util.ArrayList;

/**
 * 打印一个字符串的全排列
 *
 * //且不重复
 */
public class PrintAllPermutations {

    //所有结果放到res中去
    //str[i...]范围上，所有的字符都可以放到i位置上，后续都去尝试
    //str[o...i-1]范围伤，是之前做过的选择
    //通过swap,把选择的字符放到i位置上，后续范围变为[i+1...]时，其将不再被选取
    public static void process(char[] str, int i, ArrayList<String> res){
        if ( i == str.length){
            res.add(String.valueOf(str));
        }
        //不重复：26个小写字母，由于所有字符都可以放到i位置上，
        // 防止两个b都放到i位置，通过防重，只允许放一次b
        //去重方法很多，可以用set，但由于内容固定a~z,所以可以用数组
        //boolean[] visit = new boolean[26];
        for (int j = 0; j < str.length; j++) {
//            if (!visit[str[j]-'a']){
//                visit[str[j]-'a']=true;
//                swap(str,i,j);
//                process(str,i+1,res);
//                swap(str,i,j);
//            }
            swap(str,i,j);
            process(str,i+1,res);
            swap(str,i,j);

        }
    }

    public static void swap(char[] str, int i , int j){
        char tmp = str[i];
        str[i]=str[j];
        str[j]=tmp;
    }
}
