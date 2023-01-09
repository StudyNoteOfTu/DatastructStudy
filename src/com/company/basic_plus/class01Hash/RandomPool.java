package com.company.basic_plus.class01Hash;

import java.util.HashMap;

/**
 * 设计RandomPool结构
 * 简单使用HashMap实现
 * 【题目】
 * 设计一种结构，在该结构中有如下三个功能:
 * insert(key):将某个key加入到该结构，做到不重复加入
 * delete(key):将原本在结构中的某个key移除
 * getRandom(): 等概率随机返回结构中的任何一个key。
 * 【要求】
 * Insert、delete和getRandom方法的时间复杂度都是O(1)
 */
public class RandomPool {
    /**
     * map1(str -> index)
     * map2(index -> str)
     * int size
     *
     * 这里的index只是一个记录，标记，为了通过index来拿到某一个str
     *
     * 核心：填洞
     * 删除的时候不希望出现很多的洞（因为index是自增的）
     * 保证index是连续的，方式：让最后一条记录去填这个洞（把最后一条的index改_更新_为洞的index就够）
     */
    public static class Pool<K>{
        private HashMap<K,Integer> keyIndexMap;
        private HashMap<Integer,K> indexKeyMap;
        private int size;
        public Pool(){
            this.keyIndexMap = new HashMap<>();
            this.indexKeyMap = new HashMap<>();
            this.size = 0;
        }

        public void insert(K key){
            //如果没有就加入
            if (!this.keyIndexMap.containsKey(key)) {
                this.keyIndexMap.put(key, this.size);
                this.indexKeyMap.put(this.size++, key);
            }
        }

        //填洞
        public void delete(K key){
            //如果存在
            if (this.keyIndexMap.containsKey(key)) {
                //拿到要删的index
                int deleteIndex = this.keyIndexMap.get(key);
                //拿到最后一个index
                int lastIndex = --this.size;
                K lastKey = this.indexKeyMap.get(lastIndex);
                //把最后一个元素的index更新为填洞index
                this.keyIndexMap.put(lastKey, deleteIndex);
                this.indexKeyMap.put(deleteIndex, lastKey);
                //然后直接把要删的元素删掉
                this.keyIndexMap.remove(key);
                this.indexKeyMap.remove(lastIndex);
            }
        }
    }

}
