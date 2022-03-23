package com.skaleto.things.leet;

public class Q72 {

    public static void main(String[] args) {
        Q72 q = new Q72();
        System.out.println(q.minDistance("horse", "ros"));
    }

    public int minDistance(String word1, String word2) {

        /**
         * 定义二维dp[word1.length+1][word2.length+1]
         * dp[i][j]表示word1从开始到第i位的子串变到word2从开始到第j位的子串需要变化的最小操作数
         * dp第一行和第一列的数值为另一个非空子串的长度（即从""变到"abc"要插入三次）
         *
         * 对于例子word1 = "horse", word2 = "ros"
         * 	    “”	r	o	s
         * “”	0	1	2	3
         * h	1	1	2	3
         * o	2	2	1	2
         * r	3	2	2	2
         * s	4	3	3	2
         * e	5	4	4	3
         *
         * 假如s1[i]==s2[j]，dp[i][j]=dp[i-1][j-1]
         * 假如s1[i]!=s2[j]，dp[i][j]=min(dp[i-1][j],dp[i][j-1],dp[i-1][j-1]) + 1
         *    其中min中的三种状态分别对应word1删除一个字符、添加一个字符、替换一个字符
         */

        char[] s1 = word1.toCharArray();
        char[] s2 = word2.toCharArray();
        int[][] dp = new int[s1.length + 1][s2.length + 1];
        for (int i = 0; i <= s1.length; i++) {
            dp[i][0] = i;
        }

        for (int j = 0; j <= s2.length; j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i <= s1.length; i++) {
            for (int j = 1; j <= s2.length; j++) {
                if (s1[i - 1] == s2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                }
            }
        }

        return dp[s1.length][s2.length];

    }

    public int minDistance_20220118(String word1, String word2) {
        /**
         * 两个字符串，求最值，考虑用二维dp
         *
         * dp[i][j]表示word1从0~i的子串 -> word2从0~j的子串的最少操作数
         *
         * horse -> ros
         * 	    ""	r	o	s
         *  ""	0	1	2	3
         *  h	1	1	2	3
         *  o	2	2	1	2
         *  r	3	2	2	2
         *  s	4	3	3	2
         *  e	5	4	4	3
         *
         * 假如word1[i] == word2[j]
         *    dp[i][j]=dp[i-1][j-1]
         * 假如word[i] != word2[j]
         *    dp[i][j]=Math.min(dp[i-1][j],dp[i][j-1],dp[i-1][j-1])+1
         *
         */

        char[] char1 = word1.toCharArray();
        char[] char2 = word2.toCharArray();

        int[][] dp = new int[char1.length + 1][char2.length + 1];

        for (int i = 0; i <= char1.length; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= char2.length; j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i <= char1.length; i++) {
            for (int j = 1; j <= char2.length; j++) {
                if (char1[i - 1] == char2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
                }
            }
        }

        return dp[char1.length][char2.length];

    }


    public int minDistance_20220229(String word1, String word2) {
        /**
         *      ""  h   o   r   s   e
         * ""   0   1   2   3   4   5
         * r    1   1   2   2   3   4
         * o    2   2   1   2   3   4
         * s    3   3   2   2   2   3
         */

        char[] char1 = word1.toCharArray();
        char[] char2 = word2.toCharArray();
        int[][] dp = new int[char1.length + 1][char2.length + 1];
        for (int i = 0; i < char1.length; i++) {
            dp[i + 1][0] = i + 1;
        }

        for (int j = 0; j < char2.length; j++) {
            dp[0][j + 1] = j + 1;
        }

        for (int i = 1; i <= char1.length; i++) {
            for (int j = 1; j <= char2.length; j++) {
                if (char1[i - 1] == char2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                }
            }
        }

        return dp[char1.length][char2.length];

    }
}
