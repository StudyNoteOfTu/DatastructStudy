package com.company;

public class Main {

    public static void main(String[] args) {
        System.out.println(powMod(123,456,789));
    }

    public static int powMod(int x, int y, int z){
        if (y==0) return 1 % z;
        int half= powMod(x,y>>1,z);
        half = (half * half) % z;
        if ((y&1)==0){//y为偶数
            return half;
        }else{//y是奇数
            return (half *(x%z))%z;
        }
    }
}
