package com.skaleto.things.leet;

public class Q563 {

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

    //--------------------------------------------------------------
    int sum = 0;

    /**
     * 双递归dfs
     * Your runtime beats 19.72 % of java submissions
     * Your memory usage beats 64.19 % of java submissions (40.9 MB)
     *
     * @param root
     * @return
     */
    public int findTilt(TreeNode root) {
        if (root == null) {
            return 0;
        }

        traverseTree(root);
        return sum;
    }

    public void traverseTree(TreeNode root) {
        if (root == null) {
            return;
        }

        int left = traversePath(root.left);
        int right = traversePath(root.right);

        sum += Math.abs(left - right);

        traverseTree(root.left);
        traverseTree(root.right);

    }

    public int traversePath(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return root.val + traversePath(root.left) + traversePath(root.right);
    }

    //--------------------------------------------------------------

    /**
     * 单递归后序遍历
     * Your runtime beats 100 % of java submissions
     * Your memory usage beats 44.97 % of java submissions (41.1 MB)
     * @param root
     * @return
     */
    public int findTilt_2(TreeNode root) {
        if (root == null) {
            return 0;
        }

        traverse(root);
        return sum;
    }

    /**
     *
     *
     * @param root
     * @return root节点+左右子树的和
     */
    public int traverse(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = traverse(root.left);
        int right = traverse(root.right);

        sum += Math.abs(left - right);

        return root.val + left + right;

    }

    /**
     * -             4
     * -           /  \
     * -          2   9
     * -        /  \   \
     * -       3    5   7
     *
     * @param args
     */
    public static void main(String[] args) {

    }
}
