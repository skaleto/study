package com.skaleto.things.leet;

import java.util.HashMap;
import java.util.Map;

public class Q141 {

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    /**
     * 哈希表存储经过的路径节点，如果遍历到某个节点时在哈希表中存在，则存在环
     * @param head
     * @return
     */
    public boolean hasCycle_20211113_1(ListNode head) {
        Map<ListNode, Integer> record = new HashMap<>();
        ListNode cur = head;
        while (cur != null && cur.next != null) {
            if (record.get(cur) != null) {
                return true;
            } else {
                record.put(cur, 1);
                cur = cur.next;
            }
        }
        return false;
    }


    /**
     * 考虑用快慢指针，slow步长1，fast步长2，如果i和j相遇，则存在环
     * 只要遇到某个节点为空或某个节点后续节点为空，则一定没有环
     */
    public boolean hasCycle_20211113_2(ListNode head) {
        if(head==null||head.next==null){
            return false;
        }
        ListNode slow=head;
        ListNode fast=head.next;
        while(slow.next!=null){
            if(slow.equals(fast)){
                return true;
            }
            slow=slow.next;
            if(fast!=null && fast.next!=null){
                fast=fast.next.next;
            }
        }
        return false;
    }
}
