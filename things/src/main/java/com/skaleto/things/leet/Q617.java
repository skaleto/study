package com.skaleto.things.leet;

public class Q617 {

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
     * Your memory usage beats 48.94 % of java submissions (41.3 MB)
     *
     * @param root1
     * @param root2
     * @return
     */
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) {
            return null;
        }

        TreeNode root;
        if (root1 == null) {
            //左树为空，右树不为空时，合并结果就是右树
            root = root2;
        } else {
            if (root2 == null) {
                //左树不为空，右树为空时，合并结果就是左树
                root = root1;
            } else {
                //两棵树都不为空时，创建一个新节点，赋值为左右树值之和
                root = new TreeNode(root1.val + root2.val);
                //递归合并两棵树的左子树和右子树
                root.left = mergeTrees(root1.left, root2.left);
                root.right = mergeTrees(root1.right, root2.right);
            }
        }

        return root;
    }
}
