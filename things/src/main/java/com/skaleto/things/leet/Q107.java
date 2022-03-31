package com.skaleto.things.leet;

import java.util.*;

public class Q107 {

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

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        LinkedList<List<Integer>> result = new LinkedList<>();
        if (root == null) {
            return result;
        }

        Deque<TreeNode> deque = new ArrayDeque<>();
        deque.addLast(root);
        while (!deque.isEmpty()) {
            List<Integer> path = new ArrayList<>();
            int size = deque.size();
            for (int i = 0; i < size; i++) {
                TreeNode tmp = deque.pollFirst();
                path.add(tmp.val);
                if (tmp.left != null) {
                    deque.addLast(tmp.left);
                }
                if (tmp.right != null) {
                    deque.addLast(tmp.right);
                }
            }
            result.addFirst(path);
        }
        return result;
    }
}
