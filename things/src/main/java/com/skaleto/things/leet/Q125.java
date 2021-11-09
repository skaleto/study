package com.skaleto.things.leet;

public class Q125 {

    public boolean isPalindrome(String s) {
        //1.去除字符串中的非字母和数字的字符
        String strNonCharOrNum = filterNonCharOrNum(s);
        //2.将字符串反转
        String reversedStr = reverseStr(strNonCharOrNum);
        //3.将反转后的字符串和原来的字符串作比较
        return strNonCharOrNum.equalsIgnoreCase(reversedStr);
    }

    private String reverseStr(String strNonCharOrNum) {
        return new StringBuilder(strNonCharOrNum).reverse().toString();
    }

    private String filterNonCharOrNum(String s) {
        return s.replaceAll("[^A-Za-z0-9]", "");
    }


}
