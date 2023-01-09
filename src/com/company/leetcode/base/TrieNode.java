package com.company.leetcode.base;

//根节点的pass值，代表加入了多少字符串
//根节点的end值，代表加入了多少个空串

//方向、路径已经表示了字符
//节点只负责记录标记，如通过该字符多少次，以他结尾多少次。
//可以把通向节点的路径与节点看成一体，value部分实际是路径表示的值
public class TrieNode {
    //通过多少次（用于路径复用）
    //pass可以体现以某开头作为前缀的字符串有多少个
    public int pass;
    //这个节点是否是字符串的结尾节点，如果是，是多少个的结尾节点
    //用于路径复用与对收集的字符串结尾做标记
    //e=2加入过这个字符串2次
    public int end;
    //已知节点形式，可以用数组，查询效率更高，如果不确定节点形式，用哈希表
    //HashMap<Char,Node>，用key表示路，value表示下个节点
    public TrieNode[] nexts;// HashMap<Char,Node> nexts;

    public TrieNode(){
        pass=0;
        end =0;
        //next[0]==null 没有走向‘a'的路（认为a-z为0-25
        //..
        //next[25]!=null 有走向'z'的路
        nexts = new TrieNode[26];
    }
}
