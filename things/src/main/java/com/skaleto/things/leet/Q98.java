package com.skaleto.things.leet;

import java.util.Stack;

public class Q98 {

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

    public boolean isValidBST(TreeNode root) {
        return validate(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public boolean validate(TreeNode root, long lower, long upper) {
        if (root == null) {
            return true;
        }

        if (root.val <= lower || root.val >= upper) {
            return false;
        }

        return validate(root.left, lower, root.val) && validate(root.right, root.val, upper);
    }


    long pre = Long.MIN_VALUE;

    public boolean isValidBST2(TreeNode root) {
        if (root == null) {
            return true;
        }
        boolean l = isValidBST(root.left);
        if (root.val <= pre) {
            return false;
        }
        pre = root.val;
        boolean r = isValidBST(root.right);
        return l && r;
    }

    public boolean isValidBST3(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        long pre = Long.MIN_VALUE;

        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }

            root = stack.pop();
            if (root.val <= pre) {
                return false;
            }
            pre = root.val;
            root = root.right;
        }

        return true;
    }


    // //典型错误：
    // public boolean isValidBST(TreeNode root) {
    //     if(root==null){
    //         return true;
    //     }

    //     if(root.left!=null && root.left.val>=root.val){
    //         return false;
    //     }

    //     if(root.right!=null && root.right.val<=root.val){
    //         return false;
    //     }

    //     return isValidBST(root.left) && isValidBST(root.right);
    // }

    /**
     * 这种实现在[0x80000000,0x80000000]的用例上过不去
     *
     * @param root
     * @return
     */
    public boolean isValidBST_20220321_1(TreeNode root) {
        return isValid(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public boolean isValid(TreeNode root, int lo, int hi) {
        if (root == null) {
            return true;
        }
        if (root.val <= lo || root.val >= hi) {
            return false;
        }
        return isValid(root.left, lo, root.val) && isValid(root.right, root.val, hi);
    }


    public boolean isValidBST_20220321_2(TreeNode root) {
        return isValid(root, null, null);
    }

    public boolean isValid(TreeNode root, TreeNode lo, TreeNode hi) {
        if (root == null) {
            return true;
        }
        if (lo != null && root.val <= lo.val) {
            return false;
        }

        if (hi != null && root.val >= hi.val) {
            return false;
        }
        return isValid(root.left, lo, root) && isValid(root.right, root, hi);
    }
}
