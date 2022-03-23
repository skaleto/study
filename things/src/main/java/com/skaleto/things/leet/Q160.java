package com.skaleto.things.leet;

import java.util.HashSet;
import java.util.Set;

public class Q160 {

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public ListNode getIntersectionNode_1(ListNode headA, ListNode headB) {
        Set<ListNode> listSet = new HashSet<>();
        ListNode i = headA;
        while (i != null) {
            listSet.add(i);
            i = i.next;
        }

        ListNode j = headB;
        while (j != null) {
            if (listSet.contains(j)) {
                return j;
            }
            j = j.next;
        }
        return null;
    }

    /**
     * 双指针法，如果两个链表有相交的点，那么在相交处，两个指针走过的路程一样长，否则两个指针结束时都是null
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode_2(ListNode headA, ListNode headB) {
        ListNode i = headA;
        ListNode j = headB;
        while (i != j) {
            i = i == null ? headB : i.next;
            j = j == null ? headA : j.next;
        }
        return i;
    }
}
