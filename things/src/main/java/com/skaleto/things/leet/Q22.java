package com.skaleto.things.leet;

import java.util.*;

public class Q22 {

    //递归解法
    public List<String> generateParenthesis(int n) {
        if (n == 1) {
            return Arrays.asList("()");
        }

        Set<String> res = new HashSet<>();
        List<String> last = generateParenthesis(n - 1);
        for (String s : last) {
            //插空法
            for (int i = 0; i < s.length(); i++) {
                res.add(s.substring(0, i) + "()" + s.substring(i));
            }
        }

        return new ArrayList<>(res);

    }


    public List<String> generateParenthesis_1(int n) {
        //回溯算法
        List<String> result = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        dfs(0, 0, n, sb, result);
        return result;
    }


    void dfs(int left, int right, int n, StringBuilder sb, List<String> result) {
        //任意一边超过数量立即返回
        if (left > n || right > n) {
            return;
        }
        //任何时刻，符合要求的括号中左括号一定不会比右括号数量少
        if (left < right) {
            return;
        }
        //满足要求的条件
        if (left == n && right == n) {
            result.add(sb.toString());
            return;
        }

        sb.append("(");
        dfs(left + 1, right, n, sb, result);
        sb.deleteCharAt(sb.length() - 1);

        sb.append(")");
        dfs(left, right + 1, n, sb, result);
        sb.deleteCharAt(sb.length() - 1);

    }


    public static void main(String[] args) {
        Q22 q = new Q22();
        List<String> result = new ArrayList<>();
        q.dfs(0, 0, 3, new StringBuilder(), result);
        System.out.println(result);
    }


}
