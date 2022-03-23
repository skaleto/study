package com.skaleto.things.leet;

public class Q142 {

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public ListNode detectCycle(ListNode head) {
        if(head==null||head.next==null){
            return null;
        }
        ListNode slow=head;
        ListNode fast=head;
        while(true){
            if(fast==null||fast.next==null){
                return null;
            }

            slow=slow.next;
            fast=fast.next.next;

            if(slow==fast){
                break;
            }
        }

        fast=head;
        while(slow!=fast){
            slow=slow.next;
            fast=fast.next;
        }
        return fast;
    }
}
