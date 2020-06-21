package dp;

import java.util.Arrays;

/**
 * 最长子序列问题
 */
public class LongestSubSequence {
    /**
     * LCS:最长公共子序列
     * dp[i][j] s1前i，s2前j 的最长公共子序列
     */
    public static int lcs(String s1, String s2){
        int M = s1.length(), N = s2.length();
        int[][] dp = new int[M+1][N+1];
        for (int i = 1; i <= M; i++) {
            for (int j = 1; j <= N; j++) {
                if(s1.charAt(i-1) == s2.charAt(j-1)){
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }
                else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[M][N];
    }

    /**
     * 最长递增子序列
     * dp[i] i之前的最长递增子序列
     */
    public static int lis(int[] nums){
        int[] dp = new int[nums.length];
        //base case
        Arrays.fill(dp,1);
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                //状态转移
                if(nums[i] > nums[j])
                    dp[i] = Math.max(dp[i],dp[j] + 1);
            }
        }
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    /**
     * 最长回文子串
     * dp[i][j] s[i....j]是否为回文子串
     */

    public static String longestPalindromeSubstr(String s){
        int N = s.length();
        if(N < 2) return s;
        //dp & base case
        boolean[][] dp = new boolean[N][N];
        //可以省略
        for (int i = 0; i < N; i++) {
            dp[i][i] = true;
        }
        int begin = 0, maxLen = 1;
        for (int i = N -2; i >= 0; i--) {
            for (int j = i + 1; j < N; j++) {
                if(s.charAt(i) == s.charAt(j)){
                    if(j - i < 3){
                        dp[i][j] = true;
                    }
                    else {
                        dp[i][j] =  dp[i + 1][j - 1];
                    }

                }
                if(dp[i][j] && (j - i + 1) > maxLen){
                    maxLen = j  - i + 1;
                    begin = i;
                }
            }

        }
        //return maxLen;
        return s.substring(begin, begin + maxLen);
    }


    /**
     * 最长回文子序列
     * dp[i][j] i,j之间的最长回文子序列
     */
    public static int longestPalindromeSubseq(String s){
        int N = s.length();
        //dp & base case
        int[][] dp = new int[N][N];
        for (int i = 0; i < N ; i++) {
            dp[i][i] = 1;
        }
        for (int i = N -1; i >= 0 ; i--) {
            for (int j = i + 1; j < N ; j++) {
                if(s.charAt(i) == s.charAt(j)){
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                }
                else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i + 1][j]);
                }
            }
        }
        return dp[0][N - 1];
    }



    public static void main(String[] args){
       // System.out.println(LongestSubSequence.lcs("abcde", "ace"));
       // System.out.println(LongestSubSequence.lis(new int[]{1,2,3,2,4,7,4}));
       // System.out.println(LongestSubSequence.longestPalindromeSubseq ("aaaacaa"));
        System.out.println(LongestSubSequence.longestPalindromeSubstr("fsdfsdsaabdfsadfsdfsadgsdgasdfdsfwefsdfsdfsdfsdfsdfsdfsdfsdfbsdfsdfasdfwefsdfasdfaadd"));

    }
}
