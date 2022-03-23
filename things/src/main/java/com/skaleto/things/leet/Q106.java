package com.skaleto.things.leet;

public class Q106 {
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
     * Your runtime beats 63.92 % of java submissions
     * Your memory usage beats 13.72 % of java submissions (41.3 MB)
     *
     * @param inorder
     * @param postorder
     * @return
     */
    public TreeNode buildTree_20220317(int[] inorder, int[] postorder) {
        return buildTree_helper_20220317(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1);
    }

    public TreeNode buildTree_helper_20220317(int[] inorder, int inLow, int inHigh, int[] postorder, int postLow, int postHigh) {
        if (inLow > inHigh || postLow > postHigh) {
            return null;
        }

        TreeNode root = new TreeNode();
        root.val = postorder[postHigh];

        int length = 0;
        for (int i = inLow; i <= inHigh; i++, length++) {
            if (inorder[i] == postorder[postHigh]) {
                break;
            }
        }

        root.left = buildTree_helper_20220317(inorder, inLow, inLow + length - 1,
                postorder, postLow, postLow + length - 1);
        root.right = buildTree_helper_20220317(inorder, inLow + length + 1, inHigh,
                postorder, postLow + length, postHigh - 1);

        return root;
    }


    public static void main(String[] args) {
        Q106 q = new Q106();
        TreeNode treeA = q.buildTree_20220317(new int[]{9, 3, 15, 20, 7}, new int[]{9, 15, 7, 20, 3});
        System.out.println();
    }
}
