package st;

import java.util.Iterator;

/*
无序符号表API
 */
public interface ST<K,V>  {
    public  void put(K k, V v);
    public  void delete(K k);
    public  V get(K k);
    public  int size();
    public  boolean isEmpty();
    public  boolean contains(K k);
    public Iterable<K> keys();


}
