package sort;

/*
优先级队列，利用二叉堆父节点大于子节点特性
核心API：
delMax 删除返回最大元素，并维持二叉堆结构 使用节点下浮sink
insert 插入元素，并维持二叉堆结构 使用节点上浮swim
 */
public class MaxPQ<T extends Comparable<T>> {
    private T[] pq;                 //使用数组维持的二叉堆结构，索引1开始，元素k父节点为：k/2  , 子节点：2k,2k+1
    private int N = 0;              //队列长度为N，从索引1...N,0不使用

    public MaxPQ(int max){
        pq = (T[])new Comparable[max +1];
    }
    public int size(){
        return N;
    }

    public boolean isEmpty(){
        return 0 == N;
    }

    public T delMax(){              //删除元素需要下浮维持堆结构
        T max = pq[1];
        exch(1,N--);
        sink(1);
        pq[N+1] = null;             //防止野指针
        return max;
    }

    public void insert(T t){        //插入元素需要上浮维护堆结构
        pq[++N] = t;
        swim(N);
    }

    private void swim(int k){
        while (k > 1 && less(k/2,k)){
            exch(k/2,k); k /=2;
        }
    }

    private void sink(int k){
        while (k <= N/2){
            int c = 2*k;
            if( (c+1) < N && less(c,c+1) ) c++;
            if( less(k, c) ) { exch(k,c); k=c; }
            else break;
        }

    }

    public boolean less(int i, int j){
        if(pq[i].compareTo(pq[j]) == -1) return true;return false;
    }

    private void exch(int i, int j){
        T temp = pq[i]; pq[i] = pq[j]; pq[j] = temp;
    }

    public static void main(String[] args){
        Integer[] list = {89,100,22,3,4,53,3,534,34,4};
        MaxPQ<Integer> i_pq = new MaxPQ<>(list.length);
        for (Integer i:list){
            i_pq.insert(i);
        }
        while (!i_pq.isEmpty()){
            System.out.println(i_pq.delMax());
        }
    }



}
