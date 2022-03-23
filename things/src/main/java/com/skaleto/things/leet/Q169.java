package com.skaleto.things.leet;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Q169 {

    public int majorityElement(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        Map<Integer, Integer> result = new HashMap<>();
        for (int i : nums) {
            Integer tmp = result.get(i);
            if (tmp == null) {
                result.put(i, 1);
            } else {
                result.put(i, ++tmp);
                if (tmp > nums.length / 2) {
                    return i;
                }
            }
        }
        return 0;
    }

    /**
     * 排序后取中间值
     *
     * @param nums
     * @return
     */
    public int majorityElement_1(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }

    /**
     * 哈希表
     *
     * @param nums
     * @return
     */
    public int majorityElement_20211227(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.merge(nums[i], 1, Integer::sum);
            if (map.get(nums[i]) > nums.length / 2) {
                return nums[i];
            }
        }

        return 0;
    }

    /**
     * Boyer-Moore投票法
     * 我们维护一个候选众数 candidate 和它出现的次数 count。初始时 candidate 可以为任意值，count 为 0；
     * 我们遍历数组 nums 中的所有元素，对于每个元素 x，在判断 x 之前，如果 count 的值为 0，我们先将 x 的值赋予 candidate，随后我们判断 x：
     * 如果 x 与 candidate 相等，那么计数器 count 的值增加 1；
     * 如果 x 与 candidate 不等，那么计数器 count 的值减少 1。
     * 在遍历完成后，candidate 即为整个数组的众数。
     */
    public int majorityElement_20211227_2(int[] nums) {
        int candidate = 0;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (count == 0) {
                candidate = nums[i];
            }
            if (candidate == nums[i]) {
                count++;
            } else {
                count--;
            }
        }

        return candidate;
    }


}
