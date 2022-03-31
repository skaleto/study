package com.skaleto.things.leet;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Q113 {

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


    List<List<Integer>> result = null;

    /**
     * Your runtime beats 99.99 % of java submissions
     * Your memory usage beats 26.76 % of java submissions (41.8 MB)
     * @param root
     * @param targetSum
     * @return
     */
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        result = new ArrayList<>();
        Deque<Integer> path = new ArrayDeque<>();

        dfs(root, path, 0, targetSum);
        return result;
    }

    public void dfs(TreeNode root, Deque<Integer> path, int sum, int targetSum) {
        if (root == null) {
            return;
        }

        path.addLast(root.val);
        sum += root.val;

        if (root.left == null && root.right == null && sum == targetSum) {
            result.add(new ArrayList<>(path));
            path.pollLast();
            return;
        }

        dfs(root.left, path, sum, targetSum);
        dfs(root.right, path, sum, targetSum);
        path.pollLast();

    }


    public static void main(String[] args) {

    }
}
