package com.skaleto.things.leet;

public class Q112 {

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
     * dfs
     * Your runtime beats 100 % of java submissions
     * Your memory usage beats 42.65 % of java submissions (41.2 MB)
     * @param root
     * @param targetSum
     * @return
     */
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }

        //只有走到叶子节点并且节点值等于剩下的数值时才算存在路径
        if (root.left == null && root.right == null && root.val == targetSum) {
            return true;
        }

        targetSum -= root.val;
        return hasPathSum(root.left, targetSum) || hasPathSum(root.right, targetSum);
    }
}
