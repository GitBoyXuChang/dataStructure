package com.xuchang.ds.queue;

public interface Queue<E> {

    //返回队列元素数量
    public int size();

    //队列是否为空
    public boolean empty();

    //入队
    public void enqueue(E e);

    //出队
    public E dequeue();

    //查看队首元素
    public E peek();
}
