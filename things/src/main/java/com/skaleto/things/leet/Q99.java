package com.skaleto.things.leet;

public class Q99 {

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

    TreeNode first;
    TreeNode second;
    TreeNode pre = new TreeNode(Integer.MIN_VALUE);

    /**
     * Your runtime beats 100 % of java submissions
     * Your memory usage beats 55.17 % of java submissions (41.1 MB)
     *
     * @param root
     */
    public void recoverTree(TreeNode root) {
        traverse(root);

        int tmp = first.val;
        first.val = second.val;
        second.val = tmp;
    }

    public void traverse(TreeNode root) {
        if (root == null) {
            return;
        }

        traverse(root.left);
        if (root.val < pre.val) {
            //root节点小于前置节点，说明第一个错位的地方是pre
            if (first == null) {
                first = pre;
            }
            //第二次进入时
            second = root;
        }
        pre = root;
        traverse(root.right);
    }
}
