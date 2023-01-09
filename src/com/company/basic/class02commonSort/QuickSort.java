package com.company.basic.class02commonSort;

/**
 * 划分 partition
 * 阈值划分 如|--(<a)--|--(=a)--|--(>a)--|
 * 解法：
 * 1) [i]<a ,[i]和(<a)区域的下一个交换，(<a)区域右扩，i++
 * 2) [i]==num, i++
 * 3) [i]>a ,[i]和(>a)区域的前一个交换，(>a)区域左扩，i原地不变！
 * （原地不动因为换来的元素是新的，还没看过他）
 * 结束情况：i到了(>a)区域
 *
 * 阈值划分 如 |--(<=a)--|--(>a)--|
 * 解法：
 * 1） [i]<a,[i]和<=区域的下一个数交换，<=区域右扩，i++
 * 2） [i]>num， i++
 * 结束情况，i越界（数组遍历结束）
 */

/**
 * 快排1.0
 * 1）取最后一个数做a，用两区域划分，并将a与>a的第一个数交换。
 * 2）| <=a | a | >a |
 * 3）在 <=a 和 >a的区域分别重复上述动作。
 * 快排2.0
 * 1）取最后一个数做a，用三区域划分，并将a与>a的第一个数交换。递归。
 * 2）| <a | ==a | >a |
 * 3）在 <a 和 >a的区域分别重复上述动作。
 * 快排2.0一次完成一批数字的划分，比快排1.0更快，在重复数字多的情况下效果更明显。
 *
 * 最坏时间复杂度：O(N²)
 * 如果划分值打的很好，将会是T(N)=2T(N/2)+O(N)，也就是O(NlogN)
 * 如果划分的不太好，例如：T(N) = T(N/5)+T(4N/5)+O(N)
 *
 * 快排3.0
 * 上述所有划分情况的递归T(N)的式子的等概率求和之后，可以证明为O(NlogN)
 * 也就是，不论是什么情况的数组，即便是构建出对快排1.0和2.0不利的，在快排3.0也能在O(NlogN)
 *
 * 空间复杂度为 O(LogN) （递归开拓空间）每次都能到中间值，递归层数类似二叉树
 * 最差空间复杂度为O(N)  递归开拓N层
 *
 * 由于每层递归都需要记忆区域划分，如果不用递归而用迭代，
 * 仍然需要自己做一个栈来保存每次递归保存的分区的分界点。所以无论如何空间复杂度无法省略调
 *
 */

public class QuickSort {

    public static void quickSort(int[] arr, int L, int R){
        if(L < R){
            //1. 随机选一个数字与最后一个数字做交换 （快排3.0）
            swap(arr,L+(int)(Math.random()* (R-L+1)),R);
            //2. 进行区域划分 （快排2.0）
            int[] p = process(arr,L,R);
            quickSort(arr,L,p[0]-1);// <区域
            quickSort(arr,p[1]+1,R);// >区域
        }
    }

    //对arr做划分，变成  <a   ==a    >a
    //arr[end]为划分值，i为当前数字
    public static int[] process(int[] arr, int i, int end) {
        int less = i - 1;//初始<区的右边界
        int more = end;//初始>区的左边界
        while (i < more) {//当前数字仍在＞区域的左边
            if (arr[i] < arr[end]) { //如果当前数 < 划分值，放到<区中，<区右扩，i++
                swap(arr, ++less, i++);
            } else if (arr[i] > arr[end]) {//如果当前数 > 划分值，放到>区中，>区左扩，i不动
                swap(arr, --more, i);
            } else {//如果当前数 == 划分值，也就是在==a区域中，i++看下一个数即可
                i++;
            }
        }
        swap(arr, more, end);
        //返回三个区域的边界
        return new int[] { less + 1, more };
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

}
