package com.skaleto.things.leet;

public class Q450 {

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

    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        if (root.val == key) {
            //如果左子树为空，那么把右子树搬过来
            if (root.left == null) {
                return root.right;
            }
            //如果右子树为空，那么把左子树搬过来
            if (root.right == null) {
                return root.left;
            }
            //获得右子树上最小的节点
            TreeNode rightMin = getRightMin(root.right);
            //从右子树中删除这个最小节点，并把删除后的根节点赋为右子树
            root.right = deleteNode(root.right, rightMin.val);

            //把root的左右子树挂到最小节点左右
            rightMin.left = root.left;
            rightMin.right = root.right;
            //root节点切换到最小节点
            root = rightMin;

            //  //同样也可以从左子树上找最大节点替换
            //  TreeNode rightMin=getLeftMax(root.left);
            //  root.left=deleteNode(root.left, rightMin.val);
            //  rightMin.left=root.left;
            //  rightMin.right=root.right;
            //  root=rightMin;

        } else if (root.val < key) {
            root.right = deleteNode(root.right, key);
        } else {
            root.left = deleteNode(root.left, key);
        }
        return root;
    }

    public TreeNode getRightMin(TreeNode root) {
        while (root.left != null) {
            root = root.left;
        }
        return root;
    }

    public TreeNode getLeftMax(TreeNode root) {
        while (root.right != null) {
            root = root.right;
        }
        return root;
    }
}
