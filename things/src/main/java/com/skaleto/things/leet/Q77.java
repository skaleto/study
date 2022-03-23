package com.skaleto.things.leet;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Q77 {

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        if (k <= 0 || n < k) {
            return res;
        }
        Deque<Integer> path = new ArrayDeque<>();
        dfs(n, k, 1, path, res);
        return res;
    }

    private void dfs(int n, int k, int begin, Deque<Integer> path, List<List<Integer>> res) {
        if (path.size() == k) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = begin; i+(k-path.size())-1 <= n; i++) {
            path.addLast(i);
            System.out.println("递归之前 => " + path);
            dfs(n, k, i + 1, path, res);
            path.removeLast();
            System.out.println("递归之后 => " + path);
        }
    }

    public List<List<Integer>> combine_20211224(int n, int k) {

        List<List<Integer>> result=new ArrayList<>();

        if(n==0 || k==0){
            return result;
        }

        Deque<Integer> path=new ArrayDeque<>();
        dfs_20211224(1, k, n, path, result);

        return result;
    }

    public void dfs_20211224(int start, int k, int n, Deque<Integer> path, List<List<Integer>> result){

        if(path.size()==k){
            result.add(new ArrayList<>(path));
            return;
        }

        for(int i=start;i<=n;i++){
            path.addLast(i);
            dfs_20211224(i+1, k, n, path, result);
            path.pollLast();
        }

    }

    public static void main(String[] args) {
        Q77 solution = new Q77();
        int n = 4;
        int k = 2;
        List<List<Integer>> res = solution.combine(n, k);
        System.out.println(res);
    }
}
