package com.skaleto.things.leet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Q297 {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {

        }

        TreeNode(int x) {
            val = x;
        }
    }

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        return String.join(",", se(root));
    }

    public List<String> se(TreeNode root) {
        if (root == null) {
            return Arrays.asList("null");
        }

        List<String> result = new ArrayList<>();
        result.add(String.valueOf(root.val));
        result.addAll(se(root.left));
        result.addAll(se(root.right));

        return result;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] dataList = data.split(",");
        return de(Arrays.stream(dataList).collect(Collectors.toList()));
    }

    public TreeNode de(List<String> dataList) {
        if ("null".equals(dataList.get(0))) {
            dataList.remove(null);
            return null;
        }

        String data = dataList.remove(0);
        TreeNode tree = new TreeNode(Integer.parseInt(data));
        tree.left = de(dataList);
        tree.right = de(dataList);

        return tree;
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

    //----------------------------------------------------------------

    /**
     * 前序遍历方式序列化
     *
     * @param root
     * @return
     */
    public String serialize_preorder(TreeNode root) {
        if (root == null) {
            return "#";
        }
        String left = serialize_preorder(root.left);
        String right = serialize_preorder(root.right);
        return root.val + "," + left + "," + right;
    }

    public TreeNode deserialize_preorder(String data) {
        return dese_preorder(data.split(","));
    }

    int start = 0;

    public TreeNode dese_preorder(String[] data) {
        if (data[start].equals("#")) {
            return null;
        }
        int rootVal = Integer.parseInt(String.valueOf(data[start]));
        TreeNode root = new TreeNode(rootVal);
        ++start;
        root.left = dese_preorder(data);
        ++start;
        root.right = dese_preorder(data);
        return root;
    }


    /**
     * 后序遍历方式序列化
     *
     * @param root
     * @return
     */
    public String serialize_postorder(TreeNode root) {
        if (root == null) {
            return "#";
        }
        String left = serialize_postorder(root.left);
        String right = serialize_postorder(root.right);
        return left + "," + right + "," + root.val;
    }

    public TreeNode deserialize_postorder(String data) {
        String[] d = data.split(",");
        end = d.length - 1;
        return dese_postorder(d);
    }

    int end = 0;

    public TreeNode dese_postorder(String[] data) {
        if (data[end].equals("#")) {
            return null;
        }

        TreeNode root = new TreeNode();
        int rootVal = Integer.parseInt(String.valueOf(data[end]));
        root.val = rootVal;
        --end;
        root.right = dese_postorder(data);
        --end;
        root.left = dese_postorder(data);

        return root;
    }

    /**
     *      1
     *     /\
     *    2  3
     *      /\
     *     4  5
     * #,#,2,#,#,4,#,#,5,3,1
     * @param args
     */
    public static void main(String[] args) {
        Q297 q = new Q297();
        TreeNode root = q.generateTree(new int[]{1, 2, 3, 4, 5}, new int[]{2, 1, 4, 3, 5});
        String serial = q.serialize_postorder(root);
        System.out.println(serial);
        TreeNode newTree = q.deserialize_postorder(serial);
        System.out.println();

    }
}
