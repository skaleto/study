package com.skaleto.things.leet;

import java.util.HashMap;
import java.util.Map;

public class Q205 {

    public boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        int[] sFeature = getFeature(s);
        int[] tFeature = getFeature(t);
        for (int i = 0; i < sFeature.length; i++) {
            if (sFeature[i] != tFeature[i]) {
                return false;
            }
        }

        return true;
    }

    /**
     * 计算字符串的特征，例如abb得到[0,1,1]，title得到[0,1,0,3,4]
     * @param s
     * @return
     */
    public int[] getFeature(String s) {
        Map<Character, Integer> indexMap = new HashMap<>();
        int[] feature = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            if (indexMap.containsKey(s.charAt(i))) {
                feature[i] = indexMap.get(s.charAt(i));
            } else {
                indexMap.put(s.charAt(i), i);
                feature[i] = i;
            }
        }
        return feature;
    }
}
