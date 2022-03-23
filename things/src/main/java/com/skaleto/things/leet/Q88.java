package com.skaleto.things.leet;

import java.util.Arrays;

public class Q88 {

    public static void main(String[] args) {
        Q88 q=new Q88();
        int[] nums1=new int[]{1,2,3,0,0,0};
        int[] nums2=new int[]{2,5,6};
        q.merge_20211218(nums1,3,nums2,3);
        System.out.println(Arrays.toString(nums1));

    }

    public void merge_20211218(int[] nums1, int m, int[] nums2, int n) {

        int[] result=new int[m+n];

        int i=0;
        int j=0;
        int k=0;
        while(i<m && j<n){
            while(i<m && j<n && nums1[i]<=nums2[j]){
                result[k++]=nums1[i++];
            }
            while(i<m && j<n && nums1[i]>nums2[j]){
                result[k++]=nums2[j++];
            }
        }

        if(i==m && j<n){
            for(int jj=j;jj<n;jj++){
                result[k++]=nums2[jj];
            }
        }
        if(j==n && i<m){
            for(int ii=i;ii<m;ii++){
                result[k++]=nums1[ii];
            }
        }

        System.arraycopy(result, 0, nums1, 0, m+n);

    }

//    public void merge(int[] nums1, int m, int[] nums2, int n) {
//        int[] result=new int[m+n];
//        int i=0;
//        int j=0;
//
//        int k=0;
//        while(i<m||j<n){
//            if(i==m&&j<n){
//                while(j<n){
//                    result[k++]=nums2[j++];
//                }
//                break;
//            }
//            if(j==n&&i<m){
//                while(i<m){
//                    result[k++]=nums1[i++];
//                }
//                break;
//            }
//            if(nums1[i]<=nums2[j]){
//                result[k]=nums1[i];
//                i++;
//            }else{
//                result[k]=nums2[j];
//                j++;
//            }
//            k++;
//        }
//
//        System.arraycopy(result,0,nums1,0,m+n);
//    }
//
//    public void merge_2(int[] nums1, int m, int[] nums2, int n) {
//        int i=m-1;
//        int j=n-1;
//        int k=m+n-1;
//        while(j>=0){
//            if(i<0||nums1[i]<=nums2[j]){
//                nums1[k--]=nums2[j--];
//            }else{
//                nums1[k--]=nums1[i--];
//            }
//        }
//    }
}
