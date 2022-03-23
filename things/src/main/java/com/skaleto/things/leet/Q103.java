package com.skaleto.things.leet;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Q103 {

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

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();

        if (root == null) {
            return result;
        }

        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        boolean isOrder = true;
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> layer = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode tmp = queue.pollFirst();
                layer.add(tmp.val);
                if (tmp.left != null) {
                    queue.addLast(tmp.left);
                }
                if (tmp.right != null) {
                    queue.addLast(tmp.right);
                }
            }

            if (!isOrder) {
                int i = 0;
                int j = layer.size() - 1;
                while (i < j) {
                    int tmp = layer.get(i);
                    layer.set(i, layer.get(j));
                    layer.set(j, tmp);
                    i++;
                    j--;
                }

                isOrder = true;
            } else {
                isOrder = false;
            }

            result.add(layer);

        }

        return result;

    }
}
