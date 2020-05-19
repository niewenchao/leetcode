public class Sort {

    /*
    describe:首先，找到最小的元素；其次将它与第一个元素交换，再次，从剩下的元素中找到最小的元素，将它与第二个元素交换。
    如此往复，直到将整个数组排序。
     */
    public static void selection(Comparable[] a){
        for (int i = 0; i < a.length; i++) {
            int min = i;
            for (int j = i+1; j < a.length; j++) {
                if(less(a[j],a[min])) min = j;
            }
            exch(a,i,min);
        }
    }

    /*
    describe:前i个元素有序，将第i+1个元素有序插入到前面的序列
     */
    public static void insertion(Comparable[] a){
        int count = 0;
        int each = 0;
        for (int i = 1; i < a.length; i++) {
            Comparable temp = a[i];
            for (int j = i; j > 0; j--) {
                count++;
                if(less(temp,a[j-1])) {a[j] = a[j-1]; each++;}//exch=>a[j] = a[j-1]  减少元素交换次数
                //减少判断次数
                else {a[j] = temp;break;}
            }
        }
        System.out.println("count:" + count);
        System.out.println("each:" + each);
    }



    /**
     *
     * 模板框架代码
     */
    public static void sort(Comparable[] a){

    }

    private static boolean less(Comparable v, Comparable w){
        return v.compareTo(w) < 0;
    }

    private static void exch(Comparable[] a,int i,int j){
        Comparable t = a[i];a[i] = a[j];a[j] = t;
    }
    private static void show(Comparable[] a){
        //单行打印数组
        for(int i = 0; i < a.length; i++){
            System.out.println(a[i] + " ");
        }
        System.out.println();
    }

    public static boolean isSorted(Comparable[] a){
        for (int i = 1; i < a.length; i++) {
            if(less(a[i],a[i-1])) return false;
        }
        return true;
    }
    /*****************************************************************/

    /*
    测试代码
     */
    public static void main(String[] args){
        Integer[] list = {2,3,4,53,3,534,34,4};
        Sort.insertion(list);
        Sort.show(list);
    }
}
