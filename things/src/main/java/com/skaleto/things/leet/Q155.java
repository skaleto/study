package com.skaleto.things.leet;

import java.util.Stack;

public class Q155 {

    Stack<Integer> valStack=new Stack<>();
    Stack<Integer> minStack=new Stack<>();

    public void push(int val) {
        valStack.push(val);
        if(minStack.isEmpty()){
            minStack.push(val);
        }else{
            minStack.push(Math.min(val, minStack.peek()));
        }
    }

    public void pop() {
        valStack.pop();
        minStack.pop();
    }

    public int top() {
        return valStack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}
