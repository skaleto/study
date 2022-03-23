package com.skaleto.things.leet;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串的长度。
 *
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 *
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 */
public class Q3 {

    public static void main(String[] args) {
        Q3 q=new Q3();
        System.out.println(q.lengthOfLongestSubstring("abba"));
    }

    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int maxLen = 0;
        int left = 0;

        char[] c = s.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (map.containsKey(c[i])) {
//                left = Math.max(left, map.get(c[i]) + 1);
                left= map.get(c[i]) + 1;
            }
            map.put(c[i], i);
            maxLen = Math.max(maxLen, i - left + 1);
        }

        return maxLen;
    }


}
