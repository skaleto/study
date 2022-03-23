package com.skaleto.things.leet;

/**
 * 新华三笔试题
 * @author yaoyibin
 */
public class Q_Interview_H3C {
    public static void main(String[] args) {
        Q_Interview_H3C t = new Q_Interview_H3C();
        System.out.println(t.demo2(new int[]{3, 1, 2, 4},1));
    }

    /**
     * 输入1个int数组，去除中间的1个数字，求剩下数字组成的数字最大的抽取方式
     */
    public int demo1(int[] input) {
        int max = Integer.MIN_VALUE;
        int[] temp = new int[input.length - 1];
        for (int i = 0; i < input.length; i++) {
            if (i > 0) {
                System.arraycopy(input, 0, temp, 0, i);
                System.arraycopy(input, i + 1, temp, i, input.length - 1 - i);
            } else {
                System.arraycopy(input, 1, temp, 0, input.length - 1);
            }

            int tempResult = getNumber(temp);
            max = Math.max(max, tempResult);
        }

        return max;
    }

    public int getNumber(int[] input) {
        int length = input.length;
        int result = 0;
        for (int i = 0; i < input.length; i++) {
            result += input[i] * Math.pow(10, length - 1 - i);
        }
        return result;
    }


    /**
     * 变体:
     * 输入1个int数组，去除中间的k个数字，求剩下三个数组成的数字最大的抽取方式
     */
    public int demo2(int[] input, int k) {
        /**
         * dp[i][j]表示input[0...i]中间去除j个数字得到的最大数字
         * 不去除第i个时，dp[i][j]=dp[i-1][j]*10+input[i]
         * 去除第i个时，dp[i][j]=dp[i-1][j-1]
         * dp[i][j]=Math.max(dp[i-1][j]*10+input[i], dp[i-1][j-1])
         */

        int[][] dp=new int[input.length+1][k+1];
        dp[0][0]=0;
        for(int i=1;i<=input.length;i++){
            for(int j=1;j<=k;j++){
                dp[i][j]=Math.max(dp[i-1][j]*10+input[i-1], dp[i-1][j-1]);
            }
        }

        return dp[input.length][k];
    }
}
