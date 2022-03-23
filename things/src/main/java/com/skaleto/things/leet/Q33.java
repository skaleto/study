package com.skaleto.things.leet;

public class Q33 {
    public static void main(String[] args) {
        Q33 q = new Q33();
        System.out.println(q.search(new int[]{3, 1}, 1));
    }

    public int search(int[] nums, int target) {

        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                return mid;
            }

            if (nums[left] == nums[mid]) {
                //此时说明只有两个元素了，判断一下right即可
                if (nums[right] == target) {
                    return right;
                }
            }

            if (nums[left] < nums[mid]) {
                //此时左半部分有序
                if (nums[left] <= target && nums[mid] >= target) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                //此时右半部分有序
                if (nums[mid] <= target && nums[right] >= target) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }

        return -1;

    }
}
