package com.skaleto.things.leet;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

public class Q429 {

    static class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    };

    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> result=new ArrayList<>();
        if(root==null){
            return result;
        }

        Queue<Node> queue=new LinkedBlockingQueue<>();

        queue.add(root);

        while(!queue.isEmpty()){
            List<Integer> level=new ArrayList<>();
            //记录当前队列有多少个元素，有多少个就遍历多少次
            int size=queue.size();
            for(int i=0;i<size;i++){
                //拿出头元素
                Node head=queue.remove();
                //头元素放到层数组中
                level.add(head.val);
                //头元素的子节点放到队列中
                queue.addAll(head.children);
            }
            result.add(level);
        }

        return result;
    }

    public List<List<Integer>> levelOrder_20211222(Node root) {
        List<List<Integer>> result=new ArrayList<>();
        if(root==null){
            return result;
        }

        List<Integer> first=new ArrayList<>();
        first.add(root.val);
        result.add(first);

        Deque<Node> queue=new ArrayDeque<>();
        queue.add(root);

        while(!queue.isEmpty()){
            int layerSize=queue.size();
            List<Integer> layer=new ArrayList<>();
            for(int i=0;i<layerSize;i++){
                Node n=queue.pollFirst();
                if(n.children==null){
                    continue;
                }
                for(Node k:n.children){
                    queue.addLast(k);
                    layer.add(k.val);
                }
            }
            if(!layer.isEmpty()){
                result.add(layer);
            }
        }

        return result;

    }
}
