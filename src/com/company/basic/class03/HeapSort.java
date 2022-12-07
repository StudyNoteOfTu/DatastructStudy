package com.company.basic.class03;

/**
 * 用数组实现堆
 * 堆为完全二叉树（每层从左到右排，除了底层的各层都排满）
 * i的左孩子 2*i+1
 * i的右孩子 2*i+2
 * i的父节点 (i-1)/2
 *
 * 且堆分为大根堆和小根堆，每棵子树的最大值为子树头结点的值
 *
 * Heap insert插入原则：与父节点比较大小，做交换。
 *
 * Heap pop弹出原则：（大根堆）
 * 1.把最后一个元素放在0位置上，heapSize--；
 * 2.从头结点开始，做如下判断，直至判断失败或者没有子节点
 * 2.1 与左右孩子中较大的数比较，如果孩子更大，交换
 * 2.2 以交换完自己新的位置作为头结点
 *
 *
 * heap insert: 向上做调整
 * heapify： 向下做调整
 * 修改某个数据后调整整个堆：
 * 1. 判断这个数据是变大还是变小
 * 2. 如果变大：向上做调整（大根堆）
 * 3. 如果变小：向下做调整（大根堆）
 * 4. 或者直接先向上再向下，总有一个循环判断卡在第一步就结束。
 *
 * heapInsert时间复杂度： O（logN）
 * heapify时间复杂度： O(logN)
 *
 * 堆排序
 * 1. 先把数组变成一个大根堆（不断heapInsert）, 时间复杂度O(NlogN)
 * 2. 把最大值和最后一个值做交换，且heapsize--（把最大值放在数组中heap段最末端，并脱离堆关系）
 * 3. 把新的头结点做heapify，时间复杂度O(NlogN)
 * 4. 当heapSize==0或者1的时候，排序结束，大根堆最后结果为升序：eg: 2,3,6,7,8
 * 时间复杂度：O(NlogN)
 * 额外空间复杂度： O(1)
 */

public class HeapSort {

    /**
     * 如果数组中有N个数，直接想象成满二叉树，则有N/2个叶子节点
     * 倒数第二层 N/4 倒数第三层 N/8...
     * 估计最后的时间复杂度：（错项相减） O(N)
     */

    public static void heapSort2(int[] arr){
        if (arr == null || arr.length < 2) {
            return;
        }
        //首先把数组转为大根堆
//        for (int i = 0; i < arr.length; i++) { //O(N)
//            heapInsert(arr, i); //O(logN)
//        }

        //O(N)
        for (int i = arr.length - 1; i >= 0; i--) {
            heapify(arr,i,arr.length);
        }
        int size = arr.length;
        swap(arr, 0, --size);
        while (size > 0) { //O(N)
            heapify(arr, 0, size); //O(LogN)
            swap(arr, 0, --size); //O(1)
        }
    }

    public static void heapSort(int[] arr) {
        //判断是否需要进行排序
        if (arr == null || arr.length < 2) {
            return;
        }
        //首先把数组转为大根堆
        for (int i = 0; i < arr.length; i++) { //O(N)
            heapInsert(arr, i); //O(logN)
        }
        //然后记录heapsize
        int size = arr.length;
        //先交换
        swap(arr, 0, --size);
        //再做heapify
        while (size > 0) { //O(N)
            heapify(arr, 0, size); //O(LogN)
            swap(arr, 0, --size); //O(1)
        }
    }

    /**
     * Heap insert插入原则：与父节点比较大小，做交换。
     * “我知道我自己的位置和我父节点的位置”
     */
    public static void heapInsert(int[] arr, int index) {
        while (arr[index] > arr[(index - 1) / 2]) {//和父节点比较大小
            swap(arr, index, (index - 1) /2);
            index = (index - 1)/2 ;//比较完，把父节点作为新的子节点继续向上比较
        }
    }

    /**
     * 可以从任意位置往下做heapify
     */
    public static void heapify(int[] arr, int index, int size) {
        int left = index * 2 + 1;//左孩子下标，判断是否越界
        while (left < size) {//我底下还有孩子
            //两个孩子中，谁的值更大，把下标存在largest
            int largest = left + 1 < size && arr[left + 1] > arr[left] ? left + 1 : left;
            //父和孩子之间，谁的值大，把下标给largest
            largest = arr[largest] > arr[index] ? largest : index;
            //如果父节点本身就最大，就结束heapify调整
            if (largest == index) {
                break;
            }
            //如果父亲不是最大的，和较大孩子做交换
            swap(arr, largest, index);
            //较大孩子的位置变成新的父节点
            index = largest;
            //更新左孩子下标，准备下一次heapify循环
            left = index * 2 + 1;
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

}
