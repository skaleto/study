package com.skaleto.things.leet;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class Q84 {

    public static int largestRectangleArea_20211220(int[] heights) {

        /**
         * 柱子围起来的面积由柱子两端小于它的柱子决定（注意两端，因此有对称性）
         * 考虑维护一个单调递增的栈，依次将数字放入栈中，每当遇到栈顶数字比当前数字小，那么就要开始出栈
         */

        Deque<Integer> mono=new ArrayDeque<>();

        int max=0;
        for(int i=0;i<heights.length;i++){
            //当栈顶元素小于当前值时，需要出栈
            //（注意等于的情况还不需要出栈，因为后面可能有延伸）
            //注意这里要用while，因为小于当前的元素可能有很多，需要一直出栈直到栈顶元素高小于等于当前高
            while(!mono.isEmpty() && heights[mono.peek()]>heights[i]){
                //取当前的height高
                int height=heights[mono.pop()];
                //把和当前高度相等的前面的数值全部弹出一起计算
                while(!mono.isEmpty() && heights[mono.peek()]==height){
                    mono.pop();
                }

                //此时的width需要根据是否到达了边界来不同考虑
                int width=0;
                if(mono.isEmpty()){
                    width=i;
                }else{
                    //栈顶元素索引的后面一位即长方形的边界
                    width=i-mono.peek()-1;
                }

                max=Math.max(max,width*height);
            }

            mono.push(i);

        }

        //假如遍历完栈中还有数据，表明最后一段是单调递增的数字，
        //那么直接出栈到最后一个数字，计算最后一个数字高度的面积即可
        while(!mono.isEmpty()){
            //需要不断更新height值，一直出栈直到栈顶元素高小于等于当前高
            int height=heights[mono.pop()];
            //  int height=heights[hIndex];
            while(!mono.isEmpty() && heights[mono.peek()]==height){
                mono.pop();
            }

            //此时的height是可以用来计算高度的height
            //此时的width需要根据是否到达了边界来不同考虑
            int width=0;
            if(mono.isEmpty()){
                width=heights.length;
            }else{
                width=heights.length-mono.peek()-1;
            }

            max=Math.max(max,width*height);
        }

        return max;

    }

    public static int largestRectangleArea(int[] heights) {
        Stack<Integer> mono = new Stack<>();

        int max = 0;
        for (int i = 0; i < heights.length; i++) {
            while (!mono.isEmpty() && heights[i] < heights[mono.peek()]) {
                int height = heights[mono.pop()];
                while (!mono.isEmpty() && height == heights[mono.peek()]) {
                    mono.pop();
                }
                int width;
                if (mono.isEmpty()) {
                    width = i;
                } else {
                    width = (i - mono.peek() - 1);
                }
                max = Math.max(max, width * height);
            }

            mono.push(i);

        }

        //假如遍历完栈中还有数据，表明最后一段是单调递增的数字，
        //那么直接出栈到最后一个数字，计算最后一个数字高度的面积即可
        int hIndex=0;
        while(!mono.isEmpty()){
            //需要不断更新height值，一直出栈直到栈顶元素高小于等于当前高
            hIndex=mono.pop();
            //  int height=heights[hIndex];
            while(!mono.isEmpty() && heights[mono.peek()]==heights[hIndex]){
                hIndex=mono.pop();
            }

            //此时的height是可以用来计算高度的height
            //此时的width需要根据是否到达了边界来不同考虑
            int width=0;
            if(mono.isEmpty()){
                width=heights.length;
            }else{
                width=heights.length-hIndex;
            }

            max=Math.max(max,width*heights[hIndex]);
        }


        return max;
    }

    public static void main(String[] args) {
        int[] input = {2, 1, 5, 6, 2, 3};
//        int[] input = {0, 2, 0};
        System.out.println(largestRectangleArea_20211220(input));
    }
}
