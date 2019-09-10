package com.xuchang.ds.stack;

public interface Stack<E>  {

    //判断栈是否为空
    public boolean empty();

    //返回栈中元素数量
    public int size();

    //压栈
    public void push(E item);

    //弹栈
    public E pop();

    //查看栈顶元素（不弹栈）
    public E peek();
}
