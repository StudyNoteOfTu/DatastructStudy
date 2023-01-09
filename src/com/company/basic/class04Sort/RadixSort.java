package com.company.basic.class04Sort;


/**
 * 0-9号桶(十进制），比计数排序好
 * 先根据个位排序，从左往右倒出来
 * 再根据十位，百位...
 */
public class RadixSort {

    /**
     * 对arr[begin...L...R...end]中的L...R排序
     *
     * @param arr   数组
     * @param L     需要排序部分的起点
     * @param R     需要排序部分的起点
     * @param digit 最大数值的进制位数
     */
    public static void radixSort(int[] arr, int L, int R, int digit) {
        final int radix = 10;//十进制
        int i, j = 0;
        //需要准备多少个辅助空间
        int[] bucket = new int[R - L + 1];
        //进出桶的次数由最大进制位数决定
        for (int d = 1; d <= digit; d++) {//有多少位就发生多少次入桶出桶
            //先针对个位数进行词频统计，计入count[0 1 2 ... 9]上
            //且使用前缀和，例如 检查到71，则 [1 2 .... 9]都+1 现在是[0 1 1 1 1 1 ... 1]
            //例如 检查到12,则[2 3...9]都+1 现在是[0 1 2 2 2 .... 2]
            //最后的结果例如count的0-9上为[1 2 4 4 6 8 8 8 9 10]
            //全都-1得出下标分界位置[0 1 3 3 5 7 7 7 8 9]
            //说明一共有10个数字，且划分好区域了（0-0坐标有第一区，0-1坐标是第二区，1-3坐标内是第三区,1-3坐标内是第四区
            //后续再根据原先检查的数组，重新分区放入bucket数组中,每放入一个，对应的count计数--，其他位置的不用管
            //bucket：[10,41,62,92,84,14...]
            //如此就把个位数从小到大的顺序排好了
            //核心：“分片”

            int[] count = new int[radix];//count[0...9]
            for (i = 0; i <= R; i++) {
                j = getDigit(arr[i], d);
                count[j]++;
            }
            //构造前缀和数组
            for (i = 1; i < radix; i++) {
                count[i] = count[i] + count[i - 1];
            }
            //入桶
            for (i = R; i >= L; i--) {//对原数组从右往左（从后往前）遍历，使得先进桶的先出桶
                j = getDigit(arr[i], d);
                bucket[count[j] - 1] = arr[i];//放入bucket桶中
                count[j]--;
            }
            //出桶
            for (i = L, j = 0; i <= R; i++, j++) {//再次倒序放回原数组，两次倒序，顺序与原先一致，只按个位数字大小进行了排序小
                arr[i] = bucket[j];
            }

        }
    }

    public static int getDigit(int x, int d) {
        return ((x / ((int) Math.pow(10, d - 1))) % 10);
    }
}
