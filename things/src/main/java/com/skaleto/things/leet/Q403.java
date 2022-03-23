package com.skaleto.things.leet;

import java.util.HashMap;
import java.util.Map;

public class Q403 {

    /**
     * 暴力dfs
     *
     * @param stones
     * @return
     */
    public boolean canCross_20220119_dfs(int[] stones) {
        /**
         * dfs
         *
         * 在第i个位置(距离为curPos)，每次走step-1，step，step+1步，
         * 检查curPos+step-1，curPos+step，curPos+step+1是否可达
         */

        //记录每个位置的索引值，用于后面判断某个位置是否可达
        Map<Integer, Integer> stoneMap = new HashMap<>(stones.length);
        for (int i = 0; i < stones.length; i++) {
            stoneMap.put(stones[i], i);
        }

        //如果不存在1位置的索引值，说明不可达，例如[0,2]这种用例
        if (!stoneMap.containsKey(1)) {
            return false;
        }

        return dfs(stones, stoneMap, 1, 1);
    }

    boolean dfs(int[] stones, Map<Integer, Integer> stoneMap, int curIndex, int step) {
        //结束条件
        if (curIndex == stones.length - 1) {
            return true;
        }

        //搜索条件，step-1，step，step+1各扫一遍
        for (int i = -1; i <= 1; i++) {
            //注意step可能为1，此时会stackoverflow，因为一直在原地踏步
            if (step + i == 0) {
                continue;
            }
            //新的位置=当前位置长度+步长
            int newPos = stones[curIndex] + step + i;
            //检查新的位置是否可达
            if (stoneMap.containsKey(newPos)) {
                //可达的情况下，在当前位置的基础上向下搜索是否可达
                boolean tmp = dfs(stones, stoneMap, stoneMap.get(newPos), step + i);
                //因为结果只要是否存在，那么遇到可能的值直接返回即可
                if (tmp) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * 记忆化dfs
     * Your runtime beats 34.27 % of java submissions
     * Your memory usage beats 74.18 % of java submissions (43.4 MB)
     *
     * @param stones
     * @return
     */
    public boolean canCross_20220119_dfs_memo(int[] stones) {
        /**
         * dfs
         *
         * 在第i个位置(距离为curPos)，每次走step-1，step，step+1步，
         * 检查curPos+step-1，curPos+step，curPos+step+1是否可达
         */
        //记录每个位置的索引值，用于后面判断某个位置是否可达
        Map<Integer, Integer> stoneMap = new HashMap<>(stones.length);
        for (int i = 0; i < stones.length; i++) {
            stoneMap.put(stones[i], i);
        }

        //如果不存在1位置的索引值，说明不可达，例如[0,2]这种用例
        if (!stoneMap.containsKey(1)) {
            return false;
        }

        //构建一个key为curIndex_step，value为在curIndex，step参数下的计算结果
        //也可以使用一个int[][]数组来表示两维特征
        Map<String, Boolean> memo = new HashMap<>();

        return dfs(stones, stoneMap, memo, 1, 1);
    }

    boolean dfs(int[] stones, Map<Integer, Integer> stoneMap, Map<String, Boolean> memo, int curIndex, int step) {
        String key = curIndex + "_" + step;

        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        //结束条件
        if (curIndex == stones.length - 1) {
            return true;
        }

        //搜索条件，step-1，step，step+1各扫一遍
        for (int i = -1; i <= 1; i++) {
            //注意step可能为1，此时会stackoverflow，因为一直在原地踏步
            if (step + i == 0) {
                continue;
            }
            //新的位置=当前位置长度+步长
            int newPos = stones[curIndex] + step + i;
            //检查新的位置是否可达
            if (stoneMap.containsKey(newPos)) {
                //可达的情况下，在当前位置的基础上向下搜索是否可达
                boolean tmp = dfs(stones, stoneMap, memo, stoneMap.get(newPos), step + i);
                memo.put(key, tmp);
                //因为结果只要是否存在，那么遇到可能的值直接返回即可
                if (tmp) {
                    return true;
                }
            }
        }

        return false;
    }


    public boolean canCross_20220119_dp(int[] stones) {
        /**
         * dp[i][k]代表跳到i位置，并且跳了k步是否能跳到
         *
         * 假设跳到i之前，上一步的位置是j
         * 那么
         * dp[i][k]= dp[j][k-1] || dp[j][k] || dp[j][k+1]
         *
         * j可以在1~i之间任意取，那么k怎么计算？
         *
         * 因为是从j跳到i，那么k=stones[i]-stones[j]
         */

        if (stones[1] != 1) {
            return false;
        }
        if (stones.length == 2) {
            return true;
        }

        boolean[][] dp = new boolean[stones.length][stones.length];

        //位置1始终为true
        dp[1][1] = true;
        //从位置2开始计算
        for (int i = 2; i < stones.length; i++) {
            //j可以在1~i之间任意取
            for (int j = 1; j < i; j++) {
                int k = stones[i] - stones[j];
                //k不可能大于j+1，因为如果从1跳到j，最大的步长就是j，下一次（也就是k）最大也只能是j+1
                if (k <= j + 1) {
                    dp[i][k] = dp[j][k - 1] || dp[j][k] || dp[j][k + 1];
                    if (i == stones.length - 1 && dp[i][k]) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
