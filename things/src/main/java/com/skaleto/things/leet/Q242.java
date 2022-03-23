package com.skaleto.things.leet;

import java.util.HashMap;
import java.util.Map;

public class Q242 {

    public boolean isAnagram_20211222(String s, String t) {
        Map<Character,Integer> charMap=new HashMap<>();

        char[] schar=s.toCharArray();
        char[] tchar=t.toCharArray();

        for(char i: schar){
            if(charMap.containsKey(i)){
                charMap.put(i, charMap.get(i)+1);
            }else{
                charMap.put(i, 1);
            }
        }

        for(char j:tchar){
            if(charMap.containsKey(j)){
                int num=charMap.get(j);
                if(num-1<=0){
                    charMap.remove(j);
                }else{
                    charMap.put(j, num-1);
                }
            }else{
                return false;
            }
        }

        return charMap.isEmpty();

    }
}
