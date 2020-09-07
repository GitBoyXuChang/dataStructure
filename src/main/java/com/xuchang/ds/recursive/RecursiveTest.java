package com.xuchang.ds.recursive;

/**
 * @program: dataStructure
 * @description: java中，一个方法调用它自身，被称为方法递归。方法递归中包含了一种隐藏式的循环。它会重复执行某段代码，而且不需要循环语句控制。
 * 例如有如下数学题。已知一个数列：f（0） =1 、f（1）=4、f（n+2） =2*f（n+1) + f(n)，其中n是大于0的整数，
 * 求f（10）的值。这题中函数中 ————————————————
 * @author: XUCHANG
 * @time: 2019/10/20 23:12
 */
public class RecursiveTest {
    public static int method(int n){
        if (n == 0){
            return 1;
        }else if(n==1){
            return 4;
        }else {
            return 2*method(n-1)+method(n-2);
        }
    }

    public static void main(String[] args) {
        System.out.println(method(5));
    }
}
