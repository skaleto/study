package com.skaleto.things.leet;

public class Q14 {

    /**
     * Your runtime beats 100 % of java submissions
     * Your memory usage beats 5.03 % of java submissions (39.6 MB)
     * @param strs
     * @return
     */
    public String longestCommonPrefix(String[] strs) {
        StringBuilder result = new StringBuilder();

        int minLen = Integer.MAX_VALUE;
        for (String s : strs) {
            minLen = Math.min(minLen, s.length());
        }

        int i = 0;
        while (i < minLen) {
            char c = strs[0].charAt(i);
            for (String s : strs) {
                if (s.charAt(i) != c) {
                    return result.toString();
                }
            }
            result.append(c);
            i++;
        }

        return result.toString();
    }
}
