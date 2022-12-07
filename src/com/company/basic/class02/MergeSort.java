package com.company.basic.class02;

//会改写成一个数组中左边右边元素的比较关系的问题
public class MergeSort {
    public static void mergeSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    //归并排序，左边排好序，右边排好序
    //将排好序的两边通过外排序，整合到外部数组，再拷贝回来
    //从左往右排序，原先相同值的元素的先后顺序不变，是稳定的。
    public static void process(int[] arr, int L, int R) {
        if (L == R) {
            //如果这部分只有一个数字，不需要排序
            return;
        }
        //计算中间位置
        int mid = L + ((R - L) >> 1);
        //左边排序
        process(arr, L, mid);
        //右边排序
        process(arr, mid + 1, R);
        //两遍都排好了，归并
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
            arr[L+i] = extraArr[i];
        }

    }



    /**
     * 显然子问题规模都是N/2可以使用master公式进行复杂度估算
     * T(N) = 2*T(N/2)+O(N)
     *
     *      1) log(b,a) > d -> 复杂度为O(N^log(b,a))
     *      2) log(b,a) = d -> 复杂度为O(N^d * logN)
     *      3) log(b,a) < d -> 复杂度为O(N^d)
     *
     *      log(2,2)=1 ->复杂度为 O(N*logN)
     *      额外空间复杂度 O(N)
     */
}
