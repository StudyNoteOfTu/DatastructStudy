package com.company.basic.class02commonSort;

public class SmallSum {
    /**
     * 在一个数组中，每一个数左边比当前数小的数累加起来，叫做这个数组
     * 的小和。求一个数组 的小和。
     * 例子:[1,3,4,2,5] 1左边比1小的数，没有; 3左边比3小的数，1; 4左
     * 边比4小的数，1、3; 2左边比2小的数，1; 5左边比5小的数，1、3、4、
     * 2; 所以小和为1+1+3+1+1+3+4+2=16
     */

    //暴力解法时间复杂度O(N^2)
    //反向思考：右边有多少个数比当前这个数大，把当前数*前述个数 再加和
    //在归并排序中，右侧当前长度为（右边有多少个数比当前左侧这个数大）

    /**
     * 过程是不重复不遗漏的，从1的视角看
     * 1 3 4 2 5
     * 首先 1 和3 比较，找到一个比自己大的数
     * 1 3成为整体， 1 3 和4作比较，找到一个比自己大的数
     * 1 3 4成为整体， 1 3 4 和 2 5 作比较，找到两个比自己大的数
     * 这个过程不重复不遗漏
     */

    //唯一区别是，两侧头个数一样大的时候，要先拷贝右侧的数，且不计算小和
    //如此当第一次遇到右侧比左侧第一个数大的时候，能确定后续都比它大
    public static int smallSum(int[] arr) {
        return process(arr, 0, arr.length - 1);
    }

    public static int process(int[] arr, int L, int R) {

        if (L == R) {
            //如果这部分只有一个数字，不需要排序
            //小和为0
            return 0;
        }
        //计算中间位置
        int mid = L + ((R - L) >> 1);
        //左边排序，并求小和
        int leftSum = process(arr, L, mid);
        //右边排序，并求小和
        int rightSum = process(arr, mid + 1, R);
        //两遍都排好了，归并
        //归并同时，在归并过程中产生小和的数量
        int mergeSum = 0;
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
            //如果左边等于右边，先把右边插入，不计算小和
//            if (arr[leftIndex] == arr[rightIndex]) {
//                extraArr[extraArrIndex++] = arr[rightIndex++];
//            } else if (arr[leftIndex] < arr[rightIndex]) {
//                //如果右边更大，把左边插入，同时计算小和
//                mergeSum += (R - rightIndex + 1) * arr[leftIndex];
//                extraArr[extraArrIndex++] = arr[leftIndex++];
//            } else {
//                //如果左边更大，把右边插入，不计算小和
//                extraArr[extraArrIndex++] = arr[rightIndex++];
//            }
            //更优写法
            //只有左边要比较的数字，比右边当前下标数字小的时候，才计算小和
            mergeSum += arr[leftIndex] < arr[rightIndex] ? (R - rightIndex + 1) * arr[leftIndex] : 0;
            //然后再做归并，不同的是，左右数相等的时候，也先放右边
            extraArr[extraArrIndex++] = arr[leftIndex] < arr[rightIndex] ? arr[leftIndex++] : arr[rightIndex++];
        }
        //后续的直接加在数组末端,这部分不需要计算小和，因为一侧已经没有需要比较的数了
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
        return leftSum + rightSum + mergeSum;
    }
}
