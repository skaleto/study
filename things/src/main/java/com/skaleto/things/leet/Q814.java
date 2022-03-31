package com.skaleto.things.leet;

public class Q814 {

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
     * 自顶向下双递归dfs
     * Your runtime beats 100 % of java submissions
     * Your memory usage beats 20.76 % of java submissions (39.2 MB)
     *
     * @param root
     * @return
     */
    public TreeNode pruneTree(TreeNode root) {
        traverse(root);
        //特判剪枝后只剩一个root节点为0的情况
        if (root.left == null && root.right == null && root.val == 0) {
            return null;
        }
        return root;
    }

    public void traverse(TreeNode root) {
        if (root == null) {
            return;
        }

        boolean left = containsOne(root.left);
        boolean right = containsOne(root.right);

        if (!left) {
            root.left = null;
        }

        if (!right) {
            root.right = null;
        }

        traverse(root.left);
        traverse(root.right);
    }

    public boolean containsOne(TreeNode root) {
        if (root == null) {
            return false;
        }

        if (root.val == 1) {
            return true;
        }

        return containsOne(root.left) || containsOne(root.right);
    }


    /**
     * 自底向上dfs
     * Your runtime beats 100 % of java submissions
     * Your memory usage beats 9.66 % of java submissions (39.3 MB)
     *
     * @param root
     * @return
     */
    public TreeNode pruneTree_2(TreeNode root) {
        if (root == null) {
            return null;
        }

        TreeNode left = pruneTree_2(root.left);
        TreeNode right = pruneTree_2(root.right);
        if (left == null && right == null && root.val == 0) {
            return null;
        }

        root.left = left;
        root.right = right;

        return root;
    }
}
