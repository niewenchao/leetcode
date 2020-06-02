package sort;

public class MinPQ<T extends Comparable<T>> extends MaxPQ<T>{
    public MinPQ(int max){
     super(max);
 }

    @Override
    public boolean less(int i, int j) {
        return !super.less(i, j);
    }

    public static void main(String[] args){
        Integer[] list = {89,100,22,3,4,53,3,534,34,4};
        MinPQ<Integer> i_pq = new MinPQ<>(list.length);
        for (Integer i:list){
            i_pq.insert(i);
        }
        while (!i_pq.isEmpty()){
            System.out.println(i_pq.delMax());
        }
    }
}
