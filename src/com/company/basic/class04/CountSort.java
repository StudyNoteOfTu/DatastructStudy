package com.company.basic.class04;

/**
 * 不基于数字大小的排序，也就是需要特别定制的排序
 * 例如 先后顺序为：猪牛羊
 * 例如 一段数字长度排序：【0...200】其中一个数字可能有重复
 * 方法：词频统计
 */
public class CountSort {
    // only for 0~200 value
    //bucket [2 0 0 1 3 4 1 0 ... 2  ...]
    //index   0 1 2 3 4 5 6 7 ... 188...
    public static void countSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        //找到最大的数
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
        }
        //设计词频统计图，从0-max
        int[] bucket = new int[max + 1];
        //词频统计
        for (int i = 0; i < arr.length; i++) {
            bucket[arr[i]]++;
        }
        //统计之后，从0开始按序出桶，成为：eg: 【0 0 1 1 1 1 2 3 4 4 ...148 149... 189 200 200】
        int i = 0;
        for (int j = 0; j < bucket.length; j++) {
            while (bucket[j]-- > 0) {
                arr[i++] = j;
            }
        }
    }
}
