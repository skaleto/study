package com.skaleto.things.leet;

import java.util.Arrays;

public class Q912 {

    int[] tmp;

    /**
     * Your runtime beats 88.81 % of java submissions
     * Your memory usage beats 42.2 % of java submissions (50.6 MB)
     *
     * @param nums
     * @return
     */
    public int[] sortArray(int[] nums) {
        tmp = new int[nums.length];
        sort(nums, 0, nums.length - 1);
        return nums;
    }

    public void sort(int[] nums, int lo, int hi) {
        if (lo >= hi) {
            return;
        }

        int mid = (lo + hi) / 2;
        sort(nums, lo, mid);
        sort(nums, mid + 1, hi);

        merge(nums, lo, mid, hi);
    }

    public void merge(int[] nums, int lo, int mid, int hi) {
        for (int i = lo; i <= hi; i++) {
            tmp[i] = nums[i];
        }

        int first = lo;
        int second = mid + 1;
        int k = lo;
        while (first <= mid && second <= hi) {
            if (tmp[first] <= tmp[second]) {
                nums[k++] = tmp[first++];
            } else {
                nums[k++] = tmp[second++];
            }
        }

        while (first <= mid) {
            nums[k++] = tmp[first++];
        }

        while (second <= hi) {
            nums[k++] = tmp[second++];
        }

    }


    public static void main(String[] args) {
        Q912 q = new Q912();
        System.out.println(Arrays.toString(q.sortArray(new int[]{5, 2, 3, 1})));
    }
}
