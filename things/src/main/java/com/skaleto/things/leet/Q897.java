package com.skaleto.things.leet;

public class Q897 {

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
     * Your runtime beats 100 % of java submissions
     * Your memory usage beats 44.19 % of java submissions (38.9 MB)
     *
     * @param root
     * @return
     */
    public TreeNode increasingBST(TreeNode root) {
        return traverse(root);
    }

    public TreeNode traverse(TreeNode root) {
        if (root == null) {
            return null;
        }

        TreeNode left = traverse(root.left);
        TreeNode head;
        if (left == null) {
            head = root;
        } else {
            head = left;
            //记得断开root的左子树，否则树里面会出现环
            root.left = null;
            while (left.right != null) {
                left = left.right;
            }
            left.right = root;
        }
        root.right = traverse(root.right);
        return head;
    }
}
