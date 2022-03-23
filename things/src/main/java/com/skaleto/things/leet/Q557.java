package com.skaleto.things.leet;

public class Q557 {
    /**
     * Your runtime beats 100 % of java submissions
     * Your memory usage beats 5.02 % of java submissions (42.1 MB)
     * @param s
     * @return
     */
    public String reverseWords(String s) {
        char[] chars = s.toCharArray();
        int i = 0;
        while (i < chars.length) {
            if (chars[i] == ' ') {
                i++;
                continue;
            }
            int j = i + 1;
            while (j < chars.length && chars[j] != ' ') {
                j++;
            }
            reverse(chars, i, j - 1);
            i = j;
        }
        return new String(chars);
    }

    public void reverse(char[] chars, int begin, int end) {
        int left = begin;
        int right = end;
        while (left < right) {
            char tmp = chars[left];
            chars[left] = chars[right];
            chars[right] = tmp;
            left++;
            right--;
        }
    }
}
