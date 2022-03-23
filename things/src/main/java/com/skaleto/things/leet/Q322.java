package com.skaleto.things.leet;

import java.util.Arrays;

public class Q322 {

    public static void main(String[] args) {
        Q322 q = new Q322();
        System.out.println(q.coinChange(new int[]{186, 419, 83, 408}, 6249));
    }

    public int coinChange(int[] coins, int amount) {

        /**
         * 完全背包问题
         *
         * dp[i][j]表示第i个硬币凑成金额j的最少个数
         *
         * 不选i,dp[i][j]=dp[i-1][j]
         * 选i,dp[i][j]=dp[i][j-coins[i]]+1
         */

        int[][] dp=new int[coins.length+1][amount+1];
        dp[0][0]=0;
        for(int i=1;i<=amount;i++){
            //0个硬币凑不成金额大于0的情况
            dp[0][i]=-1;
        }

        for(int i=1;i<=coins.length;i++){
            for(int j=0;j<=amount;j++){
                dp[i][j]=dp[i-1][j];
                if(j>=coins[i-1]){
                    dp[i][j]=Math.min(dp[i][j],dp[i][j-coins[i-1]]+1);
                }
            }
        }

        return dp[coins.length][amount];
    }

    public int coinChange_20220105(int[] coins, int amount) {
        /**
         * 是一个完全背包问题
         * dp[i][j]表示把用i种硬币恰好凑成j的最少硬币个数
         * 不选第i种硬币：
         *      dp[i][j]= dp[i-1][j]
         * 选择第i种硬币：
         *      此时需要在前i种物品中选择硬币填满j-coins[i]的容量
         *      dp[i][j]= dp[i][j-coins[i]] + 1
         *
         * dp[i][j]=Math.min(dp[i-1][j], dp[i][j-coins[i]] + 1)
         *
         * 伪代码：
         * for i from 1 to N {
         *    for j from 0 to C {
         *       f[j] = Math.min(f[j], f[j-coins[i]]+1);
         *    }
         * }
         */
        int[] dp=new int[amount+1];
        //这里初始化的值不建议使用Integer.MAX_VALUE，因为在+1的时候会溢出
        Arrays.fill(dp, amount+1);

        dp[0]=0;

        for(int i=1;i<=coins.length;i++){
            for(int j=coins[i-1];j<=amount;j++){
                dp[j] = Math.min(dp[j], dp[j-coins[i-1]]+1);
            }
        }

        return dp[amount] != amount+1 ? dp[amount] : -1;

    }


//    public int coinChange(int[] coins, int amount) {
//
//        /**
//         * 状态转移方程：
//         * f(i)=min(f(i-k) with k in coins) + 1
//         */
//
//        int[] dp=new int[amount+1];
//        Arrays.fill(dp, amount+1);
//
//        dp[0] = 0;
//        for(int i = 1;i <=amount; i++){
//            for(int j=0;j<coins.length;j++){
//                if(i-coins[j]>=0){
//                    dp[i]=Math.min(dp[i-coins[j]]+1, dp[i]);
//                }
//            }
//        }
//
//        return dp[amount]>amount?-1:dp[amount];
//    }
}
