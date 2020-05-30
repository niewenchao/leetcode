package sort;

/**
 * 排序性能测试框架
 */
public class SortCompare {
    public static double time(String alg,Comparable[] a){
        long startTime=System.currentTimeMillis();
        if(alg.equals("insertion")) Sort.insertion(a);
        if(alg.equals("selection")) Sort.selection(a);
        if(alg.equals("shell")) Sort.shell(a);
        if(alg.equals("merge")) Sort.merge(a);

        long endTime=System.currentTimeMillis();
        return endTime - startTime;
    }

    public static double timeRandomInput(String alg,int N, int T){
        double total = 0.0;
        Double[] a = new Double[N];
        for (int i = 0; i < T; i++) {
            for (int j = 0; j < N; j++) {
                a[j] = Math.random()*100000D;
            }
            total += time(alg,a);
        }
        return total;
    }

    public static void main(String[] args){
        String alg1 = args[0];
        String alg2 = args[1];
        int N = Integer.parseInt(args[2]);
        int T = Integer.parseInt(args[3]);
        double t1 = timeRandomInput(alg1,N,T);
        double t2 = timeRandomInput(alg2,N,T);
        System.out.println(alg1+":" +alg2 + ":" + t1/t2);
    }
}
