package com.skaleto.things.leet;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Q_Interview_HUAWEI {

    private static String result="";
    private static int maxLength=0;

    /**
     * 输入一个字符串，输出其中最长的有效数字对应的字符串
     * @param s
     * @return
     */
    public static String longestDigitStr(String s){

        //先考虑双指针
        int left=0;
        int right=left;

        //前置序列是否包含小数点
        boolean containsDot=false;
        //前置序列是否包含符号
        boolean containsSymbol=false;
        while(right<s.length()){
            char c=s.charAt(right);
            if(isDigit(c)){
                //只要是数字，一定合法
                checkSequence(s,left,right);
                right++;
            }else if(c == '.'){
                //是小数点需要检查前置序列是否包含小数点、是否前后都是数字
                if(containsDot){
                    //前置序列包含小数点，那么双指针从后方开始计算
                    left=right+1;
                    right=left;
                    containsDot=false;
                    containsSymbol=false;
                    continue;
                }

                char pre=right>left ? s.charAt(right-1): 'N';
                char next=right<s.length() ? s.charAt(right+1): 'N';
                if(!isDigit(pre) || !isDigit(next)){
                    //前后有一个不是数字，那么双指针从后方开始计算
                    left=right+1;
                    right=left;
                    containsDot=false;
                    containsSymbol=false;
                    continue;
                }

                right++;
                checkSequence(s,left,right);
                containsDot=true;

            }else if(c =='+' || c == '-'){
                //是符号需要检查前置序列是否包含符号、是否出现在开头且后方是数字
                if(containsSymbol){
                    //前置序列包含符号，那么双指针从后方开始计算
                    left=right+1;
                    right=left;
                    containsDot=false;
                    containsSymbol=false;
                    continue;
                }

                char pre=right>left ? s.charAt(right-1): 'N';
                char next=right<s.length() ? s.charAt(right+1): 'N';
                if(pre=='N' && isDigit(next)){
                    checkSequence(s,left,right);
                    containsSymbol=true;
                    right++;
                }else{
                    left=right+1;
                    right=left;
                    containsDot=false;
                    containsSymbol=false;
                    continue;
                }

            }else{
                left=right+1;
                right=left;
                containsDot=false;
                containsSymbol=false;
                continue;
            }
        }

        return result;
    }

    public static boolean isDigit(char c){
        return c>='0' && c<='9';
    }

    public static void checkSequence(String s, int left, int right){
        int length=right-left+1;
        if(length>=maxLength){
            maxLength=length;
            result=s.substring(left,right+1);
        }
    }


    /**
     * 输入若干个人下单时间的列表，
     * 如果在某一秒内，某个人的下单时间最早，那么这个人可以免单，
     * 并且如果该时刻有多个人同时下单，那么这些人都可以免单；
     *
     * 输出有多少人可以免单
     *
     * 示例：
     * 输入：
     * 2019-01-01 08:59:00.123
     * 2019-01-01 08:59:00.123
     * 2019-01-01 08:59:00.234
     * 2018-12-28 10:08:00.999
     * 输出：
     * 3
     *
     * @param times
     * @return
     */
    public static int getFreedCount(List<String> times) {
        //一个map存秒级时间和对应的最早的毫秒级时间
        Map<String, String> timeMap = new HashMap<>();
        //一个map存毫秒级时间和对应相同时间的数量
        Map<String, Integer> count = new HashMap<>();

        for (String time : times) {
            if (count.containsKey(time)) {
                count.put(time, count.get(time) + 1);
            } else {
                count.put(time, 1);
            }

            String second = time.substring(0, time.lastIndexOf("."));
            int nano = Integer.parseInt(time.substring(time.lastIndexOf(".") + 1));
            if (timeMap.containsKey(second)) {
                String t = timeMap.get(second);
                int n = Integer.parseInt(t.substring(t.lastIndexOf(".") + 1));
                if (nano < n) {
                    timeMap.put(second, time);
                }
            } else {
                timeMap.put(second, time);
            }
        }

        AtomicInteger result = new AtomicInteger();
        timeMap.forEach((k, v) -> {
            result.addAndGet(count.get(v));
        });
        return result.get();
    }
}
