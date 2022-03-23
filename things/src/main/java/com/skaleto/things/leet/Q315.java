package com.skaleto.things.leet;

import java.util.ArrayList;
import java.util.List;

public class Q315 {

    public class Data {
        public int val;
        public int id;

        public Data(int val, int id) {
            this.val = val;
            this.id = id;
        }
    }

    public Data[] data;
    public Data[] tmp;
    public int[] count;

    public List<Integer> countSmaller(int[] nums) {
        //把数字和索引位置存起来，因为后续的归并排序会破坏数字结构
        data = new Data[nums.length];
        tmp = new Data[nums.length];
        count = new int[nums.length];

        for (int i = 0; i < nums.length; i++) {
            data[i] = new Data(nums[i], i);
        }

        sort(data, 0, data.length - 1);

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < count.length; i++) {
            result.add(count[i]);
        }

        return result;
    }

    public void sort(Data[] data, int lo, int hi) {
        if (lo >= hi) {
            return;
        }

        int mid = (lo + hi) / 2;
        sort(data, lo, mid);
        sort(data, mid + 1, hi);

        merge(data, lo, mid, hi);
    }

    public void merge(Data[] data, int lo, int mid, int hi) {
        //把需要合并的两个部分的结果存起来
        for (int i = lo; i <= hi; i++) {
            tmp[i] = data[i];
        }

        int left = lo;
        int right = mid + 1;
        int cur = lo;
        while (left <= mid && right <= hi) {
            //此时左半部分数字小于等于右半部分，左半部分要填入结果，意味着tmp[left]比[mid+1,right)区间中的数字都要大
            if (tmp[left].val <= tmp[right].val) {
                count[tmp[left].id] += right - mid - 1;
                data[cur++] = tmp[left++];
            } else {
                data[cur++] = tmp[right++];
            }
        }

        //左半部分还有没填完的数字，说明右半部分所有数字都比当前的数字小
        while (left <= mid) {
            count[tmp[left].id] += right - mid - 1;
            data[cur++] = tmp[left++];
        }

        while (right <= hi) {
            data[cur++] = tmp[right++];
        }

    }
}
