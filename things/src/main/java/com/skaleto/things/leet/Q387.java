package com.skaleto.things.leet;

import java.util.HashMap;
import java.util.Map;

public class Q387 {

    public static void main(String[] args) {
        Q387 q = new Q387();
//        System.out.println(q.firstUniqChar("loveleetcode"));
        System.out.println(q.firstUniqChar("aabb"));
    }

    public int firstUniqChar(String s) {
        char[] chars = s.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        for (char c : chars) {
            if (map.containsKey(c)) {
                map.put(c, map.get(c) + 1);
            } else {
                map.put(c, 1);
            }
        }

        for (int i = 0; i < chars.length; i++) {
            if (map.get(chars[i]) == 1) {
                return i;
            }
        }

        return -1;
    }
}
