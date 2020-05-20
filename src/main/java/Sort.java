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
            int j = i;
            while (j > 0){
                count++;
                if(less(temp,a[j-1])) {a[j] = a[j-1]; each++;j--;}
                else break;
            }
//            for (int j = i; j > 0; j--) {
//                count++;
//                if(less(temp,a[j-1])) {a[j] = a[j-1]; each++;}//exch=>a[j] = a[j-1]  减少元素交换次数
//                //减少判断次数
//                else break;
//            }
            a[j] = temp;
        }
        System.out.println("count:" + count);
        System.out.println("each:" + each);
    }

    /**
     * 希尔排序又名缩减增量排序，插入排序的特殊版本，引入了步长概念，选定初始步长h，按照一定步长缩减序列直到1，对期间每个步长间隔的元素进行插入排序。
     * 希尔排序步长为1时相当于插入排序，利用插入排序，在有序性更高的情况，效率更高的特性，利用步长较大情况下的前置排序，提高序列有序性，
     * 使最终步长为1时的插入排序更高
     * @param a
     */
    public static void shell(Comparable[] a){
        int N = a.length;
        int gap = 1;
        while (gap < N/3) gap = 3*gap +1;
        //步长为1时就相当于插入排序
        while(gap >= 1){
            for (int i = gap; i < a.length; i++) {
                for (int j = i; j >=gap ; j-=gap) {
                    if(less(a[j], a[j - gap])) exch(a,j,j-gap);
                    else break;
                }
            }
            gap /=3;
        }
    }

    public static void merge(Comparable[] a){
        Merge.sort(a);
    }

    public static void quick(Comparable[] a){
        Quick.sort(a);
    }


    /**
     *
     * 模板框架代码
     */
    public static void sort(Comparable[] a){

    }

    public static boolean less(Comparable v, Comparable w){
        return v.compareTo(w) < 0;
    }

    public static void exch(Comparable[] a,int i,int j){
        Comparable t = a[i];a[i] = a[j];a[j] = t;
    }
    public static void show(Comparable[] a){
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
        Integer[] list = {89,100,22,3,4,53,3,534,34,4};
        Sort.quick(list);
        Sort.show(list);
    }
}
