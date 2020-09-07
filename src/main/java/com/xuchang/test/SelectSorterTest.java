package com.xuchang.test;

import com.xuchang.algo.sort.SortUtils;
import com.xuchang.algo.sort.Sorter;

import java.util.Arrays;

/**
 * @project: dataStructure
 * @description: 选择排序
 * @author: XUCHANG
 * @create: 2020-09-07 23:11
 */
public class SelectSorterTest  implements Sorter {
    @Override
    public void sort(int[] arr) {
        if(arr == null || arr.length <= 1) {
            return;
        }
        for(int i=0;i<arr.length;i++){
            int min = i;
            for(int j=i+1;j<arr.length;j++){
                if (arr[min]>arr[j]){
                    min = j;
                }
            }
            int temp = arr[i];
            arr[i] = arr[min];
            arr[min] = temp;
        }

    }

    public static void main(String[] args) {
        int[] arrayToSort = SortUtils.buildRandomIntArray(10);
        SortUtils.printArray(arrayToSort);
        int[] arrayClone = Arrays.copyOf(arrayToSort, arrayToSort.length);
        Sorter sorter = new SelectSorterTest();
        sorter.sort(arrayToSort);
        SortUtils.printArray(arrayToSort);
        Arrays.sort(arrayClone,0,9);
        SortUtils.printArray(arrayClone);
    }
}
