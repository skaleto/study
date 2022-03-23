package com.skaleto.things.leet;

/**
 * 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
 * <p>
 * k 是一个正整数，它的值小于或等于链表的长度。
 * <p>
 * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 * <p>
 * 进阶：
 * <p>
 * 你可以设计一个只使用常数额外空间的算法来解决此问题吗？
 * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
 *  
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：head = [1,2,3,4,5], k = 2
 * 输出：[2,1,4,3,5]
 * 示例 2：
 * <p>
 * <p>
 * 输入：head = [1,2,3,4,5], k = 3
 * 输出：[3,2,1,4,5]
 * 示例 3：
 * <p>
 * 输入：head = [1,2,3,4,5], k = 1
 * 输出：[1,2,3,4,5]
 * 示例 4：
 * <p>
 * 输入：head = [1], k = 1
 * 输出：[1]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-nodes-in-k-group
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Q25 {

    static public class ListNode {
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

    public ListNode reverseKGroup_20211114(ListNode head, int k) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode tail = head;
        for (int i = 0; i < k; i++) {
            if (tail != null) {
                tail = tail.next;
            } else {
                return head;
            }
        }

        ListNode newHead = reverseList_20211114(head, tail);
        head.next = reverseKGroup_20211114(tail, k);
        return newHead;
    }

    public ListNode reverseList_20211114(ListNode head, ListNode tail) {
        ListNode pre = null;
        ListNode next = null;
        while (head != tail) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    public ListNode reverseKGroup_20211114_2(ListNode head, int k) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode dummy = new ListNode();
        dummy.next = head;

        ListNode pre = dummy;
        ListNode end = dummy;

        while (end.next != null) {
            for (int i = 0; i < k && end != null; i++) {
                end = end.next;
            }

            if (end == null) {
                break;
            }

            ListNode start = pre.next;
            ListNode next = end.next;

            //把end后面的链断开，方便reverse
            end.next = null;
            pre.next = reverseList_20211114_2(start);

            //原来的start反转之后变成了尾部，所以要和后面的链连接起来
            start.next = next;
            //一段反转后，pre和end都指向下一段需要反转的链表前节点
            pre = start;
            end = pre;
        }

        return dummy.next;
    }

    public ListNode reverseList_20211114_2(ListNode head) {
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


    //---------------20211216-------------------

    public ListNode reverseKGroup_20211216(ListNode head, int k) {

        ListNode dummy = new ListNode();
        dummy.next = head;

        ListNode pre = dummy;
        ListNode tail = dummy;

        while (tail.next != null) {
            //如果剩余链表长度不足k个，则直接结束循环
            for (int i = 0; i < k; i++) {
                if (tail == null) {
                    break;
                }
                tail = tail.next;
            }

            if (tail == null) {
                break;
            }

            //暂存结尾后方的节点，是下一次反转的链表的开头，因为马上要断开了
            ListNode tmp = tail.next;
            //结尾断开
            tail.next = null;

            //pre的后继节点是即将开始反转的链表的head
            ListNode start = pre.next;
            //反转当前链，新得到的头是pre的后继
            pre.next = reverse_20211216(start);

            //原来的start节点现在变成了当前链的最后节点，于是可后方节点的开头连接起来
            start.next = tmp;
            //向后迭代，pre置为tail
            pre = start;
            tail = pre;

        }

        return dummy.next;
    }

    //反转一个链表
    public ListNode reverse_20211216(ListNode head) {

        ListNode pre = null;
        ListNode cur = head;

        while (cur != null) {
            ListNode tmp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = tmp;
        }

        return pre;

    }


    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode();
        dummy.next = head;

        ListNode left = dummy;
        ListNode right = dummy;
        while (right != null) {
            for (int i = 0; i < k && right != null; i++) {
                right = right.next;
            }

            if (right == null) {
                break;
            }

            //right的后继节点存起来
            ListNode nextHead = right.next;
            right.next = null;

            //left的后继节点存起来
            ListNode start = left.next;
            //left和反转后的链连起来
            left.next = reverse(start);

            //之前left的后继节点现在是下一个链的前置节点
            start.next = nextHead;
            left = start;
            right = left;


        }

        return dummy.next;
    }

    public ListNode reverse(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode tmp = cur.next;

            cur.next = pre;
            pre = cur;
            cur = tmp;
        }

        return pre;
    }


}
