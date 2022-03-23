package com.skaleto.things.leet;

import java.util.HashSet;
import java.util.Set;

public class Q771 {

    /**
     * Your runtime beats 75.63 % of java submissions
     * Your memory usage beats 5.06 % of java submissions (40 MB)
     * @param jewels
     * @param stones
     * @return
     */
    public int numJewelsInStones(String jewels, String stones) {
        Set<Character> jewelSet = new HashSet();
        for (char c : jewels.toCharArray()) {
            jewelSet.add(c);
        }

        char[] stonechar = stones.toCharArray();

        int result = 0;
        for (char c : stones.toCharArray()) {
            if (jewelSet.contains(c)) {
                result++;
            }
        }
        return result;
    }
}
