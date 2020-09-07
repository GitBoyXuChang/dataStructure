package com.xuchang.LeetCode;

/**
 * @program: dataStructure
 * @description:
 * @author: XUCHANG
 * @time: 2019/11/5 16:47
 */
public class User1 extends User2 {
    public User1() {
        System.out.println("1");
    }
    public void method(){
        System.out.println("22222");
    }

    public static void main(String[] args) {
        User1 user1 = new User1();
        user1.method();
    }
}
