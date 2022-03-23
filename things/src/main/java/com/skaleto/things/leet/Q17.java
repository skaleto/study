package com.skaleto.things.leet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Q17 {

    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if (digits == null || digits.equals("")) {
            return result;
        }

        //0~7的位置代表2~9数字
        char[][] digitMap = new char[][]{
                {'a', 'b', 'c'},
                {'d', 'e', 'f'},
                {'g', 'h', 'i'},
                {'j', 'k', 'l'},
                {'m', 'n', 'o'},
                {'p', 'q', 'r', 's'},
                {'t', 'u', 'v'},
                {'w', 'x', 'y', 'z'}
        };

        char[] digiChar = digits.toCharArray();
        int[] digiInt = new int[digiChar.length];
        for (int i = 0; i < digiChar.length; i++) {
            digiInt[i] = digiChar[i] - '0';
        }

        String path = "";

        dfs(result, digitMap, digiInt, 0, path);

        return result;
    }

    public void dfs(List<String> result, char[][] digitMap, int[] digiInt, int begin, String path) {
        if (begin == digiInt.length) {
            result.add(path);
            return;
        }

        char[] chars = digitMap[digiInt[begin] - 2];

        for (int i = 0; i < chars.length; i++) {
            path += chars[i];
            dfs(result, digitMap, digiInt, begin + 1, path);
            path = path.substring(0, path.length() - 1);
        }
    }

    public List<String> letterCombinations_20211227(String digits) {
        List<String> result = new ArrayList<>();

        if (digits == null || digits.equals("")) {
            return result;
        }

        HashMap<Character, char[]> map = new HashMap<>();
        map.put('2', new char[]{'a', 'b', 'c'});
        map.put('3', new char[]{'d', 'e', 'f'});
        map.put('4', new char[]{'g', 'h', 'i'});
        map.put('5', new char[]{'j', 'k', 'l'});
        map.put('6', new char[]{'m', 'n', 'o'});
        map.put('7', new char[]{'p', 'q', 'r', 's'});
        map.put('8', new char[]{'t', 'u', 'v'});
        map.put('9', new char[]{'w', 'x', 'y', 'z'});


        StringBuilder sb = new StringBuilder();
        char[] digitChars = digits.toCharArray();

        dfs(digitChars, 0, map, sb, result);

        return result;
    }

    public void dfs(char[] digitChars, int cur, HashMap<Character, char[]> map, StringBuilder sb, List<String> result) {
        if (sb.length() == digitChars.length) {
            result.add(sb.toString());
            return;
        }

        char[] chars = map.get(digitChars[cur]);
        for (int i = 0; i < chars.length; i++) {
            sb.append(chars[i]);
            dfs(digitChars, cur + 1, map, sb, result);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    public static void main(String[] args) {
        Q17 q17 = new Q17();
        q17.letterCombinations("23");
    }
}
