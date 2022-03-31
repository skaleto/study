package com.skaleto.things.leet;

import java.util.ArrayDeque;
import java.util.Deque;

public class Q623 {

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
     * Your runtime beats 36.57 % of java submissions
     * Your memory usage beats 51.45 % of java submissions (40.8 MB)
     *
     * @param root
     * @param val
     * @param depth
     * @return
     */
    public TreeNode addOneRow(TreeNode root, int val, int depth) {
        if (depth == 1) {
            TreeNode newRoot = new TreeNode(val);
            newRoot.left = root;
            return newRoot;
        }

        Deque<TreeNode> deque = new ArrayDeque<>();
        deque.addLast(root);

        int curDepth = 1;
        while (!deque.isEmpty()) {
            int size = deque.size();
            if (curDepth == depth - 1) {
                //TODO
                for (int i = 0; i < size; i++) {
                    TreeNode tmp = deque.pollFirst();
                    TreeNode left = tmp.left;
                    TreeNode right = tmp.right;
                    TreeNode newLeft = new TreeNode(val);
                    TreeNode newRight = new TreeNode(val);

                    tmp.left = newLeft;
                    tmp.right = newRight;

                    newLeft.left = left;
                    newRight.right = right;
                }
                break;
            }

            for (int i = 0; i < size; i++) {
                TreeNode tmp = deque.pollFirst();
                if (tmp.left != null) {
                    deque.addLast(tmp.left);
                }
                if (tmp.right != null) {
                    deque.addLast(tmp.right);
                }
            }

            curDepth++;
        }

        return root;
    }
}
