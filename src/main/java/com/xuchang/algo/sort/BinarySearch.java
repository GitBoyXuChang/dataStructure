package com.xuchang.algo.sort;


public class BinarySearch {

    //T(n) = T(n/2) + O(1) => O(lgn)
    public static int bsearch(int[] arr, int start, int end, int targetValue) {
        if (start > end) {
            return -1;
        }

        //int mid = (start + end)/2; //(start+end)比较大时可能越界溢出
        int mid = start + ((end - start) >> 1);

        if (arr[mid] == targetValue) {
            return mid;
        } else if (arr[mid] < targetValue) {
            return bsearch(arr, mid + 1, end, targetValue);
        } else {
            return bsearch(arr, start, mid - 1, targetValue);
        }

    }

    public static int bsearchIterative(int[] arr, int start, int end, int targetValue) {
        if (start > end) {
            return -1;
        }

        while (start <= end) {
            int mid = start + ((end - start) >> 1);

            if (arr[mid] == targetValue) {
                return mid;
            } else if (arr[mid] < targetValue) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        return -1;
    }

    public static int bsearchFirst(int[] arr, int start, int end, int targetValue) {
        if (start > end) {
            return -1;
        }

        int result = -1;
        while (start <= end) {
            int mid = start + ((end - start) >> 1);

            if (arr[mid] >= targetValue) {
                end = mid - 1;

                if (arr[mid] == targetValue) {
                    result = mid;
                }
            } else {
                start = mid + 1;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {1, 1, 2, 2, 2, 2, 3, 4, 4, 4, 8, 8, 9, 9, 9, 13, 15, 15};


        System.out.println(bsearch(arr, 0, arr.length, 2));
        System.out.println(bsearchIterative(arr, 0, arr.length, 2));
        System.out.println(bsearchFirst(arr, 0, arr.length, 2));

    }

}
