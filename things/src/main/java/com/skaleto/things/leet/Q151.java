package com.skaleto.things.leet;

import java.util.ArrayList;
import java.util.List;

public class Q151 {

    public static void main(String[] args) {
        Q151 q = new Q151();
        System.out.println(q.reverseWords("the sky is blue"));
    }

    public String reverseWords(String s) {
        List<String> list = new ArrayList<>();

        int i = 0;
        while (i < s.length()) {
            if (s.charAt(i) == ' ') {
                i++;
                continue;
            }
            int j = i + 1;
            while (j < s.length() && s.charAt(j) != ' ') {
                j++;
            }
            list.add(s.substring(i, j));
            i = j;
        }

        StringBuilder result = new StringBuilder();
        for (int k = list.size() - 1; k >= 0; k--) {
            result.append(list.get(k));
            result.append(" ");
        }

        return result.toString().trim();
    }
}
