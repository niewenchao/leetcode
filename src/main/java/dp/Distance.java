package dp;


import sort.MaxPQ;

public class Distance {
    /**
     *
     *给定S1，S2 计算S1转换为S2所需要的最小操作数。可以进行插入，删除，替换三种操作
     * dp[i][j]:S1[0...i],S2[0...j]最小操作数
     *
     *
     */
    public static int minDistance(String S1, String S2){
        int M = S1.length(), N = S2.length();
        int[][] dp = new int[M + 1][N + 1];
        //base case
        for (int i = 1; i <= N; i++) {
            dp[0][i] = i;
        }
        for (int i = 1; i <= M; i++) {
            dp[i][0] = i;
        }
        for (int i = 1; i <= M; i++) {
            for (int j = 1; j <= N ; j++) {
                if(S1.charAt(i-1) == S2.charAt(j-1)){
                    dp[i][j] = dp[i - 1][j - 1];
                }
                else {
                    dp[i][j] = min(
                            dp[i][j-1] + 1,
                            dp[i-1][j] + 1,
                            dp[i-1][j-1] + 1);
                }
            }
        }
        return dp[M][N];

    }

    public static int min(int a, int b, int c){
        return Math.min(a,Math.min(b,c));
    }
}

