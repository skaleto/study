package com.skaleto.things.leet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Q894 {

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

    //存放节点数和可能的完全二叉树的列表
    Map<Integer, List<TreeNode>> treeMap = new HashMap<>();

    public List<TreeNode> allPossibleFBT(int n) {
        return traverse(n);
    }

    public List<TreeNode> traverse(int n) {
        if (n % 2 == 0) {
            //偶数节点的满二叉树不存在
            return new ArrayList<>();
        }

        //单个节点的情况单独处理
        if (n == 1) {
            if (treeMap.containsKey(1)) {
                return treeMap.get(1);
            } else {
                List<TreeNode> list = new ArrayList<>();
                list.add(new TreeNode(0));
                treeMap.put(1, list);
                return list;
            }
        }

        List<TreeNode> result = new ArrayList<>();

        for (int i = 1; i <= n - 2; i += 2) {

            List<TreeNode> leftList = treeMap.get(i);
            if (leftList == null) {
                leftList = traverse(i);
            }
            List<TreeNode> rightList = treeMap.get(n - 1 - i);
            if (rightList == null) {
                rightList = traverse(n - 1 - i);
            }

            for (TreeNode t1 : leftList) {
                for (TreeNode t2 : rightList) {
                    TreeNode root = new TreeNode(0);
                    root.left = t1;
                    root.right = t2;
                    result.add(root);
                }
            }
        }

        treeMap.put(n, result);

        return result;
    }
}
