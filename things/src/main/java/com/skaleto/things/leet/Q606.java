package com.skaleto.things.leet;

public class Q606 {

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
     * Your runtime beats 16.7 % of java submissions
     * Your memory usage beats 9.04 % of java submissions (42.3 MB)
     * @param root
     * @return
     */
    public String tree2str(TreeNode root) {
        if (root == null) {
            return "";
        }

        if (root.left == null && root.right == null) {
            return root.val + "";
        } else if (root.left != null && root.right == null) {
            return root.val + "(" + tree2str(root.left) + ")";
        } else {
            return root.val + "(" + tree2str(root.left) + ")" + "(" + tree2str(root.right) + ")";
        }
    }
}
