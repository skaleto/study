package com.skaleto.things.sundry.bytecode;

class Dinosaur extends Animal {
    @Override
    public boolean isExtinct() {
        return true;
    }

    @Override
    public String toString() {
        return "dinosaur";
    }
}