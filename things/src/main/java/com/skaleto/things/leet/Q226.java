package com.skaleto.things.leet;

public class Q226 {

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

    public TreeNode invertTree_20211122(TreeNode root) {
        if (root == null) {
            return root;
        }
        if (root.left == null && root.right == null) {
            return root;
        }
        TreeNode tmp = root.left;
        root.left = invertTree_20211122(root.right);
        root.right = invertTree_20211122(tmp);

        return root;
    }

    public TreeNode invertTree_20220317(TreeNode root) {
        if (root == null) {
            return null;
        }

        TreeNode left = invertTree_20220317(root.right);
        TreeNode right = invertTree_20220317(root.left);

        root.left = left;
        root.right = right;
        return root;
    }


    public TreeNode invertTree_20211222(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) {
            return root;
        }

        TreeNode tmp = invertTree_20211222(root.left);
        root.left = invertTree_20211222(root.right);
        root.right = tmp;

        return root;
    }
}
