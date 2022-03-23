package com.skaleto.things.leet;

public class Q104 {

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

    // public int maxDepth(TreeNode root) {
    //     /**
    //      * 考虑做个bfs，但bfs超出内存限制
    //      */
    //     if(root==null){
    //         return 0;
    //     }

    //     int depth=1;
    //     Deque<TreeNode> queue=new ArrayDeque<>();
    //     queue.addLast(root);

    //     while(!queue.isEmpty()){
    //         int size=queue.size();
    //         for(int i=0;i<size;i++){
    //             queue.pollFirst();
    //             if(root.left!=null){
    //                 queue.addLast(root.left);
    //             }
    //             if(root.right!=null){
    //                 queue.addLast(root.right);
    //             }
    //         }
    //         //一层遍历完成
    //         depth++;
    //     }

    //     return depth;

    // }

    public int maxDepth(TreeNode root) {
        /**
         * 不超过内存限制的dfs
         */
        if (root == null) {
            return 0;
        }

        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);

        return Math.max(leftDepth, rightDepth) + 1;

    }
}
