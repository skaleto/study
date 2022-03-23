package com.skaleto.things.leet;

public class Q680 {

    /**
     * Your runtime beats 5.05 % of java submissions
     * Your memory usage beats 7.36 % of java submissions (41.7 MB)
     * @param s
     * @return
     */
    public boolean validPalindrome(String s) {
        char[] chars = s.toCharArray();

        int i = 0;
        int j = s.length() - 1;

        int count = 1;
        boolean result = true;
        while (i < j) {
            if (chars[i] != chars[j] && count == 1) {
                //删掉当前的i
                i++;
                count--;
            }
            if (chars[i] != chars[j] && count == 0) {
                result = false;
            }
            i++;
            j--;
        }

        if (result) {
            return true;
        }

        i = 0;
        j = s.length() - 1;

        count = 1;
        while (i < j) {
            if (chars[i] != chars[j] && count == 1) {
                //删掉当前的j
                j--;
                count--;
            }
            if (chars[i] != chars[j] && count == 0) {
                return false;
            }
            i++;
            j--;
        }


        return true;
    }
}
