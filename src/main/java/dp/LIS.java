package dp;

import java.util.Arrays;

/**
 * 最长递增子序列Longest Increasing SubSequence,子序列可以不连续
 * dp[i]:以结尾的最长递增子序列，最短为1
 * 状态转移方程：dp[i] = max(dp[j] + 1);j<i,nums[i] > nums[j]
 */
public class LIS {
    public static int lengthOfLIS(int[] nums){
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
}
