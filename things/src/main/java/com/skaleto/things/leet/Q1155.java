package com.skaleto.things.leet;

public class Q1155 {

    public int numRollsToTarget(int n, int k, int target) {
        /**
         * 从n个1，n个2，n个3……n个k中选择n个数总和为target
         *
         * f[i][j]前i个骰子组合成target的组合情况
         *
         * 不选i
         *     f[i][j]=f[i-1][j]  (不可能不选！！！！所有骰子都要选)
         * 选i
         *     f[i][j]=Math.sum(
         *              f[i-1][j-1],
         *              f[i-1][j-2],
         *              f[i-1][j-3],
         *              ...
         *              f[i-1][j-k],
         *              )
         */

        int mod=(int)1e9+7;
        int[][] dp=new int[n+1][target+1];
        //从0个骰子中组合成结果为0的情况有一种，什么都不选
        dp[0][0]=1;
        //从0个骰子中组合成结果为1，2，3...的情况有0种

        for(int i=1;i<=n;i++){
            for(int j=0;j<=target;j++){
                for(int m=1;m<=k;m++){
                    if(j>=m){
                        dp[i][j]+=dp[i-1][j-m];
                        dp[i][j]%=mod;
                    }
                }
            }
        }

        return dp[n][target];

    }

    public int numRollsToTarget_space_opt(int n, int k, int target) {

        int mod=(int)1e9+7;
        int[] dp=new int[target+1];
        //从0个骰子中组合成结果为0的情况有一种，什么都不选
        dp[0]=1;
        //从0个骰子中组合成结果为1，2，3...的情况有0种

        for(int i=1;i<=n;i++){
            for(int j=target;j>=0;j--){
                //注意，每一轮遍历到j的时候，需要置为0
                dp[j] = 0;
                for(int m=1;m<=k;m++){
                    if(j>=m){
                        dp[j]+=dp[j-m];
                        dp[j]%=mod;
                    }
                }
            }
        }

        return dp[target];
    }
}
