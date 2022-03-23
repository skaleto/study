package com.skaleto.things.leet;

import java.util.Arrays;

public class Q105 {

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

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        //结束条件
        if (preorder.length == 0 || inorder.length == 0) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[0]);
        int index = 0;
        while (inorder[index] != preorder[0]) {
            index++;
        }
        root.left = buildTree(Arrays.copyOfRange(preorder, 1, index + 1), Arrays.copyOfRange(inorder, 0, index));
        root.left = buildTree(Arrays.copyOfRange(preorder, index + 1, preorder.length), Arrays.copyOfRange(inorder, index + 1, inorder.length));
        return root;
    }

    public TreeNode buildTree_20211223(int[] preorder, int[] inorder) {
        if (preorder.length == 0) {
            return null;
        }

        TreeNode root = new TreeNode();
        root.val = preorder[0];

        int leftLength = 0;
        for (int i = 0; i < inorder.length; i++) {
            if (inorder[i] == preorder[0]) {
                leftLength = i;
                break;
            }
        }

        root.left = buildTree(Arrays.copyOfRange(preorder, 1, 1 + leftLength), Arrays.copyOfRange(inorder, 0, leftLength));
        root.right = buildTree(Arrays.copyOfRange(preorder, 1 + leftLength, preorder.length), Arrays.copyOfRange(inorder, leftLength + 1, inorder.length));

        return root;
    }

    //-------------------------------------------------------------------------------------
    /**
     * our runtime beats 55.8 % of java submissions
     * Your memory usage beats 47.59 % of java submissions (40.9 MB)
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTree_20220317(int[] preorder, int[] inorder) {
        return buildTree_helper_20220317(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }

    public TreeNode buildTree_helper_20220317(int[] preorder, int preLeft, int preRight, int[] inorder, int inLeft, int inRight) {
        if (preLeft > preRight || inLeft > inRight) {
            return null;
        }

        TreeNode root = new TreeNode();
        root.val = preorder[preLeft];

        int length = 0;
        for (int i = inLeft; i <= inRight; i++, length++) {
            if (inorder[i] == preorder[preLeft]) {
                break;
            }
        }

        root.left = buildTree_helper_20220317(preorder, preLeft + 1, preLeft + 1 + length - 1,
                inorder, inLeft, inLeft + length - 1);
        root.right = buildTree_helper_20220317(preorder, preLeft + length + 1, preRight,
                inorder, inLeft + length + 1, inRight);

        return root;
    }


    public static void main(String[] args) {
        Q105 q = new Q105();
        TreeNode treeA = q.buildTree_20220317(new int[]{1, 2, 4, 5, 3}, new int[]{4, 2, 5, 1, 3});
        System.out.println();
    }
}
