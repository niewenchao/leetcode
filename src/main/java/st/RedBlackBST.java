package st;

import com.sun.org.apache.regexp.internal.RE;

import java.util.ArrayList;
import java.util.List;

public class RedBlackBST<K extends Comparable<K>, V> implements OrderedST<K, V> {
    public static final boolean RED = true;
    public static final boolean BLACK = false;

    private Node root;

    public class Node {
        K key;
        V val;
        Node left;
        Node right;
        int N;              //以该节点为跟节点总数
        boolean color;

        public Node(K key, V val, int N, boolean color) {
            this.key = key;
            this.val = val;
            this.N = N;
            this.color = color;
        }
    }

    private boolean isRed(Node x){
        if(x == null) return false;
        return x.color == RED;
    }

    private Node rotateLeft(Node h){
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        x.N = h.N;
        h.color = RED;
        h.N = size(h.left) + size(h.right) + 1;
        return x;
    }

    private Node rotateRight(Node h){
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        x.N = h.N;
        h.color = RED;
        h.N = size(x.left) + size(h.right) + 1;
        return x;
    }

    private void flipColors(Node x){
        x.color = RED;
        x.left.color = BLACK;
        x.right.color = BLACK;
    }

    public void put(K k, V v) {
        root = put(k, v, root);
        root.color = BLACK;
    }

    private Node put(K k, V v, Node x) {
        if (x == null) return new Node(k, v, 1, RED);
        int compare = k.compareTo(x.key);
        if (compare < 0) x.left = put(k, v, x.left);
        else if (compare > 0) x.right = put(k, v, x.right);
        else x.val = v;
        //红黑树的关键颜色判断旋转操作
        if(!isRed(x.left) && isRed(x.right) ) x = rotateLeft(x);
        if(isRed(x.left) && isRed(x.left.left) ) x = rotateRight(x);
        if(isRed(x.left) && isRed(x.right)) flipColors(x);
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    private void moveflipColors(Node h ){   //  这是用于删除节点的flipColor方法，该方法用于节点的合并，将父节点中的部分给与子节点
        h.color = BLACK;
        h.left.color = RED;
        h.right.color = RED;
    }

    private Node moveRedLeft(Node h){
        /**
         * 当前节点的左右子节点都是2-节点，左右节点需要从父节点中借一个节点
         * 如果该节点的右节点的左节点是红色节点，说明兄弟节点不是2-节点，可以从兄弟节点中借一个
         */
        moveflipColors(h);     // 从父节点中借一个
        if(isRed(h.right.left)){    // 判断兄弟节点，如果是非红节点，也从兄弟节点中借一个
            h.right = rotateRight(h.right);
            h = rotateLeft(h);
            moveflipColors(h);  //  在从兄弟节点借了一个以后，我们就需要还一个节点给父节点了，因为一开始从父节点那里借了一个
        }
        return h;
    }

    public void deleteMin(){
        if(!isRed(root.left) && !isRed(root.right)){
            root.color = RED;   // 如果根节点的左右子节点是2-节点，我们可以将根设为红节点，这样才能进行后面的moveRedLeft操作，因为左子要从根节点借一个
        }
        root = deleteMin(root);
        root.color = BLACK;  // 借完以后，我们将根节点的颜色复原
    }

    private Node deleteMin(Node x){
        if(x.left == null) return null;
        if(!isRed(x.left) && !isRed(x.left))    // 判断x的左节点是不是2-节点
            x = moveRedLeft(x);
        x.left = deleteMin(x.left);
        return balance(x);  //   解除临时组成的4-节点
    }

    private Node balance(Node h){
        if (isRed(h.right)) h = rotateLeft(h);
        if (isRed(h.right) && !isRed(h.left)) h=rotateLeft(h);
        if (isRed(h.left) && isRed(h.left.left)) h=rotateRight(h);
        if (isRed(h.left) && isRed(h.right))    flipColors(h);
        h.N = size(h.left)+size(h.right)+1;
        return h;
    }

    private Node moveRedRight(Node h){
        moveflipColors(h);
        if(isRed(h.left.left)){         // 在这里对于兄弟节点的判断都是.left，因为红色节点只会出现在左边
            h=rotateRight(h);
            moveflipColors(h);
        }
        return h;
    }
    public void deleteMax(){
        if(!isRed(root.left) && isRed(root.right)){
            root.color = RED;
        }
        root = deleteMax(root);
        root.color = BLACK;
    }

    private Node deleteMax(Node h){
        if(isRed(h.left)){
            /**
             * 这里比deleteMin多了一步操作，因为右子节点从父节点中获得节点的时候，我们需要将左边节点给于到右边节点，如果我们不移动的话，会破坏树的平衡
             *          5,6
             *      1,2     9       对于所展示的这个红黑树，如果不把5从左边移到右边的话，我们会直接删除9，这样会导致树的不平衡，因为红节点总是在左边的，我们进行删除操作的时候，直接将结点给予，只需要改变颜色即可，不需要移动
             *                      对于红黑树而言，6是黑结点，再删除的时候，是不需要移动的，我们移动的是5这样的红结点
             *
             */
            h = rotateRight(h);
        }
        if(h.right == null){
            return null;
        }
        if(!isRed(h.right) && !isRed(h.right.left)){
            h = moveRedRight(h);
        }
        h.right = deleteMax(h.right);
        return balance(h);
    }

    public void delete(K key){
        if(!isRed(root.left)&& !isRed(root.right)){
            root.color = RED;
        }
        root = delete(root,key);
        root.color = BLACK;
    }

    private Node delete(Node h, K key){

        if(key.compareTo(h.key) < 0){          // 当目标键小于当前键的时候，我们做类似于寻找最小是的操作，向树左边移动，合并父子结点来消除2-结点
            if(h.left == null){
                return null;
            }
            if(isRed(h.left) && !isRed(h.left.left)){
                h = moveRedLeft(h);
            }
            h.left = delete(h.left,key);
        }else{                  // 当目标键大于当前键的时候，我们向右移动，并做与deleteMax相同的操作，如果相同的话，我们使用和二叉树的删除一样的操作，获取当前键的右子树的最小健，然后交换，并将目标键删除
            if(isRed(h.left)){
                h = rotateRight(h);
            }
            if(key != h.key && h.right == null){    // 我们没有找到目标键，我们将其删除
                return null;
            }
            if(!isRed(h.right) && isRed(h.right.left)){
                h = moveRedRight(h);
            }
            if(key == h.key){
                h.val = get(min(h.right).key, h.right);
                h.key = min(h.right).key;
                h.right = deleteMin(h.right);
            }
            else h.right = delete(h.right,key);
        }
        return balance(h);
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

    private void  keys(Node node, List queue, K lo, K hi){
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
        return min(x.right);
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
