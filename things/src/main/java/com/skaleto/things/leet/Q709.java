package com.skaleto.things.leet;

public class Q709 {

    /**
     * Your runtime beats 100 % of java submissions
     * Your memory usage beats 5.03 % of java submissions (39.5 MB)
     * @param s
     * @return
     */
    public String toLowerCase(String s) {
        StringBuilder result = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (c <= 90 && c >= 65) {
                result.append((char) (c + 32));
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }
}
