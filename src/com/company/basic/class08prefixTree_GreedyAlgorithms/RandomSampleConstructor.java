package com.company.basic.class08prefixTree_GreedyAlgorithms;

/**
 * 随机样本产生器
 * 对数器
 */
public class RandomSampleConstructor {

    /**
     * Math.random() ->[0.1)所有小数，等概率返回一个
     * Math.random() * N ->[0,N)所有小数，等概率返回一个
     * (int)(Math.random() * N) ->[0,N-1] 所有整数，等概率返回一个
     * 可以用来数组长度随机
     * int[] arr = new int[(int)(Math.random() * (maxSize+1))];
     * 随机数字
     * arr[i] = (int)((maxValue+1)*Math.random()) - (int)((maxValue)*Math.random())
     */

    //for test
    public static void main(String[] args) {
        int testTime = 500_000;//测试次数
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize,maxValue);
            int[] arr2 = copyArray(arr1);
            //f(arr1)
            //f2(arr2)
            //判断结果是否一致 succeed = false
        }
        //打印结果

    }

    private static int[] copyArray(int[] arr1) {
        if (arr1 == null){
            return null;
        }
        int[] res = new int[arr1.length];
        for (int i = 0; i < arr1.length; i++) {
            res[i] = arr1[i];
        }
        return res;
    }

    private static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int)(Math.random() * (maxSize+1))];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int)((maxValue+1)*Math.random()) - (int)((maxValue)*Math.random());
        }
        return arr;
    }



}
