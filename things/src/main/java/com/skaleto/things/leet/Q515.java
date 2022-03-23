package com.skaleto.things.leet;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Q515 {

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

    public List<Integer> largestValues(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            int levelMax = Integer.MIN_VALUE;
            for (int i = 0; i < size; i++) {
                TreeNode head = queue.pollFirst();
                if (head.left != null) {
                    queue.add(head.left);
                }
                if (head.right != null) {
                    queue.add(head.right);
                }
                levelMax = Math.max(levelMax, head.val);
            }
            result.add(levelMax);
        }

        return result;
    }

    public List<Integer> largestValues_20211227(TreeNode root) {

        List<Integer> result=new ArrayList<>();

        if(root==null){
            return result;
        }

        Deque<TreeNode> queue=new ArrayDeque<>();
        queue.addLast(root);

        while(!queue.isEmpty()){
            int pathMax=Integer.MIN_VALUE;
            int size=queue.size();
            for(int i=0;i<size;i++){
                TreeNode tmp=queue.pollFirst();
                pathMax=Math.max(pathMax, tmp.val);
                if(tmp.left!=null){
                    queue.addLast(tmp.left);
                }
                if(tmp.right!=null){
                    queue.addLast(tmp.right);
                }
            }
            result.add(pathMax);
        }

        return result;

    }
}
