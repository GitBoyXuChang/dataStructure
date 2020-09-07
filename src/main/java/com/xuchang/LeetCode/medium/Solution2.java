package com.xuchang.LeetCode.medium;

import com.xuchang.ds.list.List;

/**
 * @program: dataStructure
 * @description:
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序的方式存储的，
 * 并且它们的每个节点只能存储 一位 数字。
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * 示例：  输入：(2 -> 4 -> 3) + (5 -> 6 -> 4) 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807  来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-two-numbers 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: XUCHANG
 * @time: 2019/12/17 14:10
 */

public class Solution2 {

     public ListNode addTwoNumbers(ListNode oneNode, ListNode twoNode) {

        ListNode listNode= new ListNode();
        ListNode p = oneNode,q = twoNode,current = listNode;
        int carry = 0;
        while (p != null || q != null){
            int x = p == null ?  0 : p.val;
            int y = q == null ?  0 : q.val;
            int sum  = x + y + carry;
            carry = sum / 10;
            sum = sum % 10;
            current.next = new ListNode(sum);
            current = current.next;
            if (p != null){
                p = p.next;
            }
            if (q != null){
                q = q.next;
            }

        }
        if (carry == 1 ){
            current.next = new ListNode(carry);
        }

        return listNode.next;
    }
    public static void main(String[] args) {
        Solution2 solution = new Solution2();
        int num1[] = {2,4,7};
        int num2[] = {2,5,7};
        ListNode one = solution.getNumNode(num1);
        ListNode two = solution.getNumNode(num2);
        ListNode result = solution.addTwoNumbers(one,two);
        System.out.println(result);
    }
    private ListNode getNumNode(int[] nums){
        if (nums.length == 0){return null;}
        ListNode root = new ListNode(nums[0]);
        ListNode listNode = root;
        for (int i=1; i<nums.length ;i++){
            ListNode tempNode = new ListNode(nums[i]);
            listNode.next = tempNode;
            listNode = tempNode;
        }

        return root;
    }

}


class ListNode{
    int val;
    ListNode next;

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(ListNode next) {
        this.next = next;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public ListNode() {
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public ListNode getNext() {
        return next;
    }

    public void setNext(ListNode next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "ListNode{" +
                "val=" + val +
                ", next=" + next +
                '}';
    }
}
