package com.skaleto.things.leet;

public class Q10 {

    /**
     * Your runtime beats 100 % of java submissions
     * Your memory usage beats 5.02 % of java submissions (40.3 MB)
     *
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch(String s, String p) {
        /**
         * dp[i][j]表示s[0,i)是否能匹配p[0,j)
         *
         * 如果s[i-1]==p[j-1] 或 p[j-1]=='.'
         *      表明此时p是单个的字符(或者是任意字符)，那么当前是否匹配就等于前面一个字符是否匹配
         *      dp[i][j]=dp[i-1][j-1]
         * 如果s[i-1]!=p[j-1] 且 p[j-1]!='.'，
         *      如果p[j-1]=='*'，要看前面一个字符p[j-2]，【例如s=abcc, p=abc*】
         *          如果s[i-1]==p[j-2] 或 p[j-2]=='.'，那么dp[i][j]有下面的情况
         *              dp[i][j]=dp[i-1][j]，此时看s[0,i-1)是否匹配当前串"abc*"，即前面存在多个c
         *              dp[i][j]=dp[i][j-2]，此时看s是否匹配串"ab"，即把"c*"去掉前面不存在c的情况
         *              dp[i][j]=dp[i][j-1]，此时看s是否匹配串"abc"，即前面只存在一个c
         *          如果s[i-1]!=p[i-2] 且 p[j-2]!='.'，那么dp[i][j]=dp[i][j-2]，只能把"c*"去掉考虑
         *      如果p[j-1]!='*'，那么这种情况不匹配
         *
         */
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        //""匹配""是为true的，但"xxx"匹配""是false
        dp[0][0] = true;

        char[] schar = s.toCharArray();
        char[] pchar = p.toCharArray();

        //初始化第0行
        for (int i = 1; i <= pchar.length; i++) {
            //如果p[0,i)的最后一位是*【例如abc*】，那么把"c*"去掉看是否匹配"ab"，因为此时的s=""
            dp[0][i] = (pchar[i - 1] == '*' && dp[0][i - 2]);
        }


        for (int i = 1; i <= schar.length; i++) {
            for (int j = 1; j <= pchar.length; j++) {
                if (schar[i - 1] == pchar[j - 1] || pchar[j - 1] == '.') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {

                    if (pchar[j - 1] == '*') {
                        if (schar[i - 1] == pchar[j - 2] || pchar[j - 2] == '.') {
                            dp[i][j] = dp[i - 1][j] || dp[i][j - 2] || dp[i][j - 1];
                        } else {
                            dp[i][j] = dp[i][j - 2];
                        }
                    } else {
                        dp[i][j] = false;
                    }
                }
            }
        }

        return dp[s.length()][p.length()];
    }
}
