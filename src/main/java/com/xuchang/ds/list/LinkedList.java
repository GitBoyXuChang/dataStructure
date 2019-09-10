package com.xuchang.ds.list;

/**
 * LinkedList 的源码
 * @param <E>
 */
public class LinkedList<E> implements List<E> {

    private class Node {
        private E data; //数据域
        private Node next; //指向下一个节点Node

        public Node(E data, Node next) {
            this.data = data;
            this.next = next;
        }

        public Node(E data) {
            this(data, null);
        }
    }

    private Node head;

    private int size;


    public LinkedList() {
        head = null;
        size = 0;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(E o) {
        Node p = head;
        while(p  != null) {
            if(p.data.equals(o)){
                return true;
            }

            p = p.next;
        }
        return false;
    }

    @Override
    public int indexOf(E e) {
        int result = -1;
        Node p = head;
        int i = 0;
        while(p != null) {
            if(p.data.equals(e)) {
                result = i;
                break;
            }

            p = p.next;
            i++;
        }

        return result;
    }

    @Override
    public E get(int index) {
        if(index < 0 || index >= size) {
            throw new IllegalArgumentException("数组下标越界...");
        }

        Node p = head;
        for(int i=0; i<index; i++){
            p = p.next;
        }

        return p.data;
    }

    @Override
    public void set(int index, E e) {
        if(index < 0 || index >= size) {
            throw new IllegalArgumentException("数组下标越界...");
        }

        Node p = head;
        for(int i=0; i<index; i++) {
            p = p.next;
        }

        p.data = e;

    }

    @Override
    public void add(int index, E e) {
        if(index < 0 || index > size) {
            throw new IllegalArgumentException("数据下标越界...");
        }

        if(index == 0) {
            addFirst(e);
        }else if(index == size){
            addLast(e);
        } else {
            Node prev = head;
            for(int i=0; i<index; i++) {
                prev = prev.next;
            }

            Node node = new Node(e, prev.next);
            prev.next = node;

            size++;
        }
    }

    private void addFirst(E e) {
        Node node = new Node(e, head);
        head = node;

        size++;
    }

    private void addLast(E e) {
        Node node = new Node(e, null);

        if(head == null) {
            head = node;
        }else {
            Node prev = head;

            while(prev.next != null) {
                prev = prev.next;
            }

            prev.next = node;
        }

        size++;
    }

    @Override
    public E remove(int index) {
        if(index < 0 || index >= size) {
            throw new IllegalArgumentException("数据下标越界...");
        }

        if(index == 0) {
            return removeFirst();
        }else if (index == size - 1) {
            return removeLast();
        }else {
            Node prev = head;
            for(int i=0; i<index-1; i++) {
                prev = prev.next;
            }

            Node tmp = prev.next;
            prev.next = tmp.next;
            tmp.next = null;
            size --;

            return tmp.data;

        }
    }

    private E removeFirst() {
        if(head == null){
            return null;
        }

        E ret = head.data;
        head = head.next;

        size--;

        return ret;
    }

    private E removeLast() {
        if(head == null) {
            return null;
        }

        E ret;

        if(head.next == null) {
            ret = head.data;
            head = null;
        }else {
            Node prev = head;

            while(prev.next.next != null) {
                prev = prev.next;
            }

            ret = prev.next.data;
            prev.next = null;
        }

        size--;

        return ret;
    }

    public static void main(String[] args) {
        List<Integer> list = new LinkedList<Integer>();

        for(int i=0; i<10; i++) {
            list.add(i, i);
        }

        for(int i=0; i<list.getSize(); i++) {
            System.out.println("The " + i + "th element is: " + list.get(i));
        }

        for(int i=0; i<5; i+=2) {
            list.remove(i);
        }

        for(int i=0; i<list.getSize(); i++) {
            System.out.println("After removing, The " + i + "th element is: " + list.get(i));
        }
    }
}
