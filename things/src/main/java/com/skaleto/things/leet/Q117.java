package com.skaleto.things.leet;

import java.util.ArrayDeque;
import java.util.Deque;

public class Q117 {

    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }


    /**
     * bfs方式，层序遍历并连接每一层的前后节点
     * Your runtime beats 66.73 % of java submissions
     * Your memory usage beats 46.79 % of java submissions (40.9 MB)
     *
     * @param root
     * @return
     */
    public Node connect(Node root) {
        if (root == null) {
            return null;
        }
        Deque<Node> deque = new ArrayDeque<>();
        deque.addLast(root);

        while (!deque.isEmpty()) {
            int size = deque.size();
            Node pre = deque.pollFirst();
            if (pre.left != null) {
                deque.addLast(pre.left);
            }
            if (pre.right != null) {
                deque.addLast(pre.right);
            }
            for (int i = 1; i < size; i++) {
                Node cur = deque.pollFirst();
                if (cur.left != null) {
                    deque.addLast(cur.left);
                }
                if (cur.right != null) {
                    deque.addLast(cur.right);
                }
                pre.next = cur;
                pre = cur;
            }
        }

        return root;
    }


    /**
     * Your runtime beats 100 % of java submissions
     * Your memory usage beats 25.08 % of java submissions (41.1 MB)
     *
     * @param root
     * @return
     */
    public Node connect_2(Node root) {
        if (root == null) {
            return null;
        }

        Node cur = root;
        while (cur != null) {
            Node dummy = new Node();
            Node tmp = dummy;

            while (cur != null) {
                if (cur.left != null) {
                    tmp.next = cur.left;
                    tmp = tmp.next;
                }
                if (cur.right != null) {
                    tmp.next = cur.right;
                    tmp = tmp.next;
                }
                cur = cur.next;
            }

            cur = dummy.next;
        }

        return root;

    }


    /**
     * 递归
     * Your runtime beats 100 % of java submissions
     * Your memory usage beats 30.65 % of java submissions (41 MB)
     *
     * @param root
     * @return
     */
    public Node connect_3(Node root) {
        if (root == null || (root.left == null && root.right == null)) {
            return root;
        }

        if (root.left != null && root.right != null) {
            root.left.next = root.right;
            root.right.next = findNextLeaf(root.next);
        }

        if (root.left == null) {
            root.right.next = findNextLeaf(root.next);
        }

        if (root.right == null) {
            root.left.next = findNextLeaf(root.next);
        }

        connect_3(root.right);
        connect_3(root.left);

        return root;
    }

    public Node findNextLeaf(Node root) {
        while (root != null) {
            if (root.left != null) {
                return root.left;
            }
            if (root.right != null) {
                return root.right;
            }
            root = root.next;
        }
        return null;
    }
}
