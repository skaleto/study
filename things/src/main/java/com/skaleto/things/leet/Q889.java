package com.skaleto.things.leet;

public class Q889 {

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
     * Your runtime beats 100 % of java submissions
     * Your memory usage beats 33.52 % of java submissions (41 MB)
     *
     * @param preorder
     * @param postorder
     * @return
     */
    public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        return buildTree_helper_20220317(preorder, 0, preorder.length - 1, postorder, 0, postorder.length - 1);
    }

    public TreeNode buildTree_helper_20220317(int[] preorder, int preLow, int preHigh, int[] postorder, int postLow, int postHigh) {
        if (preLow > preHigh || postLow > postHigh) {
            return null;
        }

        TreeNode root = new TreeNode();
        root.val = preorder[preLow];

        if (preLow + 1 > preHigh) {
            return root;
        }

        int length = 0;
        for (int i = postLow; i <= postHigh; i++, length++) {
            if (postorder[i] == preorder[preLow + 1]) {
                break;
            }
        }
//
        root.left = buildTree_helper_20220317(preorder, preLow + 1, preLow + 1 + length,
                postorder, postLow, postLow + length);
        root.right = buildTree_helper_20220317(preorder, preLow + length + 2, preHigh,
                postorder, postLow + length + 1, postHigh - 1);

        return root;
    }


    public static void main(String[] args) {
        Q889 q = new Q889();
        /**
         *      1
         *     /\
         *   2   3
         *  /\  /\
         * 4 5 6 7
         */
        TreeNode treeA = q.constructFromPrePost(new int[]{1, 2, 4, 5, 3, 6, 7}, new int[]{4, 5, 2, 6, 7, 3, 1});
        System.out.println();
    }
}
