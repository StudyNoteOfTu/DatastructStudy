package com.company.basic.class09violantRecursion;

/**
 * 给定两个长度都为N的数组weights和values，weights[i]和values[i]分别代表
 * i号物品的重量和价值。给定一个正数bag，表示一个载重bag的袋子，你装的物
 * 品不能超过这个重量。返回你能装下最多的价值是多少
 */
public class BagWithMaxValue {
    //可变参数越少越好，越简单越好

    //i... 的货物自由选择，形成的最大价值返回
    //且重量不要超过bag
    //把当前value作为信息往回传
    public static int process(int[] weights,int[] values,
                              int i , int alreadyWeight, int bagSize){
        //base case
        //1.已做的决定如果重量已经超重，那么选择的商品将不被计入
        if (alreadyWeight > bagSize){
            return 0;
        }
        //2.没得选了，后续不会获得更多的价值
        if (i==weights.length){
            return 0;
        }
        //选i货物或者不选
        return Math.max(process(weights,values,i+1,alreadyWeight,bagSize),
                values[i]+process(weights,values,i+1,alreadyWeight+weights[i],bagSize));
    }

    //把当前value作为信息往下传
    public static int process2(int[] weights,int[] values, int i , int alreadyWeight, int alreadyValue,int bag){
        if (alreadyWeight>bag){
            return 0;
        }
        if (i == values.length){
            return alreadyValue;
        }
        return Math.max(process2(weights, values, i+1, alreadyWeight, alreadyValue, bag),
                process2(weights, values, i+1, alreadyWeight+weights[i], alreadyValue+values[i], bag));
    }
}
