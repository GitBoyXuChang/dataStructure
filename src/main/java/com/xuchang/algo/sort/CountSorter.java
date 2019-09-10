package com.xuchang.algo.sort;

import java.util.Arrays;


public class CountSorter  implements  Sorter{

    @Override
    public void sort(int[] arr) {

        int max = findMax(arr);
        int[] count = new int[max+1];
        count(arr, count);
        totalCount(count);
        rebuildArrayStable(arr, count);
    }

    int findMax(int[] arr) {
     int max = Integer.MIN_VALUE;

     for(int i: arr) {
         if(i > max ){
             max = i;
         }
     }

     return  max;
    }

    void count(int[] arr, int count[]) {
        for(int i: arr) {
            count[i]++;
        }
    }

    void totalCount(int[] count){
        int sum = 0;
        for(int i=0; i<count.length; i++) {
            sum += count[i];
            count[i] = sum;
        }
    }


    //O(n + k)
    void rebuildArrayStable(int[] arr, int totalCount[]) {
        int[] sortedArr = new int[arr.length];

        for(int i=arr.length-1; i>=0; i--) {
            sortedArr[totalCount[arr[i]]-1] = arr[i];
            totalCount[arr[i]]--;
        }

        for(int i=0; i<arr.length; i++) {
            arr[i] = sortedArr[i];
        }
    }

    //O(k) (k=max)
    void rebuildArray(int[] arr, int count[]) {
        int index = 0;
        for(int j=0; j<count.length; j++) {
            for(int i=0; i<count[j]; i++) {
                arr[index++] = j;
            }
        }
    }

    public static void main(String[] args) {
        int[] arrayToSort = {2, 5, 8, 9, 8, 9, 7, 10, 100, 99, 98, 2, 8, 5, 100};
        SortUtils.printArray(arrayToSort);

        int[] arrayClone = Arrays.copyOf(arrayToSort, arrayToSort.length);
        Arrays.sort(arrayClone);

        Sorter sorter = new CountSorter();
        sorter.sort(arrayToSort);
        SortUtils.printArray(arrayToSort);

        if(SortUtils.isEquals(arrayClone, arrayToSort)){
            System.out.println("The two arrays is equal ...");
        }else {
            System.out.println("The two arrays is not equal ...");
        }
    }
}
