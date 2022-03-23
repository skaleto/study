package com.skaleto.things.leet;

public class Q91 {

    public static void main(String[] args) {
        Q91 q = new Q91();
        System.out.println(q.numDecodings_20220118("2101"));
    }


    public int numDecodings(String s) {
        /**
         * 令dp[i]为字符串从0到i位置的解码方法数
         * 1. 假如s[i]单独算，那么dp[i]=dp[i-1]，什么情况下可以单独算？
         *        s[i]!=0即可
         * 2. 假如s[i]和s[i-1]一起算，那么dp[i]=dp[i-2]，什么情况下可以一起算？
         *        s[i-1]==1 或者 (s[i-1]==2且s[i]<=6) 即可
         *
         */

        char[] sc = s.toCharArray();
        int[] dp = new int[sc.length];
        if (sc[0] == '0') {
            return 0;
        }

        dp[0] = 1;

        for (int i = 1; i < sc.length; i++) {

            //如果i为0
            if (sc[i] == '0') {
                if (sc[i - 1] == '1' || (sc[i - 1] == '2' && sc[i] <= '6')) {
                    //此时s[i]和s[i-1]一起算
                    //第一个数要特殊处理，正常dp[1]=dp[-1]，但dp[-1]不存在（也就是当串为空时应该取的值是1）
                    //空字符串可以有 1 种解码方法，解码出一个空字符串
                    if (i == 1) {
                        dp[i] = 1;
                    } else {
                        dp[i] = dp[i - 2];
                    }
                } else {
                    return 0;
                }
            } else {
                if (sc[i - 1] == '1' || (sc[i - 1] == '2' && sc[i] <= '6')) {
                    //此时s[i]!=0，已经有dp[i-1]种方法，需要把它加上
                    //第一个数要特殊处理，正常dp[1]=dp[0]+dp[-1]
                    if (i == 1) {
                        dp[i] = dp[0] + 1;
                    } else {
                        dp[i] = dp[i - 1] + dp[i - 2];
                    }
                } else {
                    dp[i] = dp[i - 1];
                }
            }
        }

        return dp[sc.length - 1];
    }


    public int numDecodings_20220118(String s) {
        /**
         * dp[i]为前i个数字可以解码成的消息总数
         *
         * 假如i为0
         *    假如i-1为0，则return 0
         *    假如i-1为1~2，则i这位只能和前一位一起计算，并且前一位不能单独作数，dp[i]=dp[i-2]
         *    假如i-1为3~9，则i这位不能单独组成，return 0
         * 假如i为1~6
         *    假如i-1为0，则i只能单独计算，dp[i]=dp[i-1]
         *    假如i-1为1~2，则i可以单独计算，也可以和前面组合计算，dp[i]=dp[i-1]+1
         *    假如i-1为3~9，则i只能单独计算，dp[i]=dp[i-1]
         * 假如i为7~9
         *    假如i-1为0，则i只能单独计算，dp[i]=dp[i-1]
         *    假如i-1为1，则i可以单独计算，也可以和前面组合计算，dp[i]=dp[i-1]
         *    假如i-1为2~9，则i只能单独计算，dp[i]=dp[i-1]
         */
        char[] schar = s.toCharArray();
        int[] dp = new int[schar.length];

        if (schar[0] == '0') {
            return 0;
        }
        dp[0] = 1;

        for (int i = 1; i < schar.length; i++) {
            if (schar[i] == '0') {
                if (schar[i - 1] == '0') {
                    return 0;
                }
                if (schar[i - 1] >= '1' && schar[i - 1] <= '2') {
                    if (i == 1) {
                        dp[i] = 1;
                    } else {
                        dp[i] = dp[i - 2];
                    }
                }
                if (schar[i - 1] >= '3' && schar[i - 1] <= '9') {
                    return 0;
                }
            }
            if (schar[i] >= '1' && schar[i] <= '6') {
                if (schar[i - 1] == '0') {
                    dp[i] = dp[i - 1];
                }
                if (schar[i - 1] >= '1' && schar[i - 1] <= '2') {
                    if (i == 1) {
                        dp[i] = dp[0] + 1;
                    } else {
                        dp[i] = dp[i - 1] + dp[i - 2];
                    }
                }
                if (schar[i - 1] >= '3' && schar[i - 1] <= '9') {
                    dp[i] = dp[i - 1];
                }
            }
            if (schar[i] >= '7' && schar[i] <= '9') {
                if (schar[i - 1] == '0') {
                    dp[i] = dp[i - 1];
                }
                if (schar[i - 1] >= '1') {
                    if (i == 1) {
                        dp[i] = dp[0] + 1;
                    } else {
                        dp[i] = dp[i - 1] + dp[i - 2];
                    }
                }
                if (schar[i - 1] >= '2' && schar[i - 1] <= '9') {
                    dp[i] = dp[i - 1];
                }
            }
        }

        return dp[schar.length - 1];
    }
}
