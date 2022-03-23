package com.skaleto.things.leet;

public class Q125 {

//    public boolean isPalindrome(String s) {
//        //1.去除字符串中的非字母和数字的字符
//        String strNonCharOrNum = filterNonCharOrNum(s);
//        //2.将字符串反转
//        String reversedStr = reverseStr(strNonCharOrNum);
//        //3.将反转后的字符串和原来的字符串作比较
//        return strNonCharOrNum.equalsIgnoreCase(reversedStr);
//    }
//
//    private String reverseStr(String strNonCharOrNum) {
//        return new StringBuilder(strNonCharOrNum).reverse().toString();
//    }
//
//    private String filterNonCharOrNum(String s) {
//        return s.replaceAll("[^A-Za-z0-9]", "");
//    }

    public static void main(String[] args) {
        Q125 q = new Q125();
//        System.out.println(q.isPalindrome("A man, a plan, a canal: Panama"));
        System.out.println(q.isPalindrome("0P"));
    }

    /**
     * Your runtime beats 96.28 % of java submissions
     * Your memory usage beats 5 % of java submissions (41.5 MB)
     * @param s
     * @return
     */
    public boolean isPalindrome(String s) {
        int i = 0;
        int j = s.length() - 1;
        char[] chars = s.toCharArray();
        while (i < j) {
            while (i < s.length() && !isValid(chars[i])) {
                i++;
            }
            while (j >= 0 && !isValid(chars[j])) {
                j--;
            }
            if (i >= j) {
                break;
            }
            if (chars[i] != chars[j] && !isCaseSame(chars[i], chars[j])) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    public boolean isCaseSame(char a, char b){
        return a==b || (isChar(a) && isChar(b) && Math.abs(a - b) == 32);
    }

    public boolean isValid(char c) {
        return (c >= 65 && c <= 90) || (c >= 97 && c <= 122) || (c >= 48 && c <= 57);
    }

    public boolean isChar(char c) {
        return (c >= 65 && c <= 90) || (c >= 97 && c <= 122);
    }

}
