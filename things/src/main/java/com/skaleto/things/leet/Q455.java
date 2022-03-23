package com.skaleto.things.leet;

import java.util.Arrays;

public class Q455 {

    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);

        int result = 0;

        int i = 0;
        int j = 0;

        while (i < g.length) {
            while (j < s.length) {
                if (g[i] <= s[j]) {
                    result++;
                    j++;
                    break;
                } else {
                    j++;
                }
            }
            i++;
        }
        return result;

    }


    public int findContentChildren_20211229(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);

        int i = 0;
        int j = 0;

        int count = 0;
        while (i < g.length && j < s.length) {
            while (j < s.length && s[j] < g[i]) {
                j++;
            }

            if (j < s.length) {
                count++;
                i++;
                j++;
            }

        }
        return count;


    }

    public static void main(String[] args) {
        Q455 q = new Q455();
        System.out.println(q.findContentChildren_20211229(new int[]{1, 2, 3}, new int[]{1, 1}));
    }
}
