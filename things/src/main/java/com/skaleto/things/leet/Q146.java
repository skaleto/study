package com.skaleto.things.leet;

import java.util.HashMap;
import java.util.Map;

/**
 * LRU
 * Your runtime beats 93.95 % of java submissions
 * Your memory usage beats 66.16 % of java submissions (108.2 MB)
 *
 * @author yaoyibin
 */
public class Q146 {

    Map<Integer, ListNode> dataMap = new HashMap<>();
    ListNode head;
    ListNode tail;

    int capacity;

    public Q146(int capacity) {
        this.capacity = capacity;
        this.head = new ListNode();
        this.tail = new ListNode();
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        ListNode result = dataMap.get(key);

        if (result != null) {
            //get后需要将数值移到队列头部
            moveToHead(result);
            return result.val;
        } else {
            return -1;
        }
    }


    public void put(int key, int value) {
        if (dataMap.containsKey(key)) {
            ListNode result = dataMap.get(key);
            result.val = value;
            moveToHead(result);
        } else {
            if (dataMap.size() == capacity) {
                ListNode last = pollLast();
                dataMap.remove(last.key);
            }
            ListNode node = new ListNode(key, value);
            addFirst(node);
            dataMap.put(key, node);
        }
    }

    private void moveToHead(ListNode cur) {
        ListNode prev = cur.prev;
        ListNode next = cur.next;
        ListNode first = head.next;

        //如果前置节点是head，那么可以直接跳出，否则可能出现死循环
        if (prev == head) {
            return;
        }

        cur.next = first;
        cur.prev = head;

        prev.next = next;
        next.prev = prev;

        head.next = cur;
        first.prev = cur;
    }

    private void addFirst(ListNode node) {
        ListNode next = head.next;

        node.next = next;
        node.prev = head;

        next.prev = node;
        head.next = node;
    }

    private ListNode pollLast() {
        ListNode cur = tail.prev;
        if (cur != head) {
            ListNode prev = cur.prev;
            cur.next = null;
            cur.prev = null;

            prev.next = tail;
            tail.prev = prev;
        }
        return cur;
    }

    public String print() {
        StringBuilder sb = new StringBuilder();
        ListNode cur = head.next;
        while (cur != tail && cur != null) {
            sb.append(cur.val);
            sb.append(",");
            cur = cur.next;
        }
        return sb.toString();
    }

    public static class ListNode {
        public int key;
        public int val;
        public ListNode prev;
        public ListNode next;

        public ListNode() {

        }

        public ListNode(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    public static void main(String[] args) {
        int[][] data = new int[][]{
                {10},
                {10, 13}, {3, 17}, {6, 11}, {10, 5}, {9, 10},
                {13},
                {2, 19},
                {2}, {3},
                {5, 25},
                {8},
                {9, 22}, {5, 5}, {1, 30},
                {11},
                {9, 12},
                {7}, {5}, {8}, {9},
                {4, 30}, {9, 3},
                {9}, {10}, {10},
                {6, 14}, {3, 1},
                {3},
                {10, 11},
                {8},
                {2, 14},
                {1}, {5}, {4},
                {11, 4}, {12, 24}, {5, 18},
                {13},
                {7, 23},
                {8}, {12},
                {3, 27}, {2, 12},
                {5},
                {2, 9}, {13, 4}, {8, 18}, {1, 7},
                {6},
                {9, 29}, {8, 21},
                {5},
                {6, 30}, {1, 12},
                {10},
                {4, 15}, {7, 22}, {11, 26}, {8, 17}, {9, 29},
                {5},
                {3, 4}, {11, 30},
                {12},
                {4, 29},
                {3}, {9}, {6},
                {3, 4},
                {1}, {10},
                {3, 29}, {10, 28}, {1, 20}, {11, 13},
                {3},
                {3, 12}, {3, 8}, {10, 9}, {3, 26},
                {8}, {7}, {5},
                {13, 17}, {2, 27}, {11, 15},
                {12},
                {9, 19}, {2, 15}, {3, 16},
                {1},
                {12, 17}, {9, 1}, {6, 19},
                {4}, {5}, {5},
                {8, 1}, {11, 7}, {5, 2}, {9, 28},
                {1},
                {2, 2}, {7, 4}, {4, 22}, {7, 24}, {9, 26}, {13, 28}, {11, 26}
        };

//        int[][] data = new int[][]{
//                {2}, {1, 1}, {2, 2}, {1}, {3, 3}, {2}, {4, 4}, {1}, {3}, {4}
//        };

//        int[][] data = new int[][]{
//                {1}, {2, 1}, {2}, {3, 2}, {2}, {3}
//        };

        Q146 q = null;
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < data.length; i++) {
            int[] tmp = data[i];
            if (i == 0 && tmp.length == 1) {
                q = new Q146(tmp[0]);
                result.append("null");
            } else {
                if (tmp.length == 1) {
                    result.append(q.get(tmp[0]));
                }
                if (tmp.length == 2) {
                    q.put(tmp[0], tmp[1]);
                    result.append("null");
                }
            }
            result.append(",");
            System.out.println(q.print());
        }
        System.out.println("RESULT: " + result.toString());
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
