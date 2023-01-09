package com.company.basic_plus.class01Hash;

public class BitMap {

    public static void main(String[] args) {
        int a = 0;
        // a 32 bit


        int[] arr = new int[10];//32bit * 10 -> 320 bits
        //arr[0] int 0~31
        //arr[1] int 32~63
        //arr[2] int 64~95
        //arr[n] int 32*n~32*(n+1)-1

        int i = 178;//想要取得第178个bit的状态(从0算起）
        int numIndex = 178 / 32;
        int bitIndex = 178 % 32;
        //拿到178位的状态
        // (num >> index) & b0000_0001
        int s = ((arr[numIndex] >> (bitIndex)) & 1);
        //另一种写法
        int bit = (arr[i/32] >> (i % 32)) & 1;

        //改状态为1
        // num | 1<<index
        arr[numIndex] = arr[numIndex] | (1 << (bitIndex));

        //改状态为0
        //仅那个位置为0 ，其他都是1
        arr[numIndex] = arr[numIndex] & (~(1 << bitIndex));
    }
}
