package com.xuchang.ds.queue;


public class LinkedQueue<E> implements Queue<E> {


    private static class Node<E> {
        private E data;
        private Node<E> next;

        public Node(E val, Node<E> next) {
            this.data = val;
            this.next = next;
        }
    }

    private Node<E> head;
    private Node<E> tail;

    private int size;

    public LinkedQueue() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean empty() {
        return size == 0;
    }

    @Override
    public void enqueue(E e) {

        Node<E> prev = tail;
        tail = new Node(e, null);

        if(size == 0) {
            head = tail;
        }else{
            prev.next = tail;
        }

        size++;

    }

    @Override
    public E dequeue() {

        if(size == 0) {
            throw new RuntimeException("队列为空...");
        }

        E result = head.data;
        head = head.next;

        size--;

        if(size == 0) {
            tail = null;
        }

        return result;
    }

    @Override
    public E peek() {
        if(size == 0) {
            throw new RuntimeException("队列为空...");
        }

        return head.data;
    }



    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedQueue<Integer>();
        for(int i=0; i<10; i++) {
            queue.enqueue(i+1);
        }
        int size = queue.size();

        for(int i=0; i<size; i++){
            System.out.println("The element is: " + queue.dequeue());
        }
    }
}
