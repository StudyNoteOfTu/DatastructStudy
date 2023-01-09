package com.company.basic.class02commonSort;

public class InversionCount {
    //右边有多少数字比左边小
    public static int inversionCount(int[] arr) {
        return process(arr, 0, arr.length - 1);
    }

    public static int process(int[] arr, int L, int R) {
        if (L == R) {
            //如果这部分只有一个数字，不需要排序
            //且不存在逆序
            return 0;
        }
        //计算中间位置
        int mid = L + ((R - L) >> 1);
        //左边排序
        int leftCount = process(arr, L, mid);
        //右边排序
        int rightCount = process(arr, mid + 1, R);
        //两遍都排好了，归并
        //同时计算归并过程中发现的左右侧的逆序
        int mergeCount = 0;
        //先建立一个新的数组
        int[] extraArr = new int[R - L + 1];
        //然后为遍历归并做准备
        int extraArrIndex = 0;//while中自增的下标变量
        int leftIndex = L;//左侧数组开始归并下标
        int rightIndex = mid + 1;//右侧数组开始归并下表
        //如果还没归并到某一侧最末端，将继续
        while (leftIndex <= mid && rightIndex <= R) {
            //小于等于保证了稳定性
            //a++ 先使用，再自增
            //如果右边的数字比自己小，那么count+1
            mergeCount += arr[leftIndex] > arr[rightIndex] ? 1 : 0;
            extraArr[extraArrIndex++] = arr[leftIndex] <= arr[rightIndex] ? arr[leftIndex++] : arr[rightIndex++];
        }
        //后续的直接加在数组末端
        while (leftIndex <= mid) {
            extraArr[extraArrIndex++] = arr[leftIndex++];
        }
        while (rightIndex <= R) {
            extraArr[extraArrIndex] = arr[rightIndex++];
        }
        //最后拷贝回原数组
        for (int i = 0; i < extraArr.length; i++) {
            arr[L + i] = extraArr[i];
        }

        return leftCount + rightCount + mergeCount;
    }
}
