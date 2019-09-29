package com.xuchang.LeetCode;

/**
 * @author xuchang
 * @create 2019/9/28 14:05
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
 * 示例:
 * 给定 nums = [2, 7, 11, 15], target = 9
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/two-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution1 {
    public int[] twoSum(int[] nums, int target) {
        int[] arr = new int[2];
        for(int i = 0 ;i<nums.length-1; i++){
            for(int j = i+1 ; j<nums.length;j++){
                int sum = nums[i] + nums[j];
                if(sum == target){
                    arr[0] = i;
                    arr[1] = j;
                    System.out.println(i+""+j);
                }
            }
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] nums = {2,2,8,74};
        Solution1 solution = new Solution1();
        solution.twoSum(nums,4);
    }
}
