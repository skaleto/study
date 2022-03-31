package com.skaleto.things.leet;

public class Q572 {

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
     * Your runtime beats 92.66 % of java submissions
     * Your memory usage beats 49.76 % of java submissions (41.2 MB)
     *
     * @param root
     * @param subRoot
     * @return
     */
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if (root == null || subRoot == null) {
            return false;
        }

        boolean result = false;
        if (root.val == subRoot.val) {
            result = isSub(root, subRoot);
        }

        if (!result) {
            result = isSubtree(root.left, subRoot);
        }

        if (!result) {
            result = isSubtree(root.right, subRoot);
        }

        return result;
    }


    public boolean isSub(TreeNode root, TreeNode subRoot) {
        if (root == null && subRoot == null) {
            return true;
        }

        if (root == null || subRoot == null) {
            return false;
        }

        if (root.val != subRoot.val) {
            return false;
        }

        return isSub(root.left, subRoot.left) && isSub(root.right, subRoot.right);
    }
}
