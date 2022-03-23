package com.skaleto.things.leet;

public class Q236 {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        //如果root是任意一个节点，那么最近公共祖先一定是root
        if (root == p || root == q) {
            return root;
        }

        if (root == null) {
            return null;
        }

        TreeNode leftAncestor = lowestCommonAncestor(root.left, p, q);
        TreeNode rightAncestor = lowestCommonAncestor(root.right, p, q);

        //如果左右祖先都为null，则该节点祖先也是null
        if (leftAncestor == null && rightAncestor == null) {
            return null;
        }
        //如果左右祖先有一个是null，另一个不是，则祖先是另一个
        if (leftAncestor == null) {
            return rightAncestor;
        }
        //如果左右祖先有一个是null，另一个不是，则祖先是另一个
        if (rightAncestor == null) {
            return leftAncestor;
        }
        //如果左右祖先都不是null，则祖先是root
        return root;
    }
}
