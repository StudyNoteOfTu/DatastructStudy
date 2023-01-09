package com.company.basic.class08prefixTree_GreedyAlgorithms;

import com.company.leetcode.base.TrieNode;

public class Trie {
    private TrieNode root;
    public Trie(){
        root = new TrieNode();
    }

    public void insert(String word){
        //不为null，但是为空串“”，最后只会在root的end++
        if (word == null){
            return;
        }
        char[] chs = word.toCharArray();
        TrieNode node = root;
        node.pass++;
        int index =0;
        for (int i = 0; i < chs.length; i++) {
            index = chs[i]-'a';//走向哪条路， 0-25字符下标
            if (node.nexts[index]==null){
                //如果原先没有，就加上
                node.nexts[index] = new TrieNode();
            }
            node = node.nexts[index];
            node.pass++;
        }
        //最后一个node作为字符串结尾 end记录+1
        node.end++;
    }

    /**
     * 删除某个加入过的word
     * @param word 要删除的字符串word
     */
    public void delete(String word){
        //先判断是否加入过，如果有加入过，才按路径从上往下删除
        if (search(word)!=0){
            char[] chs = word.toCharArray();
            TrieNode cur = root;
            cur.pass--;
            int index = 0;
            for (int i = 0; i < chs.length; i++) {
                index = chs[i]-'a';
                if (--cur.nexts[index].pass==0){
                    //如果下级节点的pass值减完后为0，那么删掉他
                    //一个节点pass=0，就把它后续的所有东西都扔掉（释放掉）
                    cur.nexts[index]=null;//Java中标空，让GC做释放
                    return;
                }
                cur = cur.nexts[index];
            }
            cur.end--;
        }
    }

    /**
     * @param word 字符串
     * @return 返回该字符串记录了几次(word这个单词加入过几次）
     */
    public int search(String word){
        if (word==null){
            return 0;
        }
        char[] chs = word.toCharArray();
        TrieNode cur = root;
        int index = 0;
        for (int i = 0; i < chs.length; i++) {
            index = chs[i]-'a';
            //如果中途检索断裂了，就是没有该字符串
            if (cur.nexts[index]==null){
                return 0;
            }
            cur = cur.nexts[index];
        }
        return cur.end;

    }

    /**
     * @param pre 前缀字符串
     * @return 返回加入过多少以pre作为前缀的字符串
     * 方式：返回pre的最后一个字符的pass值
     */
    public int prefixNumber(String pre){
        if (pre==null){
            return 0;
        }
        char[] chs = pre.toCharArray();
        TrieNode cur = root;
        int index = 0;
        for (int i = 0; i < chs.length; i++) {
            index = chs[i]-'a';
            if (cur.nexts[index]==null){
                return 0;
            }
            cur = cur.nexts[index];
        }
        return cur.pass;
    }
}
