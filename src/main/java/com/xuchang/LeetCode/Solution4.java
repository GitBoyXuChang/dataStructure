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
    /**
     * 解法二：进阶解法---数学解法
     * 通过取整和取余操作获取整数中对应的数字进行比较。
     * 举个例子：1221 这个数字。
     * 通过计算 1221 / 1000， 得首位1
     * 通过计算 1221 % 10， 可得末位 1
     * 进行比较
     * 再将 22 取出来继续比较
     * 动画描述
     * 作者：MisterBooo
     * 链接：https://leetcode-cn.com/problems/palindrome-number/solution/dong-hua-hui-wen-shu-de-san-chong-jie-fa-fa-jie-ch/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param x
     * @return
     */
    public boolean isPalindrome(int x) {
        //边界判断
        if (x < 0) {
            return false;
        }
        int div = 1;
        //
        while (x / div >= 10) {
            div *= 10;
        }
        while (x > 0) {
            int left = x / div;
            int right = x % 10;
            if (left != right) {
                return false;
            }
            x = (x % div) / 10;
            div /= 100;
        }
        return true;
    }

    /**
     * 获取这个数的每个数字存入到数组中，对数组的数组进行判断
     * @param x
     * @return
     */
    public boolean isPalindrome2(int x) {
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

    public static void main(String[] args) {
        Solution4 solution4 = new Solution4();
        solution4.isPalindrome(12211221);
    }
}
