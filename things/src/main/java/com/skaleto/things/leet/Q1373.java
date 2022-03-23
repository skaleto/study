package com.skaleto.things.leet;

public class Q1373 {

    //-------------------------复杂度较高的解法--------------------------
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
     * 对于每一个节点来说，需要知道下面的信息：
     * 1. 当前节点的左右子树是否是BST
     * 2. 当前节点的左右子树是否能和当前节点组成BST
     * 3. 以当前节点为根的BST的和是多少
     *
     * @param root
     * @return
     */
    int count = 0;

    public int maxSumBST(TreeNode root) {
        traverse(root);
        return count;
    }

    public void traverse(TreeNode root) {
        if (root == null) {
            return;
        }

        TreeNode leftMax = getMax(root.left);
        TreeNode rightMin = getMin(root.right);

        //计算以当前节点为根的BST的和是多少
        int leftSum = getSum(root.left);
        int rightSum = getSum(root.right);

        boolean isValid = true;
        //判断当前节点的左右子树是否是BST
        if (!isBST(root.left) || !isBST(root.right)) {
            isValid = false;
        }

        //判断左右子树是否能和当前节点组成BST
        if (leftMax.val >= root.val || root.val >= rightMin.val) {
            isValid = false;
        }

        if (isValid) {
            int tmp = leftSum + rightSum + root.val;
            count = Math.max(count, tmp);
        }

        traverse(root.left);
        traverse(root.right);
    }

    public boolean isBST(TreeNode root) {
        return isValid(root, null, null);
    }

    public boolean isValid(TreeNode root, TreeNode min, TreeNode max) {
        if (root == null) {
            return true;
        }
        if (min != null && min.val >= root.val) {
            return false;
        }
        if (max != null && max.val <= root.val) {
            return false;
        }

        return isValid(root.left, min, root) && isValid(root.right, root, max);
    }

    public TreeNode getMax(TreeNode root) {
        if (root == null) {
            return new TreeNode(Integer.MAX_VALUE);
        }
        while (root.right != null) {
            root = root.right;
        }
        return root;
    }

    public TreeNode getMin(TreeNode root) {
        if (root == null) {
            return new TreeNode(Integer.MIN_VALUE);
        }
        while (root.left != null) {
            root = root.left;
        }
        return root;
    }

    public int getSum(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return getSum(root.left) + getSum(root.right) + root.val;
    }


    //--------------------------复杂度较低的解法--------------------------
    /**
     * Your runtime beats 98.47 % of java submissions
     * Your memory usage beats 67.79 % of java submissions (51.7 MB)
     * @param root
     * @return
     */
    public int maxSumBST1(TreeNode root) {
        traversePost(root);
        return count;
    }

    /**
     * @param root
     * @return int[]是一个长度为4的数组，分别代表"是否是合法的BST，是1否0"，子树的最小值，子树的最大值，子树的和
     */
    public int[] traversePost(TreeNode root) {
        if (root == null) {
            //如果某个节点为空，那么他是叶子节点，他的子树的最小值置为最大数（因为要跟别人比大小，始终要取别人），最大值置为最小数
            return new int[]{1, Integer.MAX_VALUE, Integer.MIN_VALUE, 0};
        }

        int[] left = traversePost(root.left);
        int[] right = traversePost(root.right);

        boolean isValid = true;
        //判断当前节点的左右子树是否是BST
        if (left[0] != 1 || right[0] != 1) {
            isValid = false;
        }

        //判断左右子树是否能和当前节点组成BST
        if (left[2] >= root.val || root.val >= right[1]) {
            isValid = false;
        }

        //注意这里的最大最小值要变换
        int min = Math.min(left[1], root.val);
        int max = Math.max(right[2], root.val);
        int tmp = left[3] + right[3] + root.val;

        if (isValid) {
            count = Math.max(count, tmp);
        }

        return new int[]{isValid ? 1 : 0, min, max, tmp};
    }

}
