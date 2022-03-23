package com.skaleto.things.leet;

public class Q206 {

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

    public ListNode reverseList_20211110(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    public ListNode reverseList_20211113(ListNode head) {
        ListNode pre=null;
        ListNode cur=head;
        //要运行到cur节点为null的时候停止（也就是此时的cur为原链表的尾部的next指向的null）
        while(cur!=null){
            //先把当前节点的后节点存起来，因为马上要断开和后节点之间的next连接了
            ListNode tmp=cur.next;
            //当前节点和原本的后节点断开连接，指向新的后节点（也就是pre节点）
            cur.next=pre;
            //pre节点后移，此时和cur重合
            pre=cur;
            //cur节点后移，此时就是之前暂存的tmp节点
            cur=tmp;
        }
        return pre;
    }
}
