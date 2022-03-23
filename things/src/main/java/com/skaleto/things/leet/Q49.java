package com.skaleto.things.leet;

import java.util.*;

public class Q49 {

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String,List<String>> tagMap=new HashMap<>();

        for(String i: strs){
            char[] chars=i.toCharArray();
            Arrays.sort(chars);
            String tag=new String(chars);

            if(!tagMap.containsKey(tag)){
                List<String> newList=new ArrayList<>();
                newList.add(i);
                tagMap.put(tag, newList);
            }else{
                tagMap.get(tag).add(i);
            }
        }

        return new ArrayList<>(tagMap.values());

    }
}
