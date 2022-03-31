package com.skaleto.things.leet;

public class Q110 {

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
     * Your runtime beats 50.22 % of java submissions
     * Your memory usage beats 16.1 % of java submissions (41.2 MB)
     * <p>
     * 复杂度O(n^2)
     *
     * @param root
     * @return
     */
    public boolean isBalanced(TreeNode root) {
        if (root == null || getHeight(root) == 1) {
            return true;
        }
        int leftHeight = getHeight(root.left);
        int rightHeight = getHeight(root.right);
        if (Math.abs(leftHeight - rightHeight) > 1) {
            return false;
        }
        return isBalanced(root.left) && isBalanced(root.right);
    }

    /**
     * 获得树的高度
     *
     * @param root
     * @return
     */
    public int getHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(getHeight(root.left), getHeight(root.right));
    }


    /**
     * 采用后序遍历把结果带回，时间复杂度O(n)
     * Your runtime beats 100 % of java submissions
     * Your memory usage beats 5.03 % of java submissions (41.4 MB)
     *
     * @param root
     * @return
     */
    public boolean isBalanced_2(TreeNode root) {
        return traverse(root) >= 0;
    }

    /**
     * 获得树的高度
     *
     * @param root
     * @return int, -1表示不平衡，>=0表示平衡且数值代表高度
     */
    public int traverse(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = traverse(root.left);
        int right = traverse(root.right);

        if (left == -1 || right == -1) {
            return -1;
        } else {
            int height = 1 + Math.max(left, right);
            if (Math.abs(left - right) <= 1) {
                return height;
            } else {
                return -1;
            }
        }
    }

}
