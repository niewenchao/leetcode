package sort;

/*
    思想：核心是切分，选定一个值，使所有小于它的元素位于左边，大于它的元素位于右边，使用左右指针扫描互换不满足条件的元素，直到左右指针相遇。
    三向切分快排，改进版的快速排序，在上面的基础上，将切分值相等的元素收集成为切分序列，使序列左边都小于它，右边都大于它
 */
public class Quick {
    public static void sort(Comparable[] a){
        sort(a,0, a.length - 1);
    }
    public static void sort(Comparable[] a, int low, int high){
        if(low >= high) return;
        int part = partition(a,low,high);
        sort(a,low,part -1);
        sort(a,part+1,high);
    }

    public static int partition(Comparable[] a,int low, int high){
        Comparable v = a[low];
        int l = low ,h = high + 1;
        while (true){
            while (Sort.less(a[++l],v)) {if(l == high) break;}
            while (Sort.less(v,a[--h])) { if(h == low) break;}
            if(l < h) {Sort.exch(a,l,h);}
            else break;
        }
        Sort.exch(a,low,h);
        return h;

    }
}
