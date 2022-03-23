package com.skaleto.things.leet;


import java.util.ArrayDeque;
import java.util.Deque;

public class Q32 {
    public static void main(String[] args) {
        Q32 q = new Q32();
        System.out.println(q.longestValidParentheses_20220116(")()())"));
    }

    public int longestValidParentheses(String s) {

        /**
         * 从后往前看，令dp[i]为某一位字符位置的最长有效括号子串的长度，
         * 如果s[i]=='('，那么不可能组成有效括号
         * 如果s[i]==')'，那么有几种情况：
         * 1. 如果前一位是'('，那么可以和他组成括号，dp[i]=dp[i-1]+2
         * 2. 如果前一位是')'，那么看当前位的对位的信息是否有左括号。
         *    怎么看当前位的对位呢？
         *    首先，dp[i-1]是i-1这个位置的最长有效子串的长度，那么i-dp[i-1]-1就是从i这个位置跳过dp[i-1]个长度向前数的位置索引
         *    所以i位置的对位就是i-dp[i-1]-1，如果s[i-dp[i-1]-1]=='('，那么可以和i位置组成一个括号对
         *    同时，还要在i-dp[i-1]-1往前再看，加上dp[i-dp[i-1]-2]的长度
         *    因此，dp[i]=dp[i-1]+dp[i-dp[i-1]-2]+2
         */
        if (s == null || "".equals(s)) {
            return 0;
        }

        char[] sc = s.toCharArray();
        int[] dp = new int[sc.length];
        dp[0] = 0;

        int max = 0;
        for (int i = 1; i < sc.length; i++) {
            //只考虑当前位是')'的情况
            if (sc[i] == ')') {
                //如果前一位是'('，那么可以和他组成括号，dp[i]=dp[i-2]+2
                if (sc[i - 1] == '(') {
                    dp[i] = i - 2 >= 0 ? dp[i - 2] + 2 : 2;
                } else {
                    //检查当前位的对位索引是否存在
                    if (i - dp[i - 1] - 1 < 0) {
                        continue;
                    }

                    //如果前一位是')'，那么看当前位的对位的信息是否有左括号
                    if (sc[i - dp[i - 1] - 1] == '(') {
                        //在当前位的对位再往前找一位看是否存在
                        int tmp = 0;
                        if (i - dp[i - 1] - 2 >= 0) {
                            tmp = dp[i - dp[i - 1] - 2];
                        }
                        //当前位的最大长度等于前一位最大长度+2，再加上当前位对位之前的长度
                        dp[i] = dp[i - 1] + 2 + tmp;
                    }
                }
            }
            max = Math.max(max, dp[i]);
        }

        return max;
    }


    public int longestValidParentheses_20220116(String s) {
        char[] schar = s.toCharArray();
        /**
         * dp[i]表示i位置的最长有效子串长度(此处定义的是必须是包含i位置的子串)
         * 假如schar[i]=='('
         *   则dp[i]=0
         * 假如schar[i]==')'，看前一位的值schar[i-1]
         *   假如schar[i-1]='('，与i组成一个括号
         *      此时dp[i]=dp[i-2]+2
         *   假如schar[i-1]=')'，则看dp[i-1]的值，将i向前推dp[i-1]+1个长度，检查是否是左括号
         *      假如schar[i-dp[i-1]-1]=='('，则与i的右括号形成匹配【形如(()())】
         *          此时dp[i]=dp[i-dp[i-1]-2]+dp[i-1]+2
         *      假如schar[i-dp[i-1]-1]==')'
         *          此时dp[i]=0
         *
         */

        if (s == null || s.length() == 0) {
            return 0;
        }

        int[] dp = new int[schar.length];

        //schar[0]
        dp[0] = 0;

        int max = 0;

        for (int i = 1; i < schar.length; i++) {
            if (schar[i] == '(') {
                dp[i] = 0;
            } else {
                if (schar[i - 1] == '(') {
                    if (i - 2 >= 0) {
                        dp[i] = dp[i - 2] + 2;
                    } else {
                        dp[i] = 2;
                    }
                } else {
                    if (i - dp[i - 1] - 1 >= 0) {
                        if (schar[i - dp[i - 1] - 1] == '(') {
                            if (i - dp[i - 1] - 2 >= 0) {
                                dp[i] = dp[i - dp[i - 1] - 2] + dp[i - 1] + 2;
                            } else {
                                dp[i] = dp[i - 1] + 2;
                            }
                        } else {
                            dp[i] = 0;
                        }
                    } else {
                        dp[i] = 0;
                    }
                }
            }

            max = Math.max(max, dp[i]);

        }

        return max;
    }


    public int longestValidParentheses_20220116_stack(String s) {
        Deque<Integer> stack = new ArrayDeque<>();
        stack.addLast(-1);

        char[] schar = s.toCharArray();

        int max = 0;
        for (int i = 0; i < schar.length; i++) {
            if (schar[i] == '(') {
                stack.addLast(i);
            } else {
                stack.pollLast();
                if (stack.isEmpty()) {
                    stack.addLast(i);
                } else {
                    max = Math.max(max, i - stack.getLast());
                }
            }
        }

        return max;
    }

    public int longestValidParentheses_20220209(String s) {
        /**
         * dp[i]表示以s[i]结尾的最长有效括号子串长度（且子串必须包含s[i]）
         * 假如s[i]=='('，那么dp[i]=0
         * 假如s[i]==')'
         *      假如s[i-1]=='('，那么dp[i]=dp[i-2]+2
         *      假如s[i-1]==')'，从位置i向前走dp[i-1]+1步到dp[i-dp[i-1]-1]
         *          假如s[i-dp[i-1]-1]=='('，那么dp[i]=dp[i-dp[i-1]-2]+dp[i-1]+2
         *          假如s[i-dp[i-1]-1]==')'，那么dp[i]=0
         *
         */

        if (s.length() <= 1) {
            return 0;
        }

        char[] chars = s.toCharArray();
        int[] dp = new int[s.length()];
        dp[0] = 0;
        int max = 0;
        for (int i = 1; i < s.length(); i++) {
            if (chars[i] == '(') {
                dp[i] = 0;
            } else {
                if (chars[i - 1] == '(') {
                    if (i < 2) {
                        dp[i] = 2;
                    } else {
                        dp[i] = dp[i - 2] + 2;
                    }
                } else if (i - dp[i - 1] - 1 >= 0) {
                    if (chars[i - dp[i - 1] - 1] == '(') {
                        if (i - dp[i - 1] - 2 >= 0) {
                            dp[i] = dp[i - dp[i - 1] - 2] + dp[i - 1] + 2;
                        } else {
                            dp[i] = dp[i - 1] + 2;
                        }
                    } else {
                        dp[i] = 0;
                    }
                }
            }

            max = Math.max(max, dp[i]);
        }

        return max;
    }
}
