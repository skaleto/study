package com.skaleto.things.leet;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Q46 {

//    public List<List<Integer>> permute(int[] nums) {
//        List<List<Integer>> result=new ArrayList<>();
//
//        if(nums==null){
//            return result;
//        }
//
//        Deque<Integer> path=new LinkedList<>();
//        int[] hepler=new int[nums.length];
//        dfs(nums,hepler,path,result);
//
//        return result;
//
//    }
//
//    public void dfs(int[] nums, int[] helper, Deque<Integer> path, List<List<Integer>> result){
//        if(path.size()==nums.length){
//            result.add(new ArrayList<>(path));
//            return;
//        }
//
//        for(int i=0;i<nums.length;i++){
//            if(helper[i]!=1){
//                helper[i]=1;
//                path.addLast(nums[i]);
//                dfs(nums, helper, path, result);
//                path.removeLast();
//                helper[i]=0;
//            }
//        }
//    }
public List<List<Integer>> permute(int[] nums) {
    List<List<Integer>> result=new ArrayList<>();

    if(nums == null || nums.length==0){
        return result;
    }

    Deque<Integer> path=new ArrayDeque<>();
    dfs(nums, path, result);

    return result;
}

    public void dfs( int[] nums, Deque<Integer> path, List<List<Integer>> result){
        if(path.size()==nums.length){
            result.add(new ArrayList<>(path));
            return;
        }

        for(int i=0;i<nums.length;i++){
            if(path.contains(nums[i])){
                continue;
            }
            path.addLast(nums[i]);
            dfs(nums, path, result);
            path.pollLast();
        }

    }

    public static void main(String[] args) {
        Q46 q=new Q46();
        System.out.println(q.permute(new int[]{1,2,3}));
    }
}
