package com.skaleto.things.leet;

/**
 * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 * 示例 1：
 * 输入：l1 = [1,2,4], l2 = [1,3,4]
 * 输出：[1,1,2,3,4,4]
 * <p>
 * 示例 2：
 * 输入：l1 = [], l2 = []
 * 输出：[]
 * <p>
 * 示例 3：
 * 输入：l1 = [], l2 = [0]
 * 输出：[0]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/merge-two-sorted-lists
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Q21 {


    static class ListNode {
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

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode first = list1;
        ListNode second = list2;

        ListNode dummy = new ListNode();
        ListNode head = dummy;

        while (first != null && second != null) {
            while (first != null && second != null && first.val <= second.val) {
                head.next = first;
                head = head.next;
                first = first.next;
            }

            while (first != null && second != null && first.val >= second.val) {
                head.next = second;
                head = head.next;
                second = second.next;
            }

        }

        if (first == null) {
            head.next = second;
        }
        if (second == null) {
            head.next = first;
        }

        return dummy.next;
    }

//    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
//        if(l1==null){
//            return l2;
//        }
//        if(l2==null){
//            return l1;
//        }
//        ListNode cur1=l1;
//        ListNode cur2=l2;
//        ListNode dummyHead=new ListNode();
//        ListNode prev=dummyHead;
//        while(cur1!=null&&cur2!=null){
//            if(cur1.val<=cur2.val){
//                prev.next=cur1;
//                cur1=cur1.next;
//            }else{
//                prev.next=cur2;
//                cur2=cur2.next;
//            }
//            prev=prev.next;
//        }
//        if(cur1!=null){
//            prev.next=cur1;
//        }
//
//        if(cur2!=null){
//            prev.next=cur2;
//        }
//
//        return dummyHead.next;
//    }
}
