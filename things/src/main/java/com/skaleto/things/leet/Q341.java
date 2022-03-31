package com.skaleto.things.leet;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Your runtime beats 99.95 % of java submissions
 * Your memory usage beats 29.73 % of java submissions (43.6 MB)
 */
public class Q341 {

    // This is the interface that allows for creating nested lists.
    // You should not implement it, or speculate about its implementation
    public interface NestedInteger {

        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        public boolean isInteger();

        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        public Integer getInteger();

        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return empty list if this NestedInteger holds a single integer
        public List<NestedInteger> getList();
    }

    public static class NestedIterator implements Iterator<Integer> {

        Iterator<Integer> iterator = null;

        public NestedIterator(List<NestedInteger> nestedList) {
            List<Integer> data = new ArrayList<>();
            traverse(nestedList, data);
            iterator = data.iterator();
        }

        public void traverse(List<NestedInteger> list, List<Integer> data) {
            for (NestedInteger i : list) {
                if (i.isInteger()) {
                    data.add(i.getInteger());
                } else {
                    traverse(i.getList(), data);
                }
            }
        }

        @Override
        public Integer next() {
            return iterator.next();
        }

        @Override
        public boolean hasNext() {
            return iterator.hasNext();
        }
    }


    /**
     * 迭代器惰性求值，不用一开始就把所有元素就存起来，减少内存占用
     */
    public static class NestedIterator2 implements Iterator<Integer> {

        LinkedList<NestedInteger> data = null;

        public NestedIterator2(List<NestedInteger> nestedList) {
            data = new LinkedList<>(nestedList);
        }

        @Override
        public Integer next() {
            return data.remove(0).getInteger();
        }

        @Override
        public boolean hasNext() {
            while (!data.isEmpty() && !data.get(0).isInteger()) {
                List<NestedInteger> list = data.remove(0).getList();
                //这里倒序添加的原因是循环内会addFirst操作（后一个在前一个前方加入），如果顺序添加，会导致原来的顺序被颠倒
                for (int i = list.size() - 1; i >= 0; i--) {
                    data.addFirst(list.get(i));
                }
            }

            return !data.isEmpty();
        }
    }

}
