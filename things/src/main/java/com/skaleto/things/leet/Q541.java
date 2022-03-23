package com.skaleto.things.leet;

public class Q541 {

    /**
     * Your runtime beats 100 % of java submissions
     * Your memory usage beats 7.41 % of java submissions (41.2 MB)
     * @param s
     * @param k
     * @return
     */
    public String reverseStr(String s, int k) {
        char[] chars = s.toCharArray();
        int i = 0;
        while (i < chars.length) {
            if (chars.length - i < k) {
                //剩余字符数量少于k个
                reverse(chars, i, chars.length - 1);
            } else if (chars.length - i < 2 * k && chars.length - i >= k) {
                //剩余字符数量小于2k 但大于或等于k 个
                reverse(chars, i, i + k - 1);
            } else {
                //剩余字符数量大于等于2k
                reverse(chars, i, i + k - 1);
            }
            //向后走2k个
            i += 2 * k;
        }

        return new String(chars);
    }


    public void reverse(char[] chars, int start, int end) {
        int left = start;
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
