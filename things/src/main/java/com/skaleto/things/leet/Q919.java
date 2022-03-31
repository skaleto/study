package com.skaleto.things.leet;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

public class Q919 {

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
     * Your runtime beats 99.81 % of java submissions
     * Your memory usage beats 17.91 % of java submissions (42.1 MB)
     */
    class CBTInserter {

        TreeNode root;
        LinkedList<TreeNode> parentList;

        public CBTInserter(TreeNode root) {
            this.root = root;
            this.parentList = new LinkedList<>();

            Deque<TreeNode> deque = new ArrayDeque<>();
            deque.addLast(root);
            while (!deque.isEmpty()) {
                int size = deque.size();
                for (int i = 0; i < size; i++) {
                    TreeNode tmp = deque.pollFirst();
                    if (tmp.left == null || tmp.right == null) {
                        parentList.addLast(tmp);
                    }
                    if (tmp.left != null) {
                        deque.addLast(tmp.left);
                    }
                    if (tmp.right != null) {
                        deque.addLast(tmp.right);
                    }
                }
            }
        }

        public int insert(int val) {
            TreeNode node = new TreeNode(val);
            //创建一个新的节点插入树中，并且保持树的结构不变
            TreeNode parent = parentList.getFirst();
            if (parent.left == null) {
                parent.left = node;
            } else if (parent.right == null) {
                parent.right = node;
                parentList.pollFirst();
            }
            parentList.addLast(node);
            return parent.val;
        }

        public TreeNode get_root() {
            return root;
        }
    }
}
