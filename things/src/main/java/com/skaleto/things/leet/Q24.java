package com.skaleto.things.leet;

/**
 * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 * <p>
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：head = [1,2,3,4]
 * 输出：[2,1,4,3]
 * 示例 2：
 * <p>
 * 输入：head = []
 * 输出：[]
 * 示例 3：
 * <p>
 * 输入：head = [1]
 * 输出：[1]
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/swap-nodes-in-pairs
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Q24 {

    public static class ListNode {
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

    public ListNode swapPairs(ListNode head) {
        ListNode newHead = new ListNode();
        newHead.next = head;
        ListNode cur = newHead;

        while (cur.next != null && cur.next.next != null) {
            ListNode node1 = cur.next;
            ListNode node2 = cur.next.next;

            cur.next = node2;
            node1.next = node2.next;
            node2.next = node1;

            cur = node1;
        }

        return newHead.next;
    }

    public ListNode swapPairs1(ListNode head) {

        if (head == null || head.next == null) {
            return head;
        } else {
            ListNode tmpHead = head.next;
            head.next = swapPairs1(tmpHead.next);
            tmpHead.next=head;
            return tmpHead;
        }
    }

    public ListNode swapPairs_20211113_1(ListNode head) {
        ListNode newHead=new ListNode();
        newHead.next=head;
        //这里为什么要用一个tmp，是因为指针会随着循环不断遍历下去，而最重要返回的是从头部开始的链表，所以要另外把头部保存起来
        ListNode tmp=newHead;

        if(head==null || head.next==null){
            return head;
        }

        while(tmp.next!=null && tmp.next.next!=null){
            ListNode node1=tmp.next;
            ListNode node2=tmp.next.next;

            tmp.next=node2;
            node1.next=node2.next;
            node2.next=node1;

            tmp=node1;
        }
        return newHead.next;
    }

    public ListNode swapPairs_20211113_2(ListNode head) {
        while(head!=null&&head.next!=null){
            ListNode second=head.next;
            head.next=swapPairs_20211113_2(second.next);
            second.next=head;
            return second;
        }
        return head;
    }


}
