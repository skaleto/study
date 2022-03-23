package com.skaleto.things.leet;

public class Q5 {

    /**
     * Your runtime beats 71.03 % of java submissions
     * Your memory usage beats 45.65 % of java submissions (41.4 MB)
     *
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        int start = 0;
        int end = 0;

        for (int i = 0; i < s.length(); i++) {
            int oddLen = centerExpand(s, i, i);
            int evenLen = centerExpand(s, i, i + 1);
            int max = Math.max(oddLen, evenLen);
            if (max > end - start) {
                //为什么start是i向左移动(max-1)/2 而 end是i向右移动(max/2)？
                //如果max为3，那么maxStart=i-1，maxEnd=i+1，没有问题
                //如果max为2，那么maxStart=i，maxEnd=i+1，也没有问题
                start = i - (max - 1) / 2;
                end = i + max / 2;
            }
        }

        //substring函数是左闭右开区间，所以end+1
        return s.substring(start, end + 1);

    }

    public int centerExpand(String s, int left, int right) {
        //从left和right开始向两边扩张，遇到相等的情况则继续扩张
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        //为什么是right-left-1 ? 因为在判断满足条件后left和right又向外扩张了一位
        //所以真实的前后坐标应该是left+1和right-1，那么长度=(right-1)-(left+1)+1=right-left-1
        return right - left - 1;
    }

}
