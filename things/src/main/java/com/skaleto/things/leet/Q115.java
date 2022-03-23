package com.skaleto.things.leet;

public class Q115 {

    /**
     * Your runtime beats 97.56 % of java submissions
     * Your memory usage beats 6.8 % of java submissions (48.4 MB)
     * @param s
     * @param t
     * @return
     */
    public int numDistinct(String s, String t) {
        /**
         * dp[i][j]表示字符串s[0,i]的子序列中t[0,j]出现的个数
         *
         *      ""  r   a   b   b   i   t
         * ""   1   0   0   0   0   0   0
         * r    1   1   0   0   0   0   0
         * a    1   1   1   0   0   0   0
         * b    1   1   1   1   0   0   0
         * b    1   1   1   2   1   0   0
         * b    1   1   1   3   3   0   0
         * i    1   1   1   1   3   3   0
         * t    1   1   1   1   3   3   3
         *
         *
         *      ""  b   a   g
         * ""   1   0   0   0
         * b    1   1   0   0
         * a    1   1   1   0
         * b    1   2   1   0
         * g    1   2   1   1
         * b    1   3   1   1
         * a    1   3   4   1
         * g    1   3   4   5
         *
         * 假如s[i]==t[j]，
         *      dp[i][j]=dp[i-1][j-1]+dp[i-1][j]
         * 假如s[i]!=t[j]，
         *      dp[i][j]=dp[i-1][j]
         *
         */

        char[] schar = s.toCharArray();
        char[] tchar = t.toCharArray();

        int[][] dp = new int[schar.length + 1][tchar.length + 1];
        for (int i = 0; i < schar.length + 1; i++) {
            dp[i][0] = 1;
        }

        for (int i = 1; i <= schar.length; i++) {
            for (int j = 1; j <= tchar.length; j++) {
                if (schar[i - 1] == tchar[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        return dp[schar.length][tchar.length];
    }
}
