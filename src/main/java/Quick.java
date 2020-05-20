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
