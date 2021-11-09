package com.skaleto.things.leet;

public class Q283 {

    public void moveZeroes(int[] nums) {
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                int tmp = nums[j];
                nums[j] = nums[i];
                nums[i] = tmp;
                j++;
            }
        }
    }

    public void moveZeroes2(int[] nums) {
        //定义指针j，用于指向为0的地方
        int j = 0;
        //定义指针i用于遍历
        for (int i = 0; i < nums.length; i++) {
            //当数字为0的时候，仅i指针右移一位，j指针不动
            //当数字非0的时候，左右指针都会移动，且如果不是同一个位置，进行数据交换
            if (nums[i] != 0) {
                if (i != j) {
                    //这里的"交换"仅把右指针的值赋给左指针
                    nums[j] = nums[i];
                    //右指针的值变为0，因为j指针对应的数始终为0（存在0的情况下）
                    //要是一个数组都不为0，就根本不会进入这个条件判断，因为i和j始终都自增1，指向的是同一个数，直到遍历完
                    //产生差异的地方在于当遍历到一个数字为0的时候，右指针的位置还是会右移，而左指针不动，从而产生了偏差
                    nums[i] = 0;
                }
                j++;
            }
        }
    }
}
