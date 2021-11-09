package com.skaleto.things.leet;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Stack;

public class Q394 {

    Stack<String> stack = new Stack<>();

    public String decodeString(String s) {
        char[] chars = s.toCharArray();
        int i = 0;
        while (i < chars.length) {
            if (Character.isDigit(chars[i])) {
                StringBuilder sb = new StringBuilder();
                sb.append(chars[i]);
                while (Character.isDigit(chars[++i])) {
                    sb.append(chars[i]);
                }
                stack.push(sb.toString());
            } else if (isLeftBracket(chars[i]) || Character.isLetter(chars[i])) {
                stack.push(String.valueOf(chars[i]));
                i++;
            } else {
                //这种情况是遇到了右括号，此时开始出栈，直到遇到左括号
                LinkedList<String> sub = new LinkedList<String>();
                while (!"[".equals(stack.peek())) {
                    sub.addLast(stack.pop());
                }
                Collections.reverse(sub);

                StringBuilder str = new StringBuilder();
                for (String s1 : sub) {
                    str.append(s1);
                }

                //此时pop出一个左括号
                stack.pop();
                //此时pop出一个数字
                int times = Integer.parseInt(stack.pop());
                StringBuilder str2 = new StringBuilder();
                for (int j = 0; j < times; j++) {
                    str2.append(str);
                }
                stack.push(str2.toString());

                i++;
            }
        }

        StringBuilder res=new StringBuilder();
        for(String a:stack){
            res.append(a);
        }
        return res.toString();
    }

    public boolean isLeftBracket(char c) {
        return c == '[';
    }

    public static void main(String[] args) {
        Q394 q=new Q394();
        System.out.println(q.decodeString("3[a]2[bc]"));
    }

}
