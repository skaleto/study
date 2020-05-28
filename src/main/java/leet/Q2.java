package leet;

/**
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 *
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 *
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 *
 * 示例：
 *
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-two-numbers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Q2 {

    public static void main(String[] args) {
//        ListNode first = new ListNode(2);
//        first.next = new ListNode(4);
//        first.next.next = new ListNode(3);
//
//        ListNode second = new ListNode(5);
//        second.next = new ListNode(6);
//        second.next.next = new ListNode(4);

        ListNode first = new ListNode(5);

        ListNode second = new ListNode(5);

        Q2.addTwoNumbers(first, second);
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode root = new ListNode(0);
        ListNode ret_cur = root;
        int add=0;
        while (l1 != null || l2 != null || add !=0) {
            int v1=l1!=null?l1.val:0;
            int v2=l2!=null?l2.val:0;
            int temp = v1 + v2 + add;
            add = temp >= 10 ? 1 : 0;
            ret_cur.next = temp >= 10 ? new ListNode(temp - 10) : new ListNode(temp);
            ret_cur = ret_cur.next;

            if(l1!=null){
                l1=l1.next;
            }
            if(l2!=null){
                l2=l2.next;
            }
        }



        return root.next;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
