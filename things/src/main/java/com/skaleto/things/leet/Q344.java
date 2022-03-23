package com.skaleto.things.leet;

public class Q344 {

    /**
     * Your runtime beats 100 % of java submissions
     * Your memory usage beats 7.12 % of java submissions (47.9 MB)
     * @param s
     */
    public void reverseString(char[] s) {
        int left = 0;
        int right = s.length - 1;
        while (left < right) {
            char tmp = s[left];
            s[left] = s[right];
            s[right] = tmp;

            left++;
            right--;
        }
    }
}
