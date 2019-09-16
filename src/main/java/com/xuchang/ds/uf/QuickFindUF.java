package com.xuchang.ds.uf;

import java.util.Random;


public class QuickFindUF implements UnionFind {
    private int[] id;
    private int count; //component counts

    public QuickFindUF(int n){
        count = n;
        id = new int[n];
        for(int i=0; i<n; i++) {
            id[i] = i;
        }
    }

    private void validate(int p) {
        if(p < 0 || p >= id.length) {
            throw new IllegalArgumentException("index " + p + " is not between 0 and " + id.length);
        }
    }

    //O(n)
    @Override
    public void union(int p, int q) {
        validate(p);
        validate(q);

        int pId = id[p];
        int qId = id[q];

        if(pId == qId) {
            return;
        }

        for(int i=0; i<id.length; i++) {
            if(id[i] == pId) {
                id[i] = qId;
            }
        }
    }

    //O(1)
    @Override
    public boolean connected(int p, int q) {
        validate(p);
        validate(q);
        return id[p] == id[q];
    }

    //O(1)
    @Override
    public int find(int p) {
        validate(p);
        return id[p];
    }

    @Override
    public int count() {
        return count;
    }

    public  static void main(String[] args) {
        int N = 100000;
        UnionFind uf = new QuickFindUF(N);

        Random rand = new Random();

        long startTime = System.nanoTime();

        for(int i=0; i<N; i++) {
            int a = rand.nextInt(N);
            int b = rand.nextInt(N);
            uf.union(a, b);
        }

        for(int i=0; i<N; i++) {
            int a = rand.nextInt(N);
            int b = rand.nextInt(N);
            uf.connected(a, b);
        }

        long elasped = System.nanoTime() - startTime;

        System.out.println("The time elasped is: " + elasped);

    }
}
