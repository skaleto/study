package com.skaleto.things.leet;

public class Q162 {

    /**
     * Your runtime beats 100 % of java submissions
     * Your memory usage beats 16.74 % of java submissions (40.8 MB)
     *
     * @param nums
     * @return
     */
    public int findPeakElement_1(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            boolean gtleft = i == 0 ? true : nums[i] > nums[i - 1];
            boolean gtright = i == nums.length - 1 ? true : nums[i] > nums[i + 1];
            if (gtleft && gtright) {
                return i;
            }
        }
        return 0;
    }

    /**
     * Your runtime beats 100 % of java submissions
     * Your memory usage beats 5.04 % of java submissions (41 MB)
     *
     * @param nums
     * @return
     */
    public int findPeakElement_2(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (nums[mid] > nums[mid + 1]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }


}
