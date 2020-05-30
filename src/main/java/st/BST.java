package st;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * 有序符号表：基于二叉查找树,关键点在于树的递归操作，注意控制递归的进入及结束边界条件
 * @param <K>
 * @param <V>
 */
public class BST<K extends Comparable<K>, V> implements OrderedST<K, V> {
    private Node root;

    public class Node {
        K key;
        V val;
        Node left;
        Node right;
        int N;              //以该节点为跟节点总数

        public Node(K key, V val, int N) {
            this.key = key;
            this.val = val;
            this.N = N;
        }
    }

    public void deleteMin(){
        deleteMin(root);
    }

    private Node deleteMin(Node x){
        if(x.left == null) x = x.right;
        x.left = deleteMin(x.left);
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public void put(K k, V v) {
        root = put(k, v, root);

    }

    private Node put(K k, V v, Node x) {
        if (x == null) x = new Node(k, v, 1);
        int compare = k.compareTo(x.key);
        if (compare < 0) x.left = put(k, v, x.left);
        else if (compare > 0) x.right = put(k, v, x.right);
        else x.val = v;
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public void delete(K k) {
        delete(root, k);
    }
    private Node delete(Node x, K k){
        int cmpare = k.compareTo(x.key);
        if(cmpare < 0) delete(x.left,k);
        else if(cmpare > 0) delete(x.right,k);
        else {
            if(x.left == null) return x.right;
            if(x.right == null) return x.left;
            Node t = x;
            x = min(x.right);
            x.right = deleteMin(x.right);
            x.left = t.left;
        }

        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public V get(K k) {
        return get(k, root);
    }

    private V get(K k, Node x) {
        if (x == null) return null;
        int compare = k.compareTo(x.key);
        if (compare < 0) return get(k, x.left);
        else if (compare > 0) return get(k, x.right);
        else return x.val;
    }

    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null) return 0;
        return x.N;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public boolean contains(K k) {
        return get(k) != null;
    }

    public Iterable<K> keys() {
        return keys(min(), max());
    }
    public Iterable<K> keys(K lo, K hi){
       List<K> list = new ArrayList<K>();
       keys(root, list, lo, hi);
       return list;
    }

    private void  keys(Node node,List queue, K lo, K hi){
        if(node == null) return;
        int cmplo = lo.compareTo(node.key);
        int cmphi = hi.compareTo(node.key);
        if(cmplo < 0)    keys(node.left, queue,lo,hi);
        if(cmplo <= 0 && cmphi >= 0)        queue.add(node.key);
        if(cmphi > 0)    keys(node.right,queue,lo, hi);
    }

    public K min() {
        Node min = min(root);
        if (min == null) return null;
        return min.key;
    }

    private Node min(Node x) {
        if (x == null || x.left == null) return x;
        return min(x.left);
    }

    public K max() {
        Node max = max(root);
        if (max == null) return null;
        return max.key;
    }

    private Node max(Node x) {
        if (x == null || x.right == null) return x;
        return max(x.right);
    }

    /**
     * 小于等于K的最大键
     *
     * @param k
     * @return
     */
    public K floor(K k) {
        return floor(k, root).key;
    }

    private Node floor(K k, Node x) {
        if (x == null) return null;
        int compare = k.compareTo(x.key);
        if (compare == 0) return x;
        if (compare < 0) return floor(k, x.left);
        Node node = floor(k, x.right);
        if (node == null) return x;
        return node;

    }

    /**
     * 大于等于K的最小键
     *
     * @param k
     * @return
     */
    public K ceiling(K k) {
        return ceiling(k, root).key;
    }

    private Node ceiling(K k, Node x) {
        if (x == null) return null;
        int compare = k.compareTo(x.key);
        if (compare == 0) return x;
        if (compare < 0) return floor(k, x.right);
        Node node = floor(k, x.left);
        if (node == null) return x;
        return node;
    }

    /**
     * 小于K的数量
     *
     * @param k
     * @return
     */
    public int rank(K k) {
        return rank(k, root);
    }

    private int rank(K k, Node x) {
        if (x == null) return 0;
        int compare = x.key.compareTo(k);
        if (compare < 0) return size(x.left) + 1 + rank(k, x.right);
        else if (compare > 0) return rank(k, x.left);
        else return size(x.left);
    }


    /**
     * 排名为k的键
     *
     * @param k
     * @return
     */
    public K select(int k) {
        return select(k, root).key;
    }

    private Node select(int k, Node x) {
        if (x == null) return null;
        int t = size(x.left);
        if (t > k) return select(k, x.left);
        else if (t < k) return select(k - t - 1, x.right);
        else return x;
    }

    public static void main(String[] args) {
        BinarySearchST<Integer, String> searchST = new BinarySearchST<>(100);
        for (int i = 5; i >= 0; i--) {
            searchST.put(i, i + "");
        }

        System.out.println("size = " + searchST.size());

        for (int i = 0; i < searchST.size(); i++) {
            System.out.println("value:" + searchST.get(i));
        }

     //   searchST.delete(1);

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
