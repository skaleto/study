package com.skaleto.things.leet;

import java.util.*;

/**
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
 * <p>
 * 注意：答案中不可以包含重复的三元组。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [-1,0,1,2,-1,-4]
 * 输出：[[-1,-1,2],[-1,0,1]]
 * 示例 2：
 * <p>
 * 输入：nums = []
 * 输出：[]
 * 示例 3：
 * <p>
 * 输入：nums = [0]
 * 输出：[]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/3sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Q15 {

    /**
     * 1.特判，对于数组长度 n，如果数组为 null 或者数组长度小于 3，返回 []。
     * 2.对数组进行排序。
     * 3.遍历排序后数组：
     * （1）若 nums[i]>0：因为已经排序好，所以后面不可能有三个数加和等于 00，直接返回结果。
     * （2）对于重复元素：跳过，避免出现重复解
     * （3）令左指针 L=i+1，右指针 R=n-1，当 L<R 时，执行循环：
     * 当 nums[i]+nums[L]+nums[R]==0，执行循环，判断左界和右界是否和下一位置重复，去除重复解。并同时将 L,R 移到下一位置，寻找新的解
     * 若和大于 0，说明 nums[R] 太大，RR 左移
     * 若和小于 0，说明 nums[L] 太小，LL 右移
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum_20211110(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            if (nums[i] > 0) {
                break;
            }
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int j = i + 1;
            int k = nums.length - 1;
            while (j < k) {
                int res = nums[i] + nums[j] + nums[k];
                if (res == 0) {
                    while (j < k && nums[j] == nums[j + 1]) {
                        j++;
                    }
                    while (j < k && nums[k] == nums[k - 1]) {
                        k--;
                    }
                    result.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    j++;
                    k--;
                }
                if (res > 0) {
                    k--;
                }
                if (res < 0) {
                    j++;
                }
            }
        }
        return result;
    }

    public List<List<Integer>> threeSum_20211113(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int j = i + 1;
            int k = nums.length - 1;
            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                if (sum == 0) {
                    result.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    while (j < k && nums[j] == nums[j + 1]) {
                        j++;
                    }
                    while (j < k && nums[k] == nums[k - 1]) {
                        k--;
                    }
                    j++;
                    k--;
                }

                if (sum > 0) {
                    if (nums[i] > 0) {
                        break;
                    }
                    k--;
                }
                if (sum < 0) {
                    j++;
                }
            }
        }

        return result;
    }

    /**
     * 哈希法
     * Your runtime beats 10.2 % of java submissions
     * Your memory usage beats 36.21 % of java submissions (45.3 MB)
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum_20220311(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {
            // 因为数组已经排序了，那么只要某个数字大于0，后面的数字就不可能和他的和等于0
            if (nums[i] > 0) {
                continue;
            }
            // 遇到相等的数字可以跳过，因为前一次的计算结果以及包含了
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            Set<Integer> tmp = new HashSet<>();
            for (int j = i + 1; j < nums.length; j++) {
                // 如果连续三个元素j,j-1,j-2相等,则只有前两个元素是有用的
                // 因为假如j和j-1能和目标数字和为0，那么j-2的数字是重复的；假如j和j-1不能和目标数字组成0，那么j-2也不能
                if (j > i + 2 && nums[j] == nums[j - 1] && nums[j - 1] == nums[j - 2]) {
                    continue;
                }
                int target = -(nums[i] + nums[j]);
                if (tmp.contains(target)) {
                    result.add(Arrays.asList(nums[i], nums[j], target));
                    tmp.remove(target);
                } else {
                    tmp.add(nums[j]);
                }
            }
        }
        return result;
    }


    /**
     * 双指针法
     * Your runtime beats 98.65 % of java submissions
     * Your memory usage beats 41.57 % of java submissions (45.2 MB)
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum_20220311_2(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                continue;
            }
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int target = -(nums[i]);
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                int sum = nums[left] + nums[right];
                if (sum == target) {
                    result.add(Arrays.asList(-sum, nums[left], nums[right]));
                    while (left < right && nums[left + 1] == nums[left]) {
                        left++;
                    }
                    while (left < right && nums[right - 1] == nums[right]) {
                        right--;
                    }
                    //前两个循环只是把和当前数字相等的排掉了，left和right的指针还是在当前数字相等的位置
                    left++;
                    right--;
                }
                if (sum < target) {
                    left++;
                }
                if (sum > target) {
                    right--;
                }
            }
        }

        return result;
    }


    public static void main(String[] args) {
        Q15 q = new Q15();
//        System.out.println(q.threeSum(new int[]{-1, 0, 1, 2, -1, -4}));
        System.out.println(q.threeSum_20220311_2(new int[]{0, 2, 2, 3, 0, 1, 2, 3, -1, -4, 2}));
    }


}
