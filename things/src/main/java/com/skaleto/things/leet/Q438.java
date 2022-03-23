package com.skaleto.things.leet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Q438 {

    /**
     * Your runtime beats 5.02 % of java submissions
     * Your memory usage beats 5.01 % of java submissions (42.6 MB)
     * @param s
     * @param p
     * @return
     */
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();

        char[] pchar = p.toCharArray();
        Arrays.sort(pchar);
        String dest = new String(pchar);

        char[] chars = s.toCharArray();
        int i = 0;
        while (i <= s.length() - p.length()) {
            if (isAna(s.substring(i, i + p.length()), dest)) {
                result.add(i);
            }
            i++;
        }
        return result;
    }

    public boolean isAna(String source, String dest) {
        char[] chars = source.toCharArray();
        Arrays.sort(chars);
        String s = new String(chars);
        return s.equals(dest);
    }
}
