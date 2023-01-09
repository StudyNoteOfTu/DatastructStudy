package com.company.basic_plus.class01Hash;

/**
 * 给42亿个无符号整数
 * 限定1G内存
 * 求最多出现次数的数字
 */
public class MostFrequentNum {

    /**
     * 方法1， 用HashMap，遍历之后找到value最大的数
     * key(int)数字 value(int)出现次数
     * 如果这样1G内存不够用
     * 不考虑索引空间，一行记录8Byte，最差情况32G空间放不下。
     *
     * HashMap的使用与不同的key的多少有关，如果同一个数，只占一条记录
     *
     * 方法2：Hash函数 散列性质
     * 数字[a1...an]  --f--> [b1...bn] --%100--> [m1...m99]
     * 把 an发到 mk对应的k号文件上，使用外存，如果相同的数它会进到同一个文件里去
     * （出现碰撞其实就是不同的数字进入到了同一个mk对应的k号外存文件上）
     * 对每个小文件再用HashMap，这样可以保证内存不会爆
     * 最后再把100个小文件中出现次数最多的数做个比较，找到全数字出现次数最多的数
     *
     * 类似思想，每个小文件都建立一个大根堆，最外也建立一个大根堆，
     * 可以获得前k大、频率前k高的数字
     */
}
