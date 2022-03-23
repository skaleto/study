package com.skaleto.things.leet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Q652 {

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

    //------------------------------20220317------------------------------------

    /**
     * Your runtime beats 5.02 % of java submissions
     * Your memory usage beats 5.86 % of java submissions (51.6 MB)
     *
     * @param root
     * @return
     */
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        //对于某一个节点，他需要知道以自己为根的树的结构，以及知道其他节点为根的树的结构进行比较
        List<TreeNode> result = new ArrayList<>();
        //用一个哈希表来存放序列化的树结构和出现的次数
        Map<String, Integer> nodeMap = new HashMap<>();

        find(root, result, nodeMap);
        return result;
    }

    public void find(TreeNode root, List<TreeNode> result, Map<String, Integer> nodeMap) {
        if (root == null) {
            return;
        }

        //检查以root为根的树的序列化结构
        String pattern = getTreePattern(root);

        if (!nodeMap.containsKey(pattern)) {
            nodeMap.put(pattern, 1);
        } else {
            //如果map中已经记录过相同结构的树，那么当前的树就是重复的结构，存入结果
            //同时由于结果要求去重，所以这里判断前面仅出现过一次的时候再加入结果
            if (nodeMap.get(pattern) == 1) {
                result.add(root);
            }
            nodeMap.put(pattern, nodeMap.get(pattern) + 1);
        }

        //进一步检查左右子树
        find(root.left, result, nodeMap);
        find(root.right, result, nodeMap);

    }

    /**
     * 通过前序遍历的方式构造一棵树的结构
     *
     * @param root
     * @return
     */
    public String getTreePattern(TreeNode root) {
        if (root == null) {
            return "#";
        }

        String left = getTreePattern(root.left);
        String right = getTreePattern(root.right);

        return root.val + "," + left + "," + right;

    }


    //------------------------------20220317------------------------------------

    /**
     * Your runtime beats 87.65 % of java submissions
     * Your memory usage beats 22.82 % of java submissions (48.5 MB)
     *
     * @param root
     * @return
     */
    public List<TreeNode> findDuplicateSubtrees_2(TreeNode root) {
        //对于某一个节点，他需要知道以自己为根的树的结构，以及知道其他节点为根的树的结构进行比较
        List<TreeNode> result = new ArrayList<>();
        //用一个哈希表来存放序列化的树结构和出现的次数
        Map<String, Integer> nodeMap = new HashMap<>();

        traverse(root, result, nodeMap);
        return result;
    }

    public String traverse(TreeNode root, List<TreeNode> result, Map<String, Integer> nodeMap) {
        if (root == null) {
            return "#";
        }

        //先检查左右子树
        String left = traverse(root.left, result, nodeMap);
        String right = traverse(root.right, result, nodeMap);

        String pattern = root.val + "," + left + "," + right;

        if (!nodeMap.containsKey(pattern)) {
            nodeMap.put(pattern, 1);
        } else {
            //如果map中已经记录过相同结构的树，那么当前的树就是重复的结构，存入结果
            //同时由于结果要求去重，所以这里判断前面仅出现过一次的时候再加入结果
            if (nodeMap.get(pattern) == 1) {
                result.add(root);
            }
            nodeMap.put(pattern, nodeMap.get(pattern) + 1);
        }

        return pattern;
    }

}
