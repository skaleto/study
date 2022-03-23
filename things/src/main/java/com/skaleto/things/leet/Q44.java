package com.skaleto.things.leet;

public class Q44 {

    public static void main(String[] args) {
        Q44 q=new Q44();
        System.out.println(q.isMatch("aa","a"));
    }

    /**
     * Your runtime beats 89.6 % of java submissions
     * Your memory usage beats 10.37 % of java submissions (41.5 MB)
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch(String s, String p) {
        /**
         *      ""  *   a   *   b
         * ""   T   T   F   F   F
         * a    F   T   T   T   F
         * d    F
         * c    F
         * e    F
         * b    F
         *
         * dp[i][j]表示s[0,i)是否匹配p[0,j)
         *
         * 如果s[i-1]==p[j-1] 或 p[j-1]=='?'
         *      dp[i][j]=dp[i-1][j-1]
         * 如果p[j-1]=='*'
         *      dp[i][j]=dp[i-1][j] || dp[i][j-1] || dp[i-1][j-1]
         * 如果s[i-1]!=p[j-1] 且 p[j-1]!='*' 且 p[j-1]!='?'
         *      dp[i][j]=false
         */

        char[] schar = s.toCharArray();
        char[] pchar = p.toCharArray();

        boolean[][] dp = new boolean[schar.length + 1][pchar.length + 1];

        dp[0][0] = true;
        for (int j = 1; j <= pchar.length; j++) {
            dp[0][j] = pchar[j - 1] == '*' && dp[0][j - 1];
        }

        for (int i = 1; i <= schar.length; i++) {
            for (int j = 1; j <= pchar.length; j++) {
                if (schar[i - 1] == pchar[j - 1] || pchar[j - 1] == '?') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (pchar[j - 1] == '*') {
                    dp[i][j] = dp[i - 1][j] || dp[i][j - 1] || dp[i - 1][j - 1];
                } else {
                    dp[i][j] = false;
                }
            }
        }

        return dp[schar.length][pchar.length];
    }
}
