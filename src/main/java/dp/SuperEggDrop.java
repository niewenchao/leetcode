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
        //穷举所有可能的选择，取每个选择最坏情况下的最小值
        for (int i = 1; i <= N; i++) {
            res = Math.min(res,Math.max(dp(K-1,i-1) + 1,dp(K, N - i) + 1));
        }
        memo[K][N] = res;
        return res;
    }

    /**
     *  dp[k][m]:有k个鸡蛋，m次数，请问最坏情况下可以测试的楼层高度
     *  dp[k][m] = dp[k][m-1] + dp[k-1][m-1] + 1;
     */
    public int superEggDrop01(int K, int N){
        int dp[][] = new int[K+1][N+1];
        int m = 0;
        //当前测试次数测不了N层楼时
        while (dp[K][m] < N){
            m++;            //增加测试次数
            for (int k = 1; k <= K; k++) {
                dp[k][m] = dp[k-1][m-1] + dp[k][m-1] + 1;
            }
        }
        return m;
    }

    public static void main(String[] args){
        System.out.println(new SuperEggDrop().superEggDrop01(2,1000));
    }
}
