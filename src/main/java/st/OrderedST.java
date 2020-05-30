package st;
/*
有序符号表API
 */
public interface OrderedST<K,V> {
    public  void put(K k, V v);
    public  void delete(K k);
    public  V get(K k);
    public  int size();
    public  boolean isEmpty();
    public  boolean contains(K k);
    public  Iterable<K> keys();
    public K min();
    public K max();

    /**
     * 小于等于K的最大键
     * @param k
     * @return
     */
    public K floor(K k);

    /**
     * 大于等于K的最小键
     * @param k
     * @return
     */
    public K ceiling(K k);

    /**
     * 小于K的数量
     * @param k
     * @return
     */
    public int rank(K k);

    /**
     * 排名为k的键
     * @param k
     * @return
     */
    public K select(int k);
}
