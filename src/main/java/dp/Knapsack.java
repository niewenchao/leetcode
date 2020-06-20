package dp;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 背包问题综合
 * 0-1背包问题knapsack：背包可装载重量W，N个物品，每个物品重量wt[i],价值val[i],最多能装多少价值？
 *  dp[i][w]：前i个物品，背包容量w，最多可装价值
 *  dp[i][w] = dp[i-1][w-val[i-1]],dp[i-1][w]
 * 完全背包问题之零钱兑换：给不同面额的硬币和总金额，计算可以凑成总金额的硬币组合个数？每一种硬币面额无数（可选物品无数，因此为完全背包问题）
 *  dp[i][w]: 前一个硬币，金额w，最多组合数
 *  dp[i][w] = dp[i - 1][w] + dp[i][w-val[i]]
 *
 */
public class Knapsack {
    public static int knapsack01(int W, int[] wt, int[] val){
        int N = wt.length;
        int[][] dp = new int[N + 1][W + 1];
        //base case
        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], 0);
        }

        for (int i = 1; i < N + 1; i++) {
            for (int w = 1; w <= W; w++) {
                if(wt[i-1] <= w){
                    dp[i][w] = Math.max(dp[i-1][w - wt[i-1]] + val[i-1],dp[i-1][w]);
                }
                else {
                    dp[i][w] = dp[i-1][w];
                }

            }
        }
        return dp[N][W];
    }

    public static int change(int amount, int[] coins){
        int N = coins.length;
//        int[][] dp = new int[N + 1][amount + 1];
        //base case
//        Arrays.fill(dp[0], 0);
//        for (int i = 0; i < N + 1; i++) {
//            dp[i][0] = 1;
//        };
//        for (int i = 1; i <= N; i++) {
//            for (int j = 1; j <= amount ; j++) {
//                if(coins[i-1] > j){
//                    dp[i][j] = dp[i - 1][j];
//                }
//                else {
//                    dp[i][j] = dp[i - 1][j] + dp[i][j - coins[i - 1]];//状态压缩:i为本轮数值，需要使用本轮已经改变过值，因此用顺序
//                }
//            }
//        }
//        return dp[N][amount];

        //状态压缩
        int[] dp = new int[amount + 1];
        //base case
        dp[0] = 1;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= amount; j++) {
                if(j >= coins[i - 1]){
                    dp[j] = dp[j] + dp[j - coins[i - 1]];
                }
            }

        }
        return dp[amount];
    }

    /**
     * 分割等和子集：给定一个只包含正整数的非空数组，是否可以将数组分割为两个相等和相等的子集？等价数组是否可以填满sum/2
     * dp[i][j]:前i个元素可以填满j
     * dp[i][j] = dp[i-1][j-nums[i-1]] || dp[i-1][j]
     * @param nums
     * @return
     */
    public static boolean canPartition(int [] nums){
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        if(sum % 2 != 0) return false;
        sum /= 2;


//        boolean [][] dp = new boolean[nums.length+1][sum+1];
//        for (int i = 0; i < nums.length + 1; i++) {
//            dp[i][0] = true;
//        }
//        for (int i = 1; i <= nums.length; i++) {
//            for (int j = 0; j <= sum; j++) {
//                if(nums[i-1] > j){
//                    dp[i][j] = dp[i - 1][j];
//                }
//                else {
//                    dp[i][j] = dp[i - 1][j - nums[i - 1]] || dp[i - 1][j]; //i - 1为上一轮的值，为保证上一轮值不被改变，需使用逆序
//                }
//            }
//        }
//        return dp[nums.length][sum];

        //状态压缩版本
        boolean[] dp = new boolean[sum + 1];
        dp[0] = true;
        for (int i = 1; i <= nums.length; i++) {
            for (int j = sum; j >= 0; j--) {
                if(nums[i - 1] <= j){
                    dp[j] = dp[j] || dp[j - nums[i - 1]];
                }
            }
        }
        return dp[sum];

    }


}
