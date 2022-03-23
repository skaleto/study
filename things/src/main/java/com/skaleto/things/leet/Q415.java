package com.skaleto.things.leet;

import java.util.ArrayList;
import java.util.List;

public class Q415 {

    public static void main(String[] args) {
        Q415 q = new Q415();
        System.out.println(q.addStrings("11", "123"));
    }

    public String addStrings(String num1, String num2) {
        List<Integer> result = new ArrayList<>();
        int i = num1.length() - 1;
        int j = num2.length() - 1;

        int adder = 0;
        while (i >= 0 && j >= 0) {
            int temp = adder + (num1.charAt(i) - '0') + (num2.charAt(j) - '0');

            if (adder > 0) {
                adder--;
            }

            if (temp >= 10) {
                result.add(temp - 10);
                adder++;
            } else {
                result.add(temp);
            }
            i--;
            j--;
        }

        while (i >= 0) {
            int temp = adder + (num1.charAt(i) - '0');
            if (adder > 0) {
                adder--;
            }
            if (temp >= 10) {
                result.add(temp - 10);
                adder++;
            } else {
                result.add(temp);
            }
            i--;
        }

        while (j >= 0) {
            int temp = adder + (num2.charAt(j) - '0');
            if (adder > 0) {
                adder--;
            }
            if (temp >= 10) {
                result.add(temp - 10);
                adder++;
            } else {
                result.add(temp);
            }
            j--;
        }

        if (adder > 0) {
            result.add(1);
        }

        StringBuilder ans = new StringBuilder();
        for (int k = result.size() - 1; k >= 0; k--) {
            ans.append(result.get(k));
        }

        return ans.toString();

    }
}
