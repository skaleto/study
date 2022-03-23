package com.skaleto.things.leet;

public class Q230 {

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

    int count = 0;
    int value;
    int k;

    public int kthSmallest(TreeNode root, int k) {
        this.k = k;
        traverse(root);
        return value;
    }

    public void traverse(TreeNode root) {
        if (root == null) {
            return;
        }

        traverse(root.left);
        count++;
        if (count == k) {
            value = root.val;
            return;
        }
        traverse(root.right);
    }
}
