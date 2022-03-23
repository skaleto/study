package com.skaleto.things.leet;

public class Q26 {

    public static void main(String[] args) {
        Q26 q=new Q26();
        System.out.println(q.removeDuplicates(new int[]{1,1,2}));
    }

    public int removeDuplicates(int[] nums) {
        //注意是有序数组，所以考虑双指针

        int i=0;
        int j=0;
        while(j<nums.length){
            while(j<nums.length && nums[i]==nums[j]){
                j++;
            }

            if(j<nums.length){
                i++;
                nums[i]=nums[j];
            }

        }

        return i+1;

    }

//    public int removeDuplicates(int[] nums) {
//        int left=0;
//        int right=1;
//        while(right<nums.length){
//            if(nums[left]!=nums[right]){
//                left++;
//                nums[left]=nums[right];
//            }
//            right++;
//        }
//        return left+1;
//    }
}
