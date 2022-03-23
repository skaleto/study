package com.skaleto.things.leet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Q120 {

    public static void main(String[] args) {
        List<List<Integer>> triangle = new ArrayList<>();
        triangle.add(Arrays.asList(2));
        triangle.add(Arrays.asList(3, 4));
        triangle.add(Arrays.asList(6, 5, 7));
        triangle.add(Arrays.asList(4, 1, 8, 8));
        Q120 q = new Q120();
        System.out.println(q.minimumTotal(triangle));
    }

    /**
     * 自顶向下动规递推
     * [
     * [2],
     * [3,4],
     * [6,5,7],
     * [4,1,8,3]
     * ]
     *
     * @param triangle
     * @return
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        int length = triangle.size();
        int[][] dp = new int[length][length];

        /**
         * 状态转移方程
         * 对于点i，j来说，他的最短距离等于它上方或左上方的点的最小值加上他本身
         * dp[i][j]=Math.min(dp[i-1][j-1],dp[i-1][j])+triangle.get(i).get(j)
         */

        //顶点特殊赋值
        dp[0][0] = triangle.get(0).get(0);

        for(int i = 1; i < length; i++){
            //最左边情况特殊考虑，只能等于自身数值加上上方数值
            dp[i][0] = triangle.get(i).get(0) + dp[i-1][0];

            //中间数值等于自身数值加上上方两个数值中较小的数值
            for(int j = 1; j < i; j++){
                dp[i][j] = Math.min(dp[i-1][j-1], dp[i-1][j]) + triangle.get(i).get(j);
            }

            //最右边情况特殊考虑，只能等于自身数值加上上方数值
            dp[i][i] = triangle.get(i).get(i) + dp[i-1][i-1];
        }

        //找出最底层中最小的值
        int minDistance = Integer.MAX_VALUE;
        for(int k = 0;k< length; k++){
            minDistance = Math.min(minDistance, dp[length-1][k]);
        }

        return minDistance;

    }


    /**
     * 自底向上动规递推
     * @param triangle
     * @return
     */
    public int minimumTotal_1(List<List<Integer>> triangle) {
        int length = triangle.size();
        int[][] dp = new int[length][length];

        /**
         * 状态转移方程
         * 对于点i，j来说，他的最短距离等于它下方或右下方的点的最小值加上他本身
         * dp[i][j]=Math.min(dp[i+1][j],dp[i+1][j+1])+triangle.get(i).get(j)
         */

        //底边特殊赋值
        for(int j = 0;j < length; j++){
            dp[length-1][j] = triangle.get(length-1).get(j);
        }

        for(int i = length-2; i >= 0; i--){
            //中间数值等于自身数值加上上方两个数值中较小的数值
            for(int j = 0; j <= i; j++){
                dp[i][j] = Math.min(dp[i+1][j], dp[i+1][j+1]) + triangle.get(i).get(j);
            }
        }

        return dp[0][0];

    }

    /**
     * 在minimumTotal_1的基础上，把dp数组减少一维，因为每次都是一行一行更新，在没更新前，数组中记录的就是上一行的结果
     * @param triangle
     * @return
     */
    public int minimumTotal_2(List<List<Integer>> triangle) {
        int length = triangle.size();
        int[] dp = new int[length];

        /**
         * 状态转移方程
         * 对于点i，j来说，他的最短距离等于它下方或右下方的点的最小值加上他本身
         * dp[i][j]=Math.min(dp[i+1][j],dp[i+1][j+1])+triangle.get(i).get(j)
         */

        //底边特殊赋值
        for(int j = 0;j < length; j++){
            dp[j] = triangle.get(length-1).get(j);
        }

        for(int i = length-2; i >= 0; i--){
            //中间数值等于自身数值加上上方两个数值中较小的数值
            for(int j = 0; j <= i; j++){
                dp[j] = Math.min(dp[j], dp[j+1]) + triangle.get(i).get(j);
            }
        }

        return dp[0];

    }


    public int minimumTotal_20220110(List<List<Integer>> triangle) {

        int size = triangle.size();
        //向下冗余一层方便遍历
        int[][] dp = new int[size + 1][size + 1];

        //底边往上遍历
        for (int i = size - 1; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                dp[i][j] = Math.min(dp[i + 1][j], dp[i + 1][j + 1]) + triangle.get(i).get(j);
            }
        }
        /**
         * [
         * [2],
         * [3,4],
         * [6,5,7],
         * [4,1,8,3]
         * ]
         * 遍历结果：
         *
         * 0                0               0                  0                11
         * 0，0             0，0             0，0               9，10            9，10
         * 0，0，0      ->  0，0，0       ->  7，6，10       ->  7，6，10      -> 7，6，10
         * 4，1，8，3       4，1，8，3         4，1，8，3         4，1，8，3       4，1，8，3
         * 0，0，0，0，0    0，0，0，0，0       0，0，0，0，0      0，0，0，0，0    0，0，0，0，0
         *
         */
        return dp[0][0];

    }

    public int minimumTotal_20220110_space_opt(List<List<Integer>> triangle) {
        int size = triangle.size();
        int[] dp = new int[size + 1];

        for (int i = size - 1; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                dp[j] = Math.min(dp[j], dp[j + 1]) + triangle.get(i).get(j);
            }
        }
        return dp[0];

    }


}
