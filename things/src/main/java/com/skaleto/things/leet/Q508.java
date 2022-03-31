package com.skaleto.things.leet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Q508 {

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

    // key: 子树元素和， value: 出现次数
    Map<Integer, Integer> map = new HashMap<>();

    int maxCount = 0;

    /**
     * Your runtime beats 32.71 % of java submissions
     * Your memory usage beats 39.35 % of java submissions (41.4 MB)
     *
     * @param root
     * @return
     */
    public int[] findFrequentTreeSum(TreeNode root) {
        traverse(root);

        List<Integer> result = new ArrayList<>();
        map.forEach((k, v) -> {
            if (v == maxCount) {
                result.add(k);
            }
        });

        return result.stream().mapToInt(Integer::valueOf).toArray();
    }

    public int traverse(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int sum = traverse(root.left) + traverse(root.right) + root.val;
        map.put(sum, map.getOrDefault(sum, 0) + 1);
        maxCount = Math.max(maxCount, map.getOrDefault(sum, 0));

        return sum;

    }
}
