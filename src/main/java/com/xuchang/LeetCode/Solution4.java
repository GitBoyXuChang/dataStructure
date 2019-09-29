package com.xuchang.LeetCode;

import java.util.*;
/**
 * @author xuchang
 * @create 2019/9/29 15:41
 * 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 * 示例 1:
 * 输入: 121
 * 输出: true
 * 示例 2:
 * 输入: -121
 * 输出: false
 * 解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
 * 示例 3:
 * 输入: 10
 * 输出: false
 * 解释: 从右向左读, 为 01 。因此它不是一个回文数。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/palindrome-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
class Solution4 {
    public boolean isPalindrome(int x) {
        boolean flag = true;
        if(x<0){
            flag = false;
        }
        Integer length = getNum(x);
        int[] arr = new int[length];

        for(int i=0;i<length;i++){
            if(length==1){
                arr[0] = x;
                continue;
            }
            Integer num = (int)(x/Math.pow(10,length-1-i));
            arr[i] = num%10;
        }
        for(int j=0;j<length/2;j++){
            if(arr[j]!=arr[length-1-j]){
                flag = false;
            }
        }
        return flag;

    }
    public Integer getNum(Integer i){
        Integer count = 1;
        while(i>=10){
            count++;
            i = (i/10);
        }
        return count;
    }
}
