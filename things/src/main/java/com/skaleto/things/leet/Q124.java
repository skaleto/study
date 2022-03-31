package com.skaleto.things.leet;

public class Q124 {

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
     * @param root
     * @return
     */

    int max = Integer.MIN_VALUE;

    /**
     * Your runtime beats 100 % of java submissions
     * Your memory usage beats 12.9 % of java submissions (43.1 MB)
     * @param root
     * @return
     */
    public int maxPathSum(TreeNode root) {
        if (root == null) {
            return 0;
        }

        traverse(root);
        return max;
    }

    public int traverse(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = Math.max(0, traverse(root.left));
        int right = Math.max(0, traverse(root.right));

        max = Math.max(max, root.val + left + right);

        return root.val + Math.max(left, right);
    }


}
