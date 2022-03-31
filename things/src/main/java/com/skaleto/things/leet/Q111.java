package com.skaleto.things.leet;

import java.util.ArrayDeque;
import java.util.Deque;

public class Q111 {

    public int minDepth(TreeNode root) {

        /**
         * 考虑做个bfs，但bfs超出内存限制
         */
        if (root == null) {
            return 0;
        }

        int depth = 0;
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.addLast(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            if (size > 0) {
                depth++;
                for (int i = 0; i < size; i++) {
                    TreeNode n = queue.pollFirst();
                    if (n.left == null && n.right == null) {
                        return depth;
                    }
                    if (n.left != null) {
                        queue.addLast(n.left);
                    }
                    if (n.right != null) {
                        queue.addLast(n.right);
                    }
                }

            }
        }

        return depth;

    }


    /**
     * Your runtime beats 24.52 % of java submissions
     * Your memory usage beats 12.62 % of java submissions (61.3 MB)
     * @param root
     * @return
     */
    public int minDepth_20220324(TreeNode root) {
        if (root == null) {
            return 0;
        }

        if (root.left == null) {
            return minDepth(root.right) + 1;
        }

        if (root.right == null) {
            return minDepth(root.left) + 1;
        }


        return 1 + Math.min(minDepth(root.left), minDepth(root.right));
    }

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
}
