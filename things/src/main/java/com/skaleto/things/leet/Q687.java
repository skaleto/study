package com.skaleto.things.leet;

public class Q687 {
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

    int max = 1;

    /**
     * 双递归dfs
     * Your runtime beats 5.72 % of java submissions
     * Your memory usage beats 23.03 % of java submissions (45 MB)
     *
     * @param root
     * @return
     */
    public int longestUnivaluePath(TreeNode root) {
        travereTree(root);
        return max - 1;
    }

    public void travereTree(TreeNode root) {
        if (root == null) {
            return;
        }

        traversePath(root, root.val);

        travereTree(root.left);
        travereTree(root.right);

    }

    /**
     * 从root出发能够到达的最长同值路径
     * @param root
     * @param val
     * @return
     */
    public int traversePath(TreeNode root, int val) {
        if (root == null) {
            return 0;
        }

        if (root.val == val) {
            int left = traversePath(root.left, val);
            int right = traversePath(root.right, val);
            max = Math.max(max, left + right + 1);
            return 1 + Math.max(left, right);
        } else {
            return 0;
        }

    }


    /**
     * 自底向上递归法
     * Your runtime beats 96.67 % of java submissions
     * Your memory usage beats 7.68 % of java submissions (45.2 MB)
     * @param root
     * @return
     */
    public int longestUnivaluePath_2(TreeNode root) {
        travereTree_2(root);
        return max - 1;
    }

    /**
     * 从root出发的最长同值路径
     * @param root
     * @return
     */
    public int travereTree_2(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = travereTree_2(root.left);
        int right = travereTree_2(root.right);

        //当左右子树根和root节点的值不相同时，left和right值不能计算，因为此时从root触发的路径为0
        if (root.left == null || root.left.val != root.val) {
            left = 0;
        }
        if (root.right == null || root.right.val != root.val) {
            right = 0;
        }

        max = Math.max(max, left + right + 1);
        return 1 + Math.max(left, right);
    }

}
