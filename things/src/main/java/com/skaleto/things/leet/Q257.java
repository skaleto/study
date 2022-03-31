package com.skaleto.things.leet;

import java.util.ArrayList;
import java.util.List;

public class Q257 {

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

    List<String> result = new ArrayList<>();

    public List<String> binaryTreePaths(TreeNode root) {
        traverse(root, "");
        return result;
    }

    public void traverse(TreeNode root, String path) {
        if (root == null) {
            return;
        }

        if (root.left == null && root.right == null) {
            result.add(path + root.val);
            return;
        }

        path += root.val + "->";
        traverse(root.left, path);
        traverse(root.right, path);
    }


}
