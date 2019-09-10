package com.xuchang.algo.sort;



public class RecursiveAlgo {

    private class Node<E> {
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

    public static int sum(int[] arr, int startIndex, int endIndex) {
        if(startIndex == endIndex){
            return arr[startIndex];
        }

        return arr[startIndex] + sum(arr, startIndex+1, endIndex);
    }

    public static int countListNodes(Node list){
        if(list == null) {
            return 0;
        }

        return 1 + countListNodes(list.next);
    }

    public static void printList(Node list) {
        if(list == null){
            return;
        }

        System.out.println(list.data);

        printList(list.next);
    }
    public static void printListReverse(Node list) {
        if(list == null){
            return;
        }

        printListReverse(list.next);
        System.out.println(list.data);

    }

}
