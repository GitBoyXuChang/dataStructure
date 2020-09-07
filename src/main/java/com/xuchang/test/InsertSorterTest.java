package com.xuchang.test;

import com.xuchang.algo.sort.SortUtils;
import com.xuchang.algo.sort.Sorter;

/**
 * @project: dataStructure
 * @description: 插入排序测试
 * @author: XUCHANG
 * @create: 2020-09-07 22:49
 */
public class InsertSorterTest implements Sorter {
    @Override
    public void sort(int[] arr) {
        if(arr == null || arr.length <= 1) {
            return;
        }
        for (int i=1;i< arr.length;i++){
            int element = arr[i];
            int j = i-1;
            while (j>=0 && arr[j]>element){
                arr[j+1] = arr[j];
                j--;
            }
            arr[j+1] = element;
        }
    }

    public static void main(String[] args) {
        int[] arrayToSort = SortUtils.buildRandomIntArray(10);
        SortUtils.printArray(arrayToSort);

        Sorter sorter = new InsertSorterTest();
        sorter.sort(arrayToSort);
        SortUtils.printArray(arrayToSort);
    }
}
