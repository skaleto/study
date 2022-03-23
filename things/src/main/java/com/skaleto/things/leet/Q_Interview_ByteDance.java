package com.skaleto.things.leet;

import java.util.Arrays;

public class Q_Interview_ByteDance {

    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;
    }

    public static void main(String[] args) {
        Q_Interview_ByteDance q = new Q_Interview_ByteDance();
        /**
         * -------1
         * -----/  \
         * ----2    3
         * --/  \
         * -4    5
         */
        TreeNode treeA = q.generateTree(new int[]{1, 2, 4, 5, 3}, new int[]{4, 2, 5, 1, 3});
        TreeNode treeB = q.generateTree(new int[]{2, 4, 5}, new int[]{4, 2, 5});
        TreeNode treeC = q.generateTree(new int[]{2, 4, 3}, new int[]{4, 2, 3});

        System.out.println(q.isSubStructure(treeA, treeB));
        System.out.println(q.isSubStructure(treeA, treeC));
        System.out.println(q.isSubStructure(treeB, treeC));
    }

    /**
     * 通过先序遍历和中序遍历结果还原一棵树
     * 注：树的数值必须是不重复的
     */
    public TreeNode generateTree(int[] preOrder, int[] innerOrder) {
        if (preOrder.length == 0 || innerOrder.length == 0) {
            return null;
        }
        TreeNode head = new TreeNode();
        head.val = preOrder[0];

        int index = 0;
        for (int i = 0; i < innerOrder.length; i++) {
            if (innerOrder[i] == preOrder[0]) {
                index = i;
                break;
            }
        }

        head.left = generateTree(Arrays.copyOfRange(preOrder, 1, index + 1), Arrays.copyOfRange(innerOrder, 0, index));
        head.right = generateTree(Arrays.copyOfRange(preOrder, index + 1, preOrder.length), Arrays.copyOfRange(innerOrder, index + 1, innerOrder.length));

        return head;

    }


    /**
     * 判断输入的树B是否是树A的子结构
     *
     * @param a
     * @param b
     * @return
     */
    public boolean isSubStructure(TreeNode a, TreeNode b) {
        if (a == null || b == null) {
            return false;
        }

        boolean result = false;

        if (a.val == b.val) {
            result = isAContainsB(a, b);
        }

        if (!result) {
            result = isSubStructure(a.left, b);
        }

        if (!result) {
            result = isSubStructure(a.right, b);
        }

        return result;
    }

    /**
     * 同时遍历a和b，检查A是否包含B
     *
     * @param a
     * @param b
     * @return
     */
    public boolean isAContainsB(TreeNode a, TreeNode b) {
        //注意这里判断b和a是否为空的顺序不能乱，否则会误判

        //如果b节点为空，则a一定包含b
        if (b == null) {
            return true;
        }

        //如果a节点为空，肯定不包含b
        if (a == null) {
            return false;
        }

        if (a.val != b.val) {
            return false;
        }

        return isAContainsB(a.left, b.left) && isAContainsB(a.right, b.right);
    }
}
