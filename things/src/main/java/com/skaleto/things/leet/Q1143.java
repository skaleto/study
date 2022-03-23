package com.skaleto.things.leet;

public class Q1143 {

    public static void main(String[] args) {
        Q1143 q = new Q1143();
        System.out.println(q.longestCommonSubsequence("abcde", "ace"));
    }

    public int longestCommonSubsequence(String text1, String text2) {
        char[] char1 = text1.toCharArray();
        char[] char2 = text2.toCharArray();

        int[][] dp = new int[char1.length + 1][char2.length + 1];

        /**
         * 状态转移方程：
         * if char1[i]==char2[j] : dp[i][j]=dp[i-1][j-1]+1
         * else dp[i][j]=min(dp[i-1][j], dp[i][j-1])
         */

        for (int i = 0; i < char1.length; i++) {
            for (int j = 0; j < char2.length; j++) {
                if (char1[i] == char2[j]) {
                    dp[i + 1][j + 1] = dp[i][j] + 1;
                } else {
                    dp[i + 1][j + 1] = Math.max(dp[i][j + 1], dp[i + 1][j]);
                }
            }
        }

        return dp[char1.length][char2.length];
    }

    public int longestCommonSubsequence_20220110(String text1, String text2) {
        char[] l1 = text1.toCharArray();
        char[] l2 = text2.toCharArray();

        int[][] dp = new int[l1.length + 1][l2.length + 1];

        /**
         *      ""   a    b    c    d    e
         * ""   0    0    0    0    0    0
         * a    0    1    1    1    1    1
         * c    0    1    1    2    2    2
         * e    0    1    1    2    2    3
         *
         *
         *      ""   j    m    j    k    b    k    j    k    v
         * ""   0    0    0    0    0    0    0    0    0    0
         * b    0    0    0    0    0    1    1    1    1    1
         * s    0    0    0    0    0    1    1    1    1    1
         * b    0    0    0    0    0    1    1    1    1    1
         * i    0    0    0    0    0    1    1    1    1    1
         * n    0    0    0    0    0    1    1    1    1    1
         * i    0    0    0    0    0    1    1    1    1    1
         * n    0    0    0    0    0    1    1    1    1    1
         * m    0    0    0    0    0    1    1    1    1    1
         *
         * 从头开始比较两个字符串，如果比较到某一位相等，那么最长子串等于左上角位置最长子串+1
         * 假如l1[i]==l2[j]
         *      dp[i][j]=dp[i-1][j-1]+1
         * 假如l1[i]!=l2[j]
         *      dp[i][j]=Math.max(dp[i-1][j-1],dp[i][j-1],dp[i-1][j])
         */

        for (int i = 1; i <= l1.length; i++) {
            for (int j = 1; j <= l2.length; j++) {
                if (l1[i - 1] == l2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j - 1], Math.max(dp[i][j - 1], dp[i - 1][j]));
                }
            }
        }

        return dp[l1.length][l2.length];

    }

    public int longestCommonSubsequence_20220209(String text1, String text2) {
        /**
         * dp[i][j]表示text1的前i位和text2的前j位的最长公共子序列长度
         *
         *      ""  a   b   c   d   e
         * ""   0   0   0   0   0   0
         * a    0   1   1   1   1   1
         * c    0   1   1   2   2   2
         * e    0   1   1   2   2   3
         *
         * 假如text1[i]==text2[j]，那么dp[i][j]=dp[i-1][j-1]+1
         * 假如text1[i]!=text2[j]，那么dp[i][j]=Math.max(dp[i-1][j], dp[i][j-1])
         */
        char[] char1 = text1.toCharArray();
        char[] char2 = text2.toCharArray();

        int[][] dp = new int[char1.length + 1][char2.length + 1];
        for (int i = 0; i < char1.length; i++) {
            for (int j = 0; j < char2.length; j++) {
                if (char1[i] == char2[j]) {
                    dp[i + 1][j + 1] = dp[i][j] + 1;
                } else {
                    dp[i + 1][j + 1] = Math.max(dp[i + 1][j], dp[i][j + 1]);
                }
            }
        }

        return dp[char1.length][char2.length];
    }
}
