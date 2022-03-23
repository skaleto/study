package com.skaleto.things.leet;

public class Q23 {

    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode mergeKLists(ListNode[] lists) {
        return merge(lists, 0, lists.length - 1);
    }

    public ListNode merge(ListNode[] lists, int left, int right) {
        if (left == right) {
            return lists[left];
        }

        if (left > right) {
            return null;
        }

        int mid = (left + right) / 2;
        return mergeTwo(merge(lists, left, mid), merge(lists, mid + 1, right));

    }

    public ListNode mergeTwo(ListNode first, ListNode second) {
        ListNode i = first;
        ListNode j = second;

        ListNode result = new ListNode();
        ListNode head = result;
        while (i != null && j != null) {
            if (i.val < j.val) {
                result.next = i;
                i = i.next;
            } else {
                result.next = j;
                j = j.next;
            }
            result = result.next;
        }

        result.next = i != null ? i : j;
        return head.next;
    }
}
