package com.skaleto.things.leet;

public class Q917 {

    /**
     * Your runtime beats 100 % of java submissions
     * Your memory usage beats 5.24 % of java submissions (39.5 MB)
     * @param s
     * @return
     */
    public String reverseOnlyLetters(String s) {
        char[] chars = s.toCharArray();
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            while (left < s.length() && !isChar(chars[left])) {
                left++;
            }
            while (right >= 0 && !isChar(chars[right])) {
                right--;
            }

            if (left < s.length() && right >= 0 && left < right) {
                char tmp = chars[left];
                chars[left] = chars[right];
                chars[right] = tmp;
                left++;
                right--;
            }
        }

        return new String(chars);

    }

    public boolean isChar(char c) {
        return (c >= 65 && c <= 90) || (c >= 97 && c <= 122);
    }
}
