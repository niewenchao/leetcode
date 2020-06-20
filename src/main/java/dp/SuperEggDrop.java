package dp;

import java.util.Arrays;

/**
 * 高楼扔鸡蛋问题
 */
public class SuperEggDrop {
    /**
     * 有K个鸡蛋，N层楼，现在确定存在K，0<=F<=N ,在这层楼扔下去鸡蛋恰好没碎，最坏情况下，你至少要扔几次鸡蛋，才能确定楼层F呢？
     * dp(K,N) 结果
     */
    private int[][] memo;
    public  int superEggDrop(int K, int N){
        memo = new int[K + 1][];
        for (int i = 0; i <= K; i++) {
            memo[i] = new int[N + 1];
            Arrays.fill(memo[i],Integer.MAX_VALUE);
        }
        return dp(K,N);
    }
    private   int dp(int K, int N){
        if(K == 1) return N;
        if(N == 0) return 0;
        if(memo[K][N] < Integer.MAX_VALUE) return memo[K][N];
        int res = Integer.MAX_VALUE;
        for (int i = 1; i <= N; i++) {
            res = Math.min(res,Math.max(dp(K-1,i-1) + 1,dp(K, N - i) + 1));
        }
        return res;
    }

    public static void main(String[] args){
        System.out.println(new SuperEggDrop().superEggDrop(1,7));
    }
}
