package st;

import java.util.ArrayList;
import java.util.List;

/**
 * 基于拉链法的散列表，N个键值对存放在M条拉链上，N>M
 * 使用拉链解决碰撞冲突,拉链使用无序链表符号表
 * @param <K>
 * @param <V>
 */
public class SeparateChainingHashST<K,V> implements ST<K,V> {
    int N;                                                      //键值对个数
    int M;                                                      //拉链个数
    SequentialSearchST<K,V>[] st;

    public SeparateChainingHashST(int size){
        st = new SequentialSearchST[size];
        for (int i = 0; i < size; i++) {
            st[i] = new SequentialSearchST<K,V>();
        }
        M = size;
    }

    public SeparateChainingHashST(){
        this(997);
    }

    private int hash(K k){
        return (k.hashCode() & 0x7fffffff) % M;
    }

    private void resize(int size){
        SeparateChainingHashST stb = new SeparateChainingHashST(size);
        for (int i = 0; i < M; i++) {
            for(K k: st[i].keys()){
                stb.put(k,st[i].get(k));
            }

        }
        st = stb.st;
        M = stb.M;
    }

    public  void put(K k, V v){
        if(N >= 8*M) resize(2*M);
        if(!contains(k)) N++;
        st[hash(k)].put(k,v);
    }
    public  void delete(K k){
        if(N > 0 && N <= 2*M) resize(M/2);
        if(contains(k))
        {st[hash(k)].delete(k);N--;}
    }
    public  V get(K k){
       return st[hash(k)].get(k);
    }
    public  int size(){
        return N;
    }

    public  boolean isEmpty(){
        return N == 0;
    }
    public  boolean contains(K k){
        return get(k) != null;
    }
    public Iterable<K> keys(){
        List<K> list = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            for(K k: st[i].keys()){
                list.add(k);
            }
        }
        return list;
    }
}
