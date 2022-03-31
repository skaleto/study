package com.skaleto.things.leet;

public class Q671 {

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
     * 用例1：
     * -                        1
     * -                   /         \
     * -                 1              3
     * -             /      \         /   \
     * -           1         1      3      4
     * -         /  \      /  \   /  \   /  \
     * -       3    1     1   1  3   8  4   8
     * -     / \   / \   / \
     * -    3  3  1  6  2  1
     * <p>
     * 用例2：
     * -                  1
     * -                /   \
     * -               1     2
     * -             /  \  /  \
     * -            1   1 2   2
     * <p>
     * Your runtime beats 100 % of java submissions
     * Your memory usage beats 51.26 % of java submissions (38.6 MB)
     *
     * @param root
     * @return
     */
    public int findSecondMinimumValue(TreeNode root) {
        if (root.left == null && root.right == null) {
            return -1;
        }
        return helper(root);
    }

    public int helper(TreeNode root) {
        if (root == null) {
            return -1;
        }

        if (root.left == null && root.right == null) {
            return root.val;
        }

        if (root.left == null || root.right == null) {
            //不存在的情况
            return root.left != null ? root.left.val : root.right.val;
        }

        int left;
        int right;

        //如果根节点和左子树根相同且和右子树根不同，记录右子树根，递归扫描左子树
        if (root.val == root.left.val && root.val != root.right.val) {
            right = root.right.val;
            left = helper(root.left);
        } else if (root.val == root.right.val && root.val != root.left.val) {
            left = root.left.val;
            right = helper(root.right);
        } else {
            //剩下的情况是根节点和左右子树都相同的情况，左右子树都扫描
            left = helper(root.left);
            right = helper(root.right);
        }

        if (left != root.val && right != root.val) {
            if (left == -1) {
                return right;
            }
            if (right == -1) {
                return left;
            }
            return Math.min(left, right);
        } else if (left == root.val && right != root.val) {
            return right;
        } else if (left != root.val) {
            return left;
        } else {
            return -1;
        }
    }


    public int findSecondMinimumValue_2(TreeNode root) {
        if (root == null || root.left == null || root.right == null) {
            return -1;
        }

        int left = root.left.val;
        int right = root.right.val;

        //如果根节点和左子树根相同且和右子树根不同，记录右子树根，递归扫描左子树
        if (root.val == root.left.val) {
            left = findSecondMinimumValue_2(root.left);
        }

        if (root.val == root.right.val) {
            right = findSecondMinimumValue_2(root.right);
        }

        if (left == -1) {
            return right;
        }
        if (right == -1) {
            return left;
        }

        return Math.min(left, right);
    }
}
