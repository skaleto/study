package com.skaleto.things.leet;

public class Q58 {

    /**
     * Your runtime beats 100 % of java submissions
     * Your memory usage beats 5.02 % of java submissions (39.9 MB)
     * @param s
     * @return
     */
    public int lengthOfLastWord(String s) {
        char[] chars=s.toCharArray();
        int i=chars.length-1;
        while(i>=0 && chars[i]==' '){
            i--;
        }

        int count=0;
        while(i>=0 && chars[i]!=' '){
            count++;
            i--;
        }

        return count;

    }
}
