package com.skaleto.things.leet;

import java.util.*;

public class Q78 {

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result=new ArrayList<>();
        if(nums.length==0){
            return result;
        }
        Arrays.sort(nums);
        Deque<Integer> path=new LinkedList<>();
        dfs(result, path, nums, 0);
        return result;
    }

    public void dfs(List<List<Integer>> result, Deque<Integer> path, int[] nums, int start){
        result.add(new ArrayList<>(path));

        while(start<nums.length) {
            path.addLast(nums[start]);
            dfs(result, path, nums, start + 1);
            path.removeLast();
            start++;
        }
    }

    public static void main(String[] args) {
        Q78 solution=new Q78();
        solution.subsets(new int[]{1,2,3});
    }
}
