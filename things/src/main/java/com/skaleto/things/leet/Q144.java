package com.skaleto.things.leet;

import java.util.ArrayList;
import java.util.List;

public class Q144 {

    public List<Integer> preorderTraversal(TreeNode root) {
        /**
         * 对于每个节点root，
         * 如果有左子树，则找到左子树的最右节点
         *      找到左子树的最右节点后，将当前节点root连带着root的右子树一起挂到最右节点的右方，把root切到左子树
         *      如果遍历左子树的最右节点时遍历到了root，那么说明左子树已经遍历完了，把root加入结果集，切到右子树
         * 如果没有左子树，那么把root加入结果集
         */

        List<Integer> result=new ArrayList<>();

        TreeNode pre=null;
        while(root!=null){
            //如果有左子树
            if(root.left!=null){
                pre=root.left;
                //找左子树的最右节点（且不为root）
                while(pre.right!=null && pre.right!=root){
                    pre=pre.right;
                }
                //pre.right为空，说明pre就是左子树的最右节点，
                if(pre.right==null){
                    //把root挂上去
                    pre.right=root;
                    //加入结果
                    result.add(root.val);
                    //把root切到左子树
                    root=root.left;
                    continue;
                }else{
                    //进这个else的时候，pre.right一定等于root，是我们人为加上去的
                    //在这里把这个结构切断，否则会影响原本的树结构
                    pre.right=null;
                }
                //如果没有左子树
            }else{
                //加入结果
                result.add(root.val);
            }

            root=root.right;
        }

        return result;

    }

    public static class TreeNode {
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
}
