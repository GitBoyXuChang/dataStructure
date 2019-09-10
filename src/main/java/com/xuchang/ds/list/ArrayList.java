package com.xuchang.ds.list;

import java.util.Arrays;


public class ArrayList<E> implements List<E> {

    private  static final int DEFAULT_CAPACITY = 10;
    private E[] data;
    private int size;

    public ArrayList(int capacity) {
        if(capacity < 0) {
            throw new IllegalArgumentException("数组容量不能为负...");
        }

        data = (E[]) new Object[capacity];
        size = 0;
    }

    public ArrayList() {
        this(DEFAULT_CAPACITY);
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
        for(int i=0; i<size; i++) {
            if(data[i].equals(o)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public int indexOf(E e) {
        for(int i=0; i<size; i++) {
            if(data[i].equals(e)){
                return i;
            }
        }
        return -1;
    }

    @Override
    public E get(int index) {
        if(index < 0 || index >=size) {
            throw new IllegalArgumentException("数组下标越界...");
        }

        return data[index];
    }

    @Override
    public void set(int index, E e) {
        if(index < 0 || index >=size) {
            throw new IllegalArgumentException("数组下标越界...");
        }

        data[index] = e;
    }

    @Override
    public void add(int index, E e) {
        if(index < 0 || index > size) {
            throw new IllegalArgumentException("数组下标越界...");
        }

        if(size == data.length) {
            grow(2*data.length);
        }

        //TODO when the size is full, copy in the grow method
        for(int i=size-1; i>=index; i--) {
            data[i+1] = data[i];
        }

        data[index] = e;
        size++;
    }

    private void grow(int newcapacity) {
       /* E[] newdata = (E[]) new Object[newcapacity];

        for(int i=0; i<data.length; i++) {
            newdata[i] = data[i];
        }

        data= newdata;*/

        if(newcapacity <= DEFAULT_CAPACITY){
            return;
        }

        data = Arrays.copyOf(data, newcapacity);
    }

    @Override
    public E remove(int index) {
        if(index < 0 || index >=size) {
            throw new IllegalArgumentException("数组下标越界...");
        }

        E val = data[index];

        for(int i = index + 1; i < size; i++) {
            data[i-1] = data[i];
        }

        //TODO
        if(size < data.length /2 ) {
            grow(data.length / 2);
        }

        size--;
        data[size] = null;

        return val;
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<Integer>();

        for(int i=0; i<100; i++) {
            list.add(i, i);
        }

        for(int i=0; i<list.getSize(); i++) {
            System.out.println("The " + i + "th element is: " + list.get(i));
        }

        for(int i=0; i<50; i+=5) {
            list.remove(i);
        }

        for(int i=0; i<list.getSize(); i++) {
            System.out.println("After removing, The " + i + "th element is: " + list.get(i));
        }
    }
}
