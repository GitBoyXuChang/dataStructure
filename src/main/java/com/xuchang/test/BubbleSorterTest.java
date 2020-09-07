package com.xuchang.test;

import com.xuchang.algo.sort.SortUtils;
import com.xuchang.algo.sort.Sorter;

/**
 * @project: dataStructure
 * @description: 冒泡排序测试
 * @author: XUCHANG
 * @create: 2020-09-07 22:29
 */
public class BubbleSorterTest implements Sorter {

    @Override
    public void sort(int[] arr) {
        if (arr == null || arr.length <= 1){
            return;
        }
        for(int i=0 ; i < arr.length ; i++){
            boolean flag = false;
            for (int j=0 ; j < arr.length-1-i ; j++){
                if (arr[j] > arr[j+1]){
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;

                    flag = true;
                }
            }
            if (!flag){return;}
        }
    }

    public static void main(String[] args) {
        int[] arrayToSort = SortUtils.buildRandomIntArray(10);
        SortUtils.printArray(arrayToSort);
        Sorter sorter = new BubbleSorterTest();

        sorter.sort(arrayToSort);
        SortUtils.printArray(arrayToSort);
    }
}
