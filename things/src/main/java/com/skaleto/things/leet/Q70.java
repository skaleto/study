package com.skaleto.things.leet;

public class Q70 {

    /**
     * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
     * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
     *
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        //f(n)=f(n-1)+f(n-2)，其实就是一个斐波那切数列
        if (n <= 2) {
            return n;
        }
        int f1 = 1, f2 = 2, f3 = 3;
        for (int i = 3; i < n + 1; i++) {
            f3 = f1 + f2;
            f1 = f2;
            f2 = f3;
        }
        return f3;
    }

    public int climbStairs_20220110(int n) {
        /**
         * 第n阶要么从第n-1阶走1阶到达，要么从第n-2阶走2阶到达
         *
         * dp[n]=dp[n-1]+dp[n-2];
         */

        int[] dp=new int[n+1];

        //第0阶不用走就能到达，有一种走法那就是走0不
        dp[0]=1;
        dp[1]=1;

        for(int i=2;i<=n;i++){
            dp[i]=dp[i-1]+dp[i-2];
        }

        return dp[n];

    }
}
