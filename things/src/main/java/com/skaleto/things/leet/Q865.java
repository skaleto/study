package com.skaleto.things.leet;

import java.util.HashMap;
import java.util.Map;

public class Q865 {

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
     * 双递归dfs
     * 分步考虑：
     * 1. dfs扫描整棵树确定每个节点的深度以及最大深度
     * 2. dfs扫描整棵树找到包含最深节点的最小公共子树
     * Your runtime beats 26.87 % of java submissions
     * Your memory usage beats 38.05 % of java submissions (39.4 MB)
     *
     * @param root
     * @return
     */
    Map<TreeNode, Integer> depthMap = new HashMap<>();
    int maxDepth = 0;

    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        traverseDepth(root, 0);
        return traverseTree(root);
    }

    public void traverseDepth(TreeNode root, int depth) {
        if (root == null) {
            return;
        }

        depthMap.put(root, depth);
        maxDepth = Math.max(maxDepth, depth);

        depth++;

        traverseDepth(root.left, depth);
        traverseDepth(root.right, depth);
    }

    /**
     * 找到从root节点开始的包含最深节点的最小公共子树
     *
     * @param root
     * @return
     */
    public TreeNode traverseTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        int depth = depthMap.get(root);

        if (depth == maxDepth) {
            return root;
        }

        TreeNode left = traverseTree(root.left);
        TreeNode right = traverseTree(root.right);

        if (left != null && right != null) {
            return root;
        }

        if (left != null) {
            return left;
        }

        if (right != null) {
            return right;
        }

        return null;
    }


    //---------------------------------------------------------

    /**
     * 单递归自底向上dfs，需要逻辑判断需要获得左右子树的信息，所以采用后序遍历
     * 站在一个节点上，要检查它的子树所包含的节点的最大深度，以及最大深度节点的最近公共祖先
     * <p>
     * Your runtime beats 100 % of java submissions
     * Your memory usage beats 30.34 % of java submissions (39.5 MB)
     *
     * @param root
     * @return
     */
    public TreeNode subtreeWithAllDeepest_2(TreeNode root) {
        Result result = traverse(root);
        return result.commonTree;
    }

    public class Result {
        public TreeNode commonTree;
        public int maxDepth;

        public Result() {

        }

        public Result(TreeNode node, int val) {
            commonTree = node;
            maxDepth = val;
        }
    }

    public Result traverse(TreeNode root) {
        if (root == null) {
            return new Result(null, 0);
        }

        Result left = traverse(root.left);
        Result right = traverse(root.right);

        if (left.maxDepth == right.maxDepth) {
            return new Result(root, left.maxDepth + 1);
        }

        Result res = left.maxDepth > right.maxDepth ? left : right;
        res.maxDepth++;

        return res;
    }
}
