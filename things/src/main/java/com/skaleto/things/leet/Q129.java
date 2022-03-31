package com.skaleto.things.leet;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

public class Q129 {

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

    int sum = 0;

    /**
     * Your runtime beats 18.93 % of java submissions
     * Your memory usage beats 12.54 % of java submissions (39.2 MB)
     *
     * @param root
     * @return
     */
    public int sumNumbers_1(TreeNode root) {
        Deque<Integer> path = new ArrayDeque<>();
        dfs(root, path);
        return sum;
    }

    public void dfs(TreeNode root, Deque<Integer> path) {
        if (root == null) {
            sum += calculatePathNum(path);
            return;
        }

        path.addLast(root.val);

        if (root.left != null && root.right != null) {
            dfs(root.left, path);
            dfs(root.right, path);
        } else if (root.left == null && root.right != null) {
            dfs(root.right, path);
        } else if (root.left != null) {
            dfs(root.left, path);
        } else {
            dfs(null, path);
        }

        path.pollLast();
    }

    public int calculatePathNum(Deque<Integer> path) {
        int pow = path.size() - 1;
        Iterator<Integer> it = path.iterator();

        int result = 0;
        while (it.hasNext()) {
            result += it.next() * Math.pow(10, pow);
            pow--;
        }
        return result;
    }


    /**
     * Your runtime beats 100 % of java submissions
     * Your memory usage beats 16.97 % of java submissions (39.2 MB)
     *
     * @param root
     * @return
     */
    public int sumNumbers_2(TreeNode root) {
        return sum(root, 0);
    }

    public int sum(TreeNode root, int prevSum) {
        if (root == null) {
            return prevSum;
        }

        int cur = prevSum * 10 + root.val;
        if (root.left != null && root.right != null) {
            return sum(root.left, cur) + sum(root.right, cur);
        } else if (root.left == null && root.right != null) {
            return sum(root.right, cur);
        } else if (root.left != null) {
            return sum(root.left, cur);
        } else {
            return sum(null, cur);
        }
    }

    public int sum2(TreeNode root, int prevSum) {
        if (root == null) {
            return 0;
        }

        int cur = prevSum * 10 + root.val;
        if (root.left == null && root.right == null) {
            return cur;
        } else {
            return sum2(root.left, cur) + sum2(root.right, cur);
        }
    }


    public static void main(String[] args) {
        Q129 q = new Q129();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        System.out.println(q.sumNumbers_2(root));

    }

}
