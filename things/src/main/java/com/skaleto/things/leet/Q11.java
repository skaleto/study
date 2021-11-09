package com.skaleto.things.leet;

public class Q11 {

    public int maxArea1(int[] height) {
        int area = 0;
        for (int i = 0; i < height.length - 1; i++) {
            for (int j = i + 1; j < height.length; j++) {
                int newArea = (j - i) * Math.min(height[i], height[j]);
                area = Math.max(area, newArea);
            }
        }
        return area;
    }

    public int maxArea2(int[] height) {
        int area = 0;
        //定义左右指针i,j
        for (int i = 0, j = height.length - 1; i < j; ) {
            int newArea = (j - i) * Math.min(height[i], height[j]);
            area = Math.max(area, newArea);
            //哪边短哪边就往里
            if (height[i] <= height[j]) {
                i++;
            } else {
                j--;
            }
        }
        return area;
    }

    public int maxArea3(int[] height) {
        int i = 0, j = height.length - 1, res = 0;
        while (i < j) {
            res = height[i] < height[j] ?
                    Math.max(res, (j - i) * height[i++]) :
                    Math.max(res, (j - i) * height[j--]);
        }
        return res;
    }
}
