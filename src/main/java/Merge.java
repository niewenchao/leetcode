/*
归并排序的核心是实现左右两个有序子序列的merge,需要额外占用一个数组的外部存储空间。通过递归方式不断拆分左右子序进行merger完成排序
 */
public class Merge {
    public static Comparable[] aux = new Comparable[10];
    public static void merge(Comparable[] a, int low, int mid, int high){
        int left = low;
        int right = mid + 1;
        for (int i = low; i <= high; i++) {
            aux[i] = a[i];
        }
        for (int i = low; i <= high ; i++) {
            if (left > mid) a[i] = aux[right++];
            else if(right > high) a[i] = aux[left++];
            else if(Sort.less(aux[left],aux[right])) a[i] = aux[left++];
           else a[i] = aux[right++];

        }

    }
    public static void sort(Comparable[] a){
        aux = new Comparable[a.length];//分配一次性空间
        sort(a,0,a.length - 1);
    }

    private static void sort(Comparable[] a, int low, int high){
        if(low >= high) return;
        int mid = low + (high - low)/2;
        sort(a, low, mid);//排序左边
        sort(a, mid+1, high);//排序右边
        merge(a, low, mid, high);
    }
/*
        自底向上归并排序，先以子序列长度为1merge排序，再以子序长度为2 merge排序，直到step >= len -step，做(0,step,len - 1) merge排序
 */
    public static void sortBU(Comparable[] a){
        int N = a.length;
        aux = new Comparable[N];//分配一次性空间
        for (int step = 1; step < N ; step = 2*step) {
            for (int j = 0; j < N - step; j+= 2*step) {
                int low = j;
                int mid = j + step -1;
                int high = j + 2*step - 1;
                if(high > N - 1) high = N - 1;
                merge(a,low,mid,high);
            }
        }
    }

    /*
   测试代码
    */
    public static void main(String[] args){
        Integer[] list = {89,100,22,3,4,53,3,534,34,4};
        Merge.sortBU(list);
        Sort.show(list);
    }
}
