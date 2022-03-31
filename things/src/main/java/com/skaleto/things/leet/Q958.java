package com.skaleto.things.leet;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Q958 {

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
     * Your runtime beats 69.95 % of java submissions
     * Your memory usage beats 54.64 % of java submissions (40.6 MB)
     *
     * @param root
     * @return
     */
    public boolean isCompleteTree(TreeNode root) {
        //BFS遍历，每一层判断数量和是否有空位

        Deque<TreeNode> deque = new ArrayDeque<>();
        deque.addLast(root);

        int curDepth = 0;

        boolean isValid = true;
        while (!deque.isEmpty()) {
            int size = deque.size();

            boolean preNull = false;
            for (int i = 0; i < size; i++) {
                TreeNode tmp = deque.pollFirst();

                //假如某个节点还有子节点，那么它的前面不能存在空的子节点
                if (tmp.left != null) {
                    deque.addLast(tmp.left);
                    if (preNull) {
                        isValid = false;
                    }
                } else {
                    preNull = true;
                }

                if (tmp.right != null) {
                    deque.addLast(tmp.right);
                    if (preNull) {
                        isValid = false;
                    }
                } else {
                    preNull = true;
                }
            }

            if (!deque.isEmpty()) {
                //假如遍历完队列中还有内容，那么这一层不是最后一层
                //检查这一层的数量，是否是2的幂
                if (size != Math.pow(2, curDepth)) {
                    return false;
                }
            } else {
                return isValid;
            }
            curDepth++;
        }

        return true;
    }


    public static void main(String[] args) {
        Q958 q = new Q958();
        System.out.println(q.isCompleteTree(
                q.generateTree(
                        new int[]{1, 2, 4, 5, 3, 7},
                        new int[]{4, 2, 5, 1, 3, 7})
        ));
    }

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
