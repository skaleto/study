package com.skaleto.things.leet;

import java.util.Arrays;

/**
 * 给你一个数组，将数组中的元素向右轮转 k 个位置，其中 k 是非负数。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [1,2,3,4,5,6,7], k = 3
 * 输出: [5,6,7,1,2,3,4]
 * 解释:
 * 向右轮转 1 步: [7,1,2,3,4,5,6]
 * 向右轮转 2 步: [6,7,1,2,3,4,5]
 * 向右轮转 3 步: [5,6,7,1,2,3,4]
 * 示例 2:
 * <p>
 * 输入：nums = [-1,-100,3,99], k = 2
 * 输出：[3,99,-1,-100]
 * 解释:
 * 向右轮转 1 步: [99,-1,-100,3]
 * 向右轮转 2 步: [3,99,-1,-100]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/rotate-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Q189 {

    public static void main(String[] args) {
        Q189 q = new Q189();
        int[] nums = new int[]{1, 2, 3, 4, 5, 6, 7};
        q.rotate_20211217_2(nums, 3);
        System.out.println(Arrays.toString(nums));
    }

    /**
     * O(1)的空间复杂度
     * <p>
     * 1,2,3,4,5,6,7 -> 7,6,5,4,3,2,1 -> 5,6,7,1,2,3,4
     *
     * @param nums
     * @param k
     */
    public void rotate_20211217_2(int[] nums, int k) {
        int length = nums.length;
        int rot = k % length;

        if (rot == 0) {
            return;
        }

        //反转整个串
        reverse(nums, 0, nums.length - 1);

        //反转前面的串
        reverse(nums, 0, rot - 1);

        //反转后面的串
        reverse(nums, rot, nums.length - 1);

    }

    public void reverse(int[] nums, int start, int end) {
        int i=start;
        int j=end;
        while(i<j){
            int tmp=nums[i];
            nums[i]=nums[j];
            nums[j]=tmp;
            i++;
            j--;
        }
    }


    /**
     * O(N)的空间复杂度
     *
     * @param nums
     * @param k
     */
    public void rotate_20211217_1(int[] nums, int k) {
        int length = nums.length;
        int rot = k % length;

        if (rot == 0) {
            return;
        }

        //把前面一部分数组拷贝出来
        int[] tmp = Arrays.copyOfRange(nums, 0, nums.length - rot);
        //循环把后面一部分数据拷贝到前面
        for (int i = 0; i < rot; i++) {
            nums[i] = nums[nums.length - rot + i];
        }
        System.arraycopy(tmp, 0, nums, rot, tmp.length);

    }

//    public void rotate(int[] nums, int k) {
//        int split=k%nums.length;
//        if(split==0){
//            return;
//        }
//
//        reverse(nums,0,nums.length-1);
//        reverse(nums,0,split-1);
//        reverse(nums,split,nums.length-1);
//    }
//
//    public void reverse(int[] nums,int start,int end){
//        for(int i=start,j=end;i<j;i++,j--){
//            int temp=nums[i];
//            nums[i]=nums[j];
//            nums[j]=temp;
//        }
//    }

}
