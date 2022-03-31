package com.skaleto.things.leet;

public class Q222 {

    public static class TreeNode {
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
     * Your runtime beats 100 % of java submissions
     * Your memory usage beats 24.36 % of java submissions (44.2 MB)
     */
    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return countNodes(root.left) + countNodes(root.right) + 1;
    }


    /**
     * Your runtime beats 100 % of java submissions
     * Your memory usage beats 28.6 % of java submissions (44.1 MB)
     *
     * @param root
     * @return
     */
    public int countNodes_2(TreeNode root) {
        if (root == null) {
            return 0;
        }

        TreeNode leftTree = root.left;
        int leftHeight = 0;
        while (leftTree != null) {
            leftHeight++;
            leftTree = leftTree.left;
        }

        TreeNode rightTree = root.right;
        int rightHeight = 0;
        while (rightTree != null) {
            rightHeight++;
            rightTree = rightTree.left;
        }

        if (leftHeight == rightHeight) {
            return (1 << leftHeight) + countNodes(root.right);
        } else {
            return (1 << rightHeight) + countNodes(root.left);
        }
    }
}
