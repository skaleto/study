package com.skaleto.things.leet;

import java.util.HashMap;
import java.util.Map;

public class Q12 {

    /**
     * Your runtime beats 36.12 % of java submissions
     * Your memory usage beats 35.5 % of java submissions (38.1 MB)
     *
     * @param num
     * @return
     */
    public String intToRoman(int num) {
        int[] tab = new int[]{1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};

        Map<Integer, String> tabMap = new HashMap<>();
        tabMap.put(0, "M");
        tabMap.put(1, "CM");
        tabMap.put(2, "D");
        tabMap.put(3, "CD");
        tabMap.put(4, "C");
        tabMap.put(5, "XC");
        tabMap.put(6, "L");
        tabMap.put(7, "XL");
        tabMap.put(8, "X");
        tabMap.put(9, "IX");
        tabMap.put(10, "V");
        tabMap.put(11, "IV");
        tabMap.put(12, "I");

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < tab.length; i++) {
            if (num >= tab[i]) {
                int count = num / tab[i];
                num %= tab[i];
                for (int j = 0; j < count; j++) {
                    result.append(tabMap.get(i));
                }
            }

            if (num == 0) {
                break;
            }
        }

        return result.toString();

    }
}
