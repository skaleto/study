package com.skaleto.things.leet;

import java.util.Stack;

/**
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 * <p>
 * <p>
 * <p>
 * 上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。 
 * <p>
 * 示例:
 * <p>
 * 输入: [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出: 6
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/trapping-rain-water
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Q42 {

    /**
     * 单调栈法
     */
    Stack<Integer> tmp = new Stack<>();

    public int trap1(int[] height) {
        int res = 0;
        for (int i = 0; i < height.length; i++) {
            while (!tmp.isEmpty() && height[i] > height[tmp.peek()]) {
                int cur = tmp.pop();
                if (!tmp.isEmpty()) {
                    int distance = i - tmp.peek() - 1;
                    int diff = Math.min(height[i], height[tmp.peek()]) - height[cur];
                    res += distance * diff;
                }
            }
            tmp.push(i);

        }
        return res;

    }

    /**
     * 双指针法，当左边小于右边时，结果由左边max决定
     * ps.这个方法太巧妙了不由得发出感叹
     *
     * @param height
     * @return
     */
    public int trap2(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int res = 0;
        int leftMax = 0;
        int rightMax = 0;
        while (left < right) {
            if (height[left] < height[right]) {
                if (height[left] >= leftMax) {
                    leftMax = height[left];
                } else {
                    res += leftMax - height[left];
                }
                left++;
            } else {
                if (height[right] >= rightMax) {
                    rightMax = height[right];
                } else {
                    res += rightMax - height[right];
                }
                right--;
            }
        }

        return res;
    }

    public static void main(String[] args) {
        Q42 q = new Q42();
        System.out.println(q.trap2(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));

    }
}
