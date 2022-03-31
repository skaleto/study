package com.skaleto.things.leet;

import java.util.Arrays;

public class Q543 {

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


    //------------------------------------------------------------

    int max = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        //递归每个节点
        traverseTree(root);
        return max;
    }

    public void traverseTree(TreeNode root) {
        if (root == null) {
            return;
        }

        int length = traversePath(root.left) + traversePath(root.right) + 1;
        max = Math.max(max, length);
        traverseTree(root.left);
        traverseTree(root.right);
    }

    /**
     * 以root为起点的最大路径包含的节点数量
     *
     * @param root
     */
    public int traversePath(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(traversePath(root.left), traversePath(root.right));
    }


    //------------------------------------------------------------

    public int diameterOfBinaryTree_2(TreeNode root) {
        //递归每个节点
        traverse(root);
        return max - 1;
    }

    /**
     * 从root出发的的路径的最大长度
     *
     * @param root
     * @return
     */
    public int traverse(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = traverse(root.left);
        int right = traverse(root.right);

        //由于左右最大长度都计算出来了，所以此时可以加起来看一下直径
        int length = left + right + 1;
        max = Math.max(max, length);
        return Math.max(left, right) + 1;
    }


    /**
     * 4,-7,-3,null,null,-9,-3,9,-7,-4,null,6,null,-6,-6,null,null,0,6,5,null,9,null,null,-1,-4,null,null,null,-2
     * -             4
     * -           /   \
     * -        -7      -3
     * -              /    \
     * -           -9      -3
     * -          /  \     /
     * -        9    -7  -4
     * -      /     / \
     * -     6    -6  -6
     * -   /  \   /   /
     * - 0    6  5   9
     * -     /\       \
     * -   -1 -4      -2
     *
     * @param args
     */
    public static void main(String[] args) {
        Q543 q = new Q543();
//        System.out.println(q.diameterOfBinaryTree(q.generateTree(new int[]{1, 2, 4, 5, 3}, new int[]{4, 2, 5, 1, 3})));
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

}
