package com.skaleto.things.leet;

public class Q116 {
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


    public Node connect(Node root) {
        if (root == null) {
            return null;
        }

        connectTwoNode(root.left, root.right);

        return root;
    }

    public void connectTwoNode(Node first, Node second) {
        if (first == null || second == null) {
            return;
        }

        first.next = second;
        connectTwoNode(first.left, first.right);
        connectTwoNode(second.left, second.right);
        connectTwoNode(first.right, second.left);
    }
}
