package com.skaleto.things.leet;

import java.util.*;

public class Q47 {

//    public List<List<Integer>> permuteUnique(int[] nums) {
//        List<List<Integer>> result=new ArrayList<>();
//
//        if(nums==null){
//            return result;
//        }
//
//        Arrays.sort(nums);
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
//            if(helper[i]==1){
//
//            }
//            if(i>0&&nums[i]==nums[i-1]&&helper[i-1]!=1){
//                continue;
//            }
//            if(helper[i]!=1){
//                helper[i]=1;
//                path.addLast(nums[i]);
//                dfs(nums, helper, path, result);
//                path.removeLast();
//                helper[i]=0;
//            }
//        }
//    }

    public List<List<Integer>> permuteUnique_20211226(int[] nums) {

        List<List<Integer>> result=new ArrayList<>();

        Arrays.sort(nums);

        Deque<Integer> path=new ArrayDeque<>();

        //用一个长度和原数组相同的辅助数组来标识某个位置是否可以访问
        int[] helper=new int[nums.length];

        dfs(nums,helper,path,result);

        return result;
    }

    public void dfs(int[] nums, int[] helper, Deque<Integer> path, List<List<Integer>> result){
        if(path.size()==nums.length){
            result.add(new ArrayList<>(path));
            return;
        }

        for(int i=0;i<nums.length;i++){
            if(helper[i]==1){
                continue;
            }

            //如果某一位数字和前一位数字相同，那么
            //    如果前一位helper记录为0（helper[i-1]==0），说明前一位数字没有用过，这一位不能用
            //        （因为这一位一旦用了，就会在前一位数字被用到的时候产生冲突，逻辑上和前一位就说不清楚了）
            //    如果前一位helper记录不为0，说明前一位数字已经用过，这一位可以接着用，逻辑上表示的是不同的数字
            if(i>0 && nums[i]==nums[i-1] && helper[i-1]==0){
                continue;
            }

            path.addLast(nums[i]);
            helper[i]=1;

            dfs(nums, helper, path, result);

            path.pollLast();
            helper[i]=0;
        }


    }

    public static void main(String[] args) {
        Q47 solution=new Q47();
        System.out.println(solution.permuteUnique_20211226(new int[]{1,1,2}));
    }
}
