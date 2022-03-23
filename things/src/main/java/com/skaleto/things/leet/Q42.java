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

    public int trap(int[] height) {
        //如果遍历到的当前数字cur小于等于栈顶元素top，则入栈，否侧出栈
        //如果出栈,记为poped元素，则使用Math.min(top,cur)-poped作为高，cur-top-1作为长度
        //再循环比较新的栈顶元素是否小于等于当前元素，满足则继续出栈，计算面积，否则结束
        Stack<Integer> mono = new Stack<>();
        int area = 0;
        for (int i = 0; i < height.length; i++) {
            while (!mono.isEmpty() && height[mono.peek()] <= height[i]) {
                int poped = mono.pop();
                if (mono.isEmpty()) {
                    break;
                }
                int top = mono.peek();
                int h = Math.min(height[i], height[top]) - height[poped];
                area += h * (i - top - 1);
            }
            mono.push(i);
        }
        return area;
    }

    public int trap2(int[] height) {
        int left=0;
        int right=height.length-1;

        int leftMax=height[left];
        int rightMax=height[right];

        int area=0;
        while(left<right){
            leftMax=Math.max(leftMax,height[left]);
            rightMax=Math.max(rightMax,height[right]);

            if(leftMax<=rightMax){
                area+=leftMax-height[left];
                left++;
            }else{
                area+=rightMax-height[right];
                right--;
            }
        }
        return area;
    }

    public static void main(String[] args) {
        Q42 q = new Q42();
        System.out.println(q.trap(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));

    }
}
