package com.skaleto.things.leet;

import java.util.HashMap;
import java.util.Map;

public class Q437 {

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

    int result = 0;
    int target = 0;

    /**
     * 双递归解法
     * Your runtime beats 58.23 % of java submissions
     * Your memory usage beats 52 % of java submissions (40.8 MB)
     *
     * @param root
     * @param targetSum
     * @return
     */
    public int pathSum(TreeNode root, int targetSum) {
        target = targetSum;
        dfsTree(root);
        return result;
    }

    public void dfsTree(TreeNode root) {
        if (root == null) {
            return;
        }

        dfsPath(root, root.val);
        dfsTree(root.left);
        dfsTree(root.right);
    }

    public void dfsPath(TreeNode root, int sum) {
        if (sum == target) {
            result++;
        }

        if (root.left != null) {
            dfsPath(root.left, sum + root.left.val);
        }

        if (root.right != null) {
            dfsPath(root.right, sum + root.right.val);
        }

    }


    Map<Integer, Integer> targetMap = new HashMap<>();

    /**
     * 前缀和解法
     * Your runtime beats 64.33 % of java submissions
     * Your memory usage beats 53.09 % of java submissions (40.8 MB)
     *
     * @param root
     * @param targetSum
     * @return
     */
    public int pathSum_2(TreeNode root, int targetSum) {
        target = targetSum;
        targetMap.put(0, 1);
        dfs(root, 0);
        return result;
    }

    public void dfs(TreeNode root, int sum) {
        if (root == null) {
            return;
        }

        sum += root.val;
        result += targetMap.getOrDefault(sum - target, 0);
        targetMap.put(sum, targetMap.getOrDefault(sum, 0) + 1);

        dfs(root.left, sum);
        dfs(root.right, sum);

        targetMap.put(sum, targetMap.get(sum) - 1);
        sum -= root.val;

    }

}
