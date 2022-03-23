package com.skaleto.things.leet;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;

public class Q127 {

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        HashSet<String> bankSet = new HashSet<>(wordList);
        if (!bankSet.contains(endWord)) {
            return 0;
        }

        Deque<String> queue=new ArrayDeque<>();
        queue.addLast(beginWord);

        char[] generic=new char[]{'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};

        int level=1;
        while(!queue.isEmpty()){
            level++;
            int levelSize=queue.size();
            for(int i=0;i<levelSize;i++){
                char[] first=queue.pollFirst().toCharArray();
                for(int j=0;j<first.length;j++){
                    char tmp=first[j];
                    for(int k=0;k<generic.length;k++){
                        first[j]=generic[k];
                        //这里必须new一个String，不能用char[]的toString
                        String newStr=new String(first);
                        if(newStr.equals(endWord)){
                            return level;
                        }

                        if(bankSet.contains(newStr)){
                            bankSet.remove(newStr);
                            queue.addLast(newStr);
                        }
                    }
                    first[j]=tmp;
                }
            }
        }

        return 0;
    }

}
