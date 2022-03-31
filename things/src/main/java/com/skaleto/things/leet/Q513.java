package com.skaleto.things.leet;

import java.util.ArrayDeque;
import java.util.Deque;

public class Q513 {

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

    /**
     * bfs解法
     * Your runtime beats 68.51 % of java submissions
     * Your memory usage beats 68.36 % of java submissions (40.6 MB)
     *
     * @param root
     * @return
     */
    public int findBottomLeftValue(TreeNode root) {
        if (root == null) {
            return -1;
        }
        Deque<TreeNode> deque = new ArrayDeque<>();
        deque.addLast(root);
        int last = 0;

        while (!deque.isEmpty()) {
            int size = deque.size();
            for (int i = 0; i < size; i++) {
                TreeNode first = deque.pollFirst();
                if (first.left != null) {
                    deque.addLast(first.left);
                }
                if (first.right != null) {
                    deque.addLast(first.right);
                }
                if (i == 0) {
                    last = first.val;
                }
            }
        }

        return last;
    }

    public static void main(String[] args) {

    }
}
