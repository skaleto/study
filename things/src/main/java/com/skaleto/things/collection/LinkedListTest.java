package com.skaleto.things.collection;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author : ybyao
 * @Create : 2019-09-10 15:29
 */
public class LinkedListTest {

    private LinkedList<String> list = new LinkedList<>();

    public void init() {
        list.add("1");
        list.add("2");
        list.add("3");
    }


    public void print() {
        System.out.println(Arrays.toString(list.toArray()));
    }

    public void reverse() {
        list.getFirst();
    }

    public static void main(String[] args) {
        LinkedListTest test = new LinkedListTest();
        test.init();


    }



    public class MyList<E> {

        Node<E> first;

        Node<E> last;

        private class Node<E> {
            E item;
            Node<E> next;
            Node<E> prev;

            Node(Node<E> prev, E element, Node<E> next) {
                this.item = element;
                this.next = next;
                this.prev = prev;
            }
        }
    }
}
