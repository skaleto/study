package com.skaleto.things.leet;

import java.util.Arrays;

public class Sort {

    public static void main(String[] args) {
        int[] data = new int[]{3, 15, 1, 4, 23, 8, 4, 10, 76, 35, 26, 93, 43, 16};
//        quickSort(data, 0, data.length - 1);
        mergeSort(data, 0, data.length - 1);

        System.out.println(Arrays.toString(data));
    }

    public static void quickSort(int[] data, int begin, int end) {
        if (begin > end) {
            return;
        }
        int i = begin;
        int j = end;
        //记录一下第一个数，作为基准数，此时空出来的位置也就是i的位置
        int pivot = data[begin];
        while (i < j) {
            //从后往前找小于等于pivot的数
            while (i < j && data[j] >= pivot) {
                j--;
            }
            //将它放到空出来的的位置i处，此时j处位置空了出来
            data[i] = data[j];
            //从前往后找大于等于pivot的数
            while (i < j && data[i] <= pivot) {
                i++;
            }
            //将它放到空出来的j处的位置，此时i处位置空了出来
            data[j] = data[i];
        }
        //最后i==j是一个空出来的位置，把之前记录的pivot放到这里
        data[i] = pivot;
        quickSort(data, begin, i - 1);
        quickSort(data, i + 1, end);
    }


    public static void mergeSort(int[] data, int begin, int end) {
        //注意这里假如是begin>end很可能永远结束不了，出现stackoverflow
        if (begin >= end) {
            return;
        }
        int mid = (begin + end) / 2;
        mergeSort(data, begin, mid);
        mergeSort(data, mid + 1, end);
        merge(data, begin, mid, end);
    }

    public static void merge(int[] data, int begin, int mid, int end) {
        int[] temp = new int[end - begin + 1];
        int i = begin;
        int j = mid + 1;
        int t = 0;
        while (i <= mid && j <= end) {
            if (data[i] <= data[j]) {
                temp[t++] = data[i++];
            } else {
                temp[t++] = data[j++];
            }
        }

        while (i <= mid) {
            temp[t++] = data[i++];
        }

        while (j <= end) {
            temp[t++] = data[j++];
        }

        System.arraycopy(temp, 0, data, begin, end - begin + 1);
    }


}
