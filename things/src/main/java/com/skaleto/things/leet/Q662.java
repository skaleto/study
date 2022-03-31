package com.skaleto.things.leet;

import java.util.LinkedList;

public class Q662 {

    public class TreeNode {
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

    public int widthOfBinaryTree(TreeNode root) {
        LinkedList<TreeNode> deque = new LinkedList<>();
        deque.addLast(root);

        int maxWidth = 0;
        root.val = 0;
        while (!deque.isEmpty()) {
            int size = deque.size();
            int first = -1;
            int second = -1;
            for (int i = 0; i < size; i++) {
                TreeNode tmp = deque.pollFirst();
                if (tmp.left != null) {
                    deque.addLast(tmp.left);
                    tmp.left.val = tmp.val * 2;
                }
                if (tmp.right != null) {
                    deque.addLast(tmp.right);
                    tmp.right.val = tmp.val * 2 + 1;
                }
                if (i == 0) {
                    first = tmp.val;
                }
                if (i == size - 1) {
                    second = tmp.val;
                }
            }

            maxWidth = Math.max(maxWidth, second - first + 1);

        }

        return maxWidth;
    }
}
