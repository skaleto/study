package com.skaleto.things.leet;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Q20 {

    Stack<Character> stack = new Stack<>();

    public boolean isValid(String s) {
        char[] chars = s.toCharArray();
        for (char c : chars) {
            if (isRight(c)) {
                if (stack.empty()) {
                    return false;
                }
                if (getPair(c) != stack.pop()) {
                    return false;
                }
            } else {
                stack.push(c);
            }
        }
        return stack.empty();
    }

    public boolean isleft(char c) {
        List<Object> o=new ArrayList<>();
        return '(' == c || '[' == c || '{' == c;
    }

    public boolean isRight(char c) {
        return ')' == c || ']' == c || '}' == c;
    }

    public char getPair(char c) {
        switch (c) {
            case ')':
                return '(';
            case ']':
                return '[';
            case '}':
                return '{';
            default:
                return 0;
        }
    }

}
