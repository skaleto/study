package com.skaleto.things.leet;

import java.util.ArrayList;
import java.util.List;

public class Q95 {

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
     * Your runtime beats 98.51 % of java submissions
     * Your memory usage beats 23.11 % of java submissions (42 MB)
     * @param n
     * @return
     */
    public List<TreeNode> generateTrees(int n) {
        return generate(1, n);
    }

    /**
     * 生成从lo到hi范围内的合法的BST树
     *
     * @param lo
     * @param hi
     * @return
     */
    public List<TreeNode> generate(int lo, int hi) {
        List<TreeNode> result = new ArrayList<>();
        if (lo > hi) {
            result.add(null);
            return result;
        }

        for (int i = lo; i <= hi; i++) {
            List<TreeNode> left = generate(lo, i - 1);
            List<TreeNode> right = generate(i + 1, hi);

            for (TreeNode l : left) {
                for (TreeNode r : right) {
                    TreeNode root = new TreeNode(i);
                    root.left = l;
                    root.right = r;
                    result.add(root);
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        Q95 q=new Q95();
        System.out.println(q.generateTrees(3));
    }

}
