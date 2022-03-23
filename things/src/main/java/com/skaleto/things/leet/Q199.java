package com.skaleto.things.leet;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Q199 {

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
     * Your runtime beats 80.42 % of java submissions
     * Your memory usage beats 31.5 % of java submissions (39.8 MB)
     * @param root
     * @return
     */
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        result.add(root.val);

        Deque<TreeNode> deque = new ArrayDeque<>();
        deque.add(root);
        while (deque.size() != 0) {
            int length = deque.size();
            for (int i = 0; i < length; i++) {
                TreeNode n = deque.pollFirst();
                if (n.left != null) {
                    deque.addLast(n.left);
                }
                if (n.right != null) {
                    deque.addLast(n.right);
                }
            }
            if (deque.size() != 0) {
                result.add(deque.getLast().val);
            }
        }
        return result;
    }
}
