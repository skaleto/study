package com.skaleto.things.java100;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class TestSublist {

    private List<String> list = new ArrayList<>();

    public static void main(String[] args) {
        TestSublist testSub = new TestSublist();
        IntStream.rangeClosed(1, 1_000_000).forEach(i -> {
            testSub.list.add(String.valueOf(i));
            testSub.list = testSub.list.subList(testSub.list.size()-1, testSub.list.size());
        });

        System.out.println();
    }

}
