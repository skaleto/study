package com.skaleto.things.leet;

import java.util.*;

public class Q863 {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 3,5,1,6,2,0,8,null,null,7,4
     * -            3
     * -          /   \
     * -         5     1
     * -       /  \  /  \
     * -      6   2 0   8
     * -         / \
     * -        7  4
     * Your runtime beats 72.95 % of java submissions
     * Your memory usage beats 39.04 % of java submissions (41.3 MB)
     *
     * @param root
     * @param target
     * @param k
     * @return
     */
    Map<Integer, TreeNode> parent = new HashMap<>();

    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        traverse(root);

        Deque<TreeNode> deque = new ArrayDeque<>();
        deque.addLast(target);

        Set<Integer> visited = new HashSet<>();
        visited.add(target.val);
        List<Integer> result = new ArrayList<>();

        int depth = 0;
        while (!deque.isEmpty()) {
            int size = deque.size();
            for (int i = 0; i < size; i++) {
                TreeNode tmp = deque.pollFirst();
                if (depth == k) {
                    result.add(tmp.val);
                }

                if (tmp.left != null && !visited.contains(tmp.left.val)) {
                    deque.addLast(tmp.left);
                    visited.add(tmp.left.val);
                }
                if (tmp.right != null && !visited.contains(tmp.right.val)) {
                    deque.addLast(tmp.right);
                    visited.add(tmp.right.val);
                }

                TreeNode parentNode = parent.get(tmp.val);
                if (parentNode != null && !visited.contains(parentNode.val)) {
                    deque.addLast(parentNode);
                    visited.add(parentNode.val);
                }

            }
            depth++;
        }

        return result;
    }

    public void traverse(TreeNode root) {
        if (root == null) {
            return;
        }

        if (root.left != null) {
            parent.put(root.left.val, root);
        }

        if (root.right != null) {
            parent.put(root.right.val, root);
        }

        traverse(root.left);
        traverse(root.right);
    }
}
