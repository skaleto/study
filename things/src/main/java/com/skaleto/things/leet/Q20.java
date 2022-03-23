package com.skaleto.things.leet;

import java.util.*;

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


    public boolean isValid_20211119(String s) {
        Stack<Character> stack=new Stack<>();
        char[] chars=s.toCharArray();
        for(int i=0;i<chars.length;i++){
            if(chars[i]=='(' || chars[i]=='{' || chars[i]=='['){
                stack.add(chars[i]);
            }
            if(chars[i]==')'){
                if(stack.isEmpty()){
                    return false;
                }
                char c=stack.pop();
                if(c!='('){
                    return false;
                }
            }
            if(chars[i]=='}'){
                if(stack.isEmpty()){
                    return false;
                }
                char c=stack.pop();
                if(c!='{'){
                    return false;
                }
            }
            if(chars[i]==']'){
                if(stack.isEmpty()){
                    return false;
                }
                char c=stack.pop();
                if(c!='['){
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }

    public boolean isValid_20211219(String s) {
        Deque<Character> stack=new ArrayDeque<>();

        char[] schar=s.toCharArray();
        //遇到左括号入栈，遇到右括号出栈并判断栈顶是否与该右括号匹配
        for(char c: schar){
            if(c=='(' || c=='[' || c=='{'){
                stack.addFirst(c);
            }else{
                if(stack.isEmpty()){
                    return false;
                }
                char tmp=stack.pollFirst();
                if(c==')' && tmp!='('){
                    return false;
                }

                if(c==']' && tmp!='['){
                    return false;
                }

                if(c=='}' && tmp!='{'){
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }

}
