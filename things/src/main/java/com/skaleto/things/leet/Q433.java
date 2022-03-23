package com.skaleto.things.leet;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;

public class Q433 {

    public int minMutation(String start, String end, String[] bank) {
        HashSet<String> bankSet = new HashSet<>(Arrays.asList(bank));
        if (!bankSet.contains(end)) {
            return -1;
        }

        Deque<String> queue = new ArrayDeque<>();
        queue.addLast(start);

        char[] generic = new char[]{'A', 'C', 'G', 'T'};

        int level = 0;
        while (!queue.isEmpty()) {
            level++;
            int levelSize = queue.size();
            for (int i = 0; i < levelSize; i++) {
                char[] first = queue.pollFirst().toCharArray();
                //遍历整个串，每个字符位置进行四次变化放入队列
                for (int j = 0; j < first.length; j++) {
                    char tmp = first[j];
                    for (int k = 0; k < generic.length; k++) {
                        first[j] = generic[k];
                        //这里必须new一个String，不能用char[]的toString
                        String newStr = new String(first);
                        if (newStr.equals(end)) {
                            return level;
                        }

                        if (bankSet.contains(newStr)) {
                            bankSet.remove(newStr);
                            queue.addLast(newStr);
                        }
                    }
                    first[j] = tmp;
                }
            }
        }

        return -1;
    }


    public int minMutation_20211227(String start, String end, String[] bank) {
        //bank列表转换为set，方便判断某个变化后的基因是否符合要求
        HashSet<String> bankSet = new HashSet<>(Arrays.asList(bank));

        char[] gene = new char[]{'A', 'C', 'G', 'T'};

        /**
         * 考虑使用BFS，每一个位置对应会有3种变化，构造一颗3叉树
         */
        Deque<String> queue = new ArrayDeque<>();
        queue.add(start);

        int step = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String s = queue.pollFirst();
                if (end.equals(s)) {
                    return step;
                }
                assert s != null;
                char[] schar = s.toCharArray();
                for (int j = 0; j < schar.length; j++) {
                    for (char c : gene) {
                        char t = schar[j];
                        if (schar[j] != c) {
                            schar[j] = c;
                            //判断生成的新串是否合法，合法的才入队
                            String tmp = new String(schar);
                            if (bankSet.contains(tmp)) {
                                queue.addLast(tmp);

                                //当某个基因序列符合要求时，把这个基因序列从银行中删除
                                //如果这里不删除，会出现TLE
                                //为什么要删除？
                                //假设变化前序列为A，符合要求的序列为B，当A第二次变到B时，说明之前已经有人变化过了，那么A此次的变化没有意义(即可能出现了环)
                                bankSet.remove(tmp);
                            }

                        }
                        //注意这里一定要变回来，否则会错乱
                        schar[j] = t;
                    }
                }
            }
            //一层遍历完
            step++;
        }

        return -1;

    }

    public static void main(String[] args) {
        Q433 q = new Q433();
        System.out.println(q.minMutation_20211227("AACCGGTT", "AACCGGTA", new String[]{"AACCGGTA"}));
    }
}
