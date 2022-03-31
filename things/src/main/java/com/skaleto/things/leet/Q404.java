package com.skaleto.things.leet;

public class Q404 {

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

    int result = 0;

    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) {
            return 0;
        }
        //仅有根节点的情况下特判，因为根节点不能算做叶子节点
        if (root.left == null && root.right == null) {
            return 0;
        }
        traverse(root);
        return result;
    }

    public void traverse(TreeNode root) {
        if (root == null) {
            return;
        }

        //当节点为叶子节点时加入结果
        if (root.left == null && root.right == null) {
            result += root.val;
        }

        //左子树肯定会走一遍
        traverse(root.left);
        //右子树需要右子树不是一个叶子节点的时候才会走一遍
        if (root.right != null && (root.right.right != null || root.right.left != null)) {
            traverse(root.right);
        }

    }
}
