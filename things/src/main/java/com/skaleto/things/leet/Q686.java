package com.skaleto.things.leet;

public class Q686 {

    public static void main(String[] args) {
        Q686 q=new Q686();
        System.out.println(q.repeatedStringMatch("abcd","cdabcdab"));
    }

    public int repeatedStringMatch(String a, String b) {
        int i=1;
        String newStr=null;
        while(i<10){
            newStr=a+a;
            if(newStr.contains(b)){
                return i;
            }else{
                i++;
            }
        }

        return -1;
    }
}
