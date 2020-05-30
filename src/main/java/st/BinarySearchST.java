package st;

import sort.BinarySearch;

import java.util.ArrayList;
import java.util.List;

/**
 * 二分查找:基于有序数组
 */
public class BinarySearchST<K extends Comparable<K> , V > implements OrderedST<K,V> {
    /**
     * 使用数组保存
     */
    private K[] keys;
    private V[] vals;
    private int N;

    public BinarySearchST(int capacity){
        keys = (K[]) new Comparable[capacity];
        vals = (V[]) new Object[capacity];
    }
    public  void put(K k, V v){
        int i = rank(k);
        if(i < N && keys[i].compareTo(k) == 0) {vals[i] = v;return;}
        for (int j = N; j > i; j--) {
            keys[j] = keys[j-1];vals[j] = vals[j-1];
        }
        keys[i] = k; vals[i] = v;
        N++;
    }
    public  void delete(K k){
        int i = rank(k);
        if(i < N && keys[i].compareTo(k) == 0){
            for (int j = i; j < N -1; j++) {
                keys[j] = keys[j+1];vals[j] = vals[j+1];
            }
            N--;
        }

    }
    public  V get(K k){
        int i = rank(k);
        if(i < N && keys[i].compareTo(k) == 0) return vals[i];
        return null;
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
    public  Iterable<K> keys(){
        List<K> list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            list.add(keys[i]);
        }
        return list;
    }
    public K min(){
        if(N == 0) return null;
        return keys[0];
    }
    public K max(){
        if(N == 0) return null;
        return keys[N -1];
    }

    /**
     * 小于等于K的最大键
     * @param k
     * @return
     */
    public K floor(K k){
        if(N == 0) return null;
        int i = rank(k);
        if(i < N && keys[i].compareTo(k) == 0) return keys[i];
        if(i == 0) return null;
        return keys[i-1];

    }

    /**
     * 大于等于K的最小键
     * @param k
     * @return
     */
    public K ceiling(K k){
        int i = rank(k);
        return keys[i];
    }

    /**
     * 小于K的数量
     * @param k
     * @return
     */
    public int rank(K k){
        int lo = 0, hi = N -1;
        while (lo <= hi){
            int mid = lo + (hi - lo)/2;
            int compare = keys[mid].compareTo(k);
            if(compare < 0) lo = mid + 1;
            else if(compare > 0) hi = mid -1;
            else return mid;
        }
        return lo;
    }

    /**
     * 排名为k的键
     * @param k
     * @return
     */
    public K select(int k){
        return keys[k];
    }

    public static void main(String[] args){
        BinarySearchST<Integer, String> searchST = new BinarySearchST<>(100);
        for(int i = 0; i < 5; i++) {
            searchST.put(i, i + "");
        }

        System.out.println("size = " + searchST.size());

        for(int i = 0; i < searchST.size(); i++) {
            System.out.println("value:" + searchST.get(i));
        }

        searchST.delete(1);

//        System.out.println("删除后:");
//        for(int s: searchST.keys()) {
//            System.out.println("key:" + s + ", Value:" + searchST.get(s));
//        }

        System.out.println("rank:4=" + searchST.rank(4));
        System.out.println("floor:4=" + searchST.floor(4));
        System.out.println("ceiling:4=" + searchST.ceiling(4));
        System.out.println("select:4=" + searchST.select(4));
        System.out.println("min:=" + searchST.min());
        System.out.println("max:=" + searchST.max());

    }
}
