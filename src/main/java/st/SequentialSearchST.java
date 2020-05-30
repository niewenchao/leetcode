package st;

import java.util.*;

/**
 * 顺序查找：基于无序链表
 * @param <K>
 * @param <V>
 */
public class SequentialSearchST<K,V> implements ST<K,V>{

    /**
     * 使用链表保存
     */
    private Node head;
    private int N;
    private class Node{
        K k;
        V v;
        Node next;
        public Node(K k, V v, Node next){
            this.k = k;
            this.v = v;
            this.next = next;
        }
    }

//    public SequentialSearchST(){
//        N = 0;
//        head = null;
//    }
    @Override
    public  void put(K k, V v){
        for (Node x = head; x != null; x = x.next){
            if(x.k.equals(k)) {x.v = v;return;}
        }
            head = new Node(k, v, head);
            N++;

    }
    @Override
    public  void delete(K k){
        if (N == 0) return;
        if(head.k.equals(k)) {head = head.next; N--;return;}
        for (Node x = head;  x.next != null; x = x.next){
            if(x.next.k.equals(k)) {
                x.next = x.next.next;
                N--;
            }
        }
    }
    @Override
    public  V get(K k){
        for (Node x = head; x != null; x = x.next){
            if(x.k.equals(k)) return x.v;
        }
        return null;
    }
    @Override
    public  int size(){
        return N;
    }
    @Override
    public  boolean isEmpty(){
        return N == 0;
    }
    @Override
    public  boolean contains(K k){
        return get(k) != null;
    }
    @Override
    public Iterable<K> keys(){
        List<K> list = new ArrayList<>();
        for (Node x = head; x != null; x = x.next){
            list.add(x.k);
        }
        return  list;
    }

    public static void main(String[] args){
        SequentialSearchST<Integer, String> searchST = new SequentialSearchST<>();
        for(int i = 0; i < 5; i++) {
            searchST.put(i, i + "");
        }

        System.out.println("size = " + searchST.size());

        for(int i = 0; i < searchST.size(); i++) {
            System.out.println("value:" + searchST.get(i));
        }

        searchST.delete(1);

        System.out.println("删除后:");
        for(int s: searchST.keys()) {
            System.out.println("key:" + s + ", Value:" + searchST.get(s));
        }


    }
}
