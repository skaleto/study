package com.skaleto.things.leet;

import java.util.ArrayList;
import java.util.List;

public class Q145 {

    //后序Morris
    public List<Integer> postorderTraversal(TreeNode head) {
        List<Integer> result = new ArrayList<>();
        if (head == null) {
            return result;
        }

        TreeNode cur1 = head;//遍历树的指针变量
        TreeNode cur2 = null;//当前子树的最右节点
        while (cur1 != null) {
            cur2 = cur1.left;
            if (cur2 != null) {
                while (cur2.right != null && cur2.right != cur1) {
                    cur2 = cur2.right;
                }
                if (cur2.right == null) {
                    cur2.right = cur1;
                    cur1 = cur1.left;
                    continue;
                } else {
                    cur2.right = null;
                    postMorrisPrint(cur1.left, result);
                }
            }
            cur1 = cur1.right;
        }
        postMorrisPrint(head, result);
        return result;
    }

    //打印函数
    public static void postMorrisPrint(TreeNode head, List<Integer> result) {
        TreeNode reverseList = postMorrisReverseList(head);
        TreeNode cur = reverseList;
        while (cur != null) {
            result.add(cur.val);
            cur = cur.right;
        }
        postMorrisReverseList(reverseList);
    }

    //翻转单链表
    public static TreeNode postMorrisReverseList(TreeNode head) {
        TreeNode cur = head;
        TreeNode pre = null;
        while (cur != null) {
            TreeNode next = cur.right;
            cur.right = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
