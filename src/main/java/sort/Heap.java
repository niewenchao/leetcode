package sort;

/*
使用数组维持的二叉堆结构，索引1开始，元素k父节点为：k/2  , 子节点：2k,2k+1  ,
                          0                  (k-1)/2              2K+1,2k+2
堆构建：如果一个节点的两个子节点已经是堆了，在该节点sink就是堆了；利用该特性从左到右构建堆sink 比 从右到左通过插入元素方式swim构建堆要更快
堆排序：类似于优先队列delMax exch(1,N--) sink(1,N)

 */
public class Heap {
   public static void sort(Comparable[] a){
       int N = a.length-1 ;                     //索引为0 .... N
       for (int i = (N-1)/2; i >= 0 ; i--) {
            sink(a,i,N);
       }
       while (N > 0){
           Sort.exch(a,0,N--);
           sink(a,0,N);
       }
   }

    private static void sink(Comparable[] a, int k, int N){
        while (k <= (N-1)/2){
            int c = 2*k + 1;
            if( (c+1) <= N && Sort.less(a[c], a[c+1]) ) c++;
            if( Sort.less(a[k], a[c]) ) { Sort.exch(a, k ,c); k=c; }
            else break;
        }

    }

    public static void main(String[] args){

    }
}
