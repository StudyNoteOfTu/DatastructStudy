package com.company.basic.class09violantRecursion;

/**
 * 规定1和A对应、2和B对应、3和C对应...
 * 那么一个数字字符串比如"111"，就可以转化为"AAA"、"KA"和"AK"。
 * 给定一个只有数字字符组成的字符串str，返回有多少种转化结果。
 *
 * 方法：从左往右尝试
 * 在 [0...i-1] 已确定，问从[i...]有多少种尝试方式
 * 情况：如果遇到i位置为0，不存在这种转化方式，直接返回
 * 情况：如果i位置非0，可以决定让i位置转换，并让[i+1...]继续尝试
 *                   也可以让i和i+1位置转换，让[i+2...]继续尝试
 */
public class ConvertToLetterString {

    //i 表示之前的位置，如何转化已经做过决定了
    //i... 之后能有多少种转化的结果
    public static int process(char[] chs,int i){
        //如果前面完全转化，是一条可行的有效的转化字符串的方式，return 1共上层策略累加
        if (i == chs.length){
            return 1;
        }
        //如果i位置是0，不存在转化结果，直接return 0
        //--是否可行的处境由新一轮来判断--
        if (chs[i]=='0'){
            return 0;
        }
        if (chs[i]=='1'){
            int res = process(chs,i+1);//i自己作为单独的部分，后续有多少种方法
            if (i+1<chs.length){
                res+=process(chs,i+1);//i和i+1作为单独的部分，后续有多少种方法
            }
            return res;
        }

        if (chs[i] == '2') {
            int res = process(chs, i + 1);
            if (i + 1 < chs.length && (chs[i + 1] >= '0' && chs[i + 1] <= '6')) {
                res += process(chs, i + 2);
            }
            return res;
        }
        //其他一般情况，就取i位置作为转化结果，剩下的从头判断
        return process(chs,i+1);
    }
}
