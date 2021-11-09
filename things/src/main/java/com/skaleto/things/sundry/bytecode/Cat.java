package com.skaleto.things.sundry.bytecode;

public class Cat extends Animal {
    @Override
    public boolean isExtinct() {
        return false;
    }

    @Override
    public Long livingTime() {
        return Long.MAX_VALUE;
    }

    @Override
    public String toString() {
        return "cat";
    }
}