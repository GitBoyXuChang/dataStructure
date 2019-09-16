package com.xuchang.ds.bit;

import com.xuchang.algo.sort.SortUtils;

import java.util.BitSet;
import java.util.Random;


public class BitSetDemo {
    public static void main(String[] args) {
        final int S = 20000000;
        int[] arrayToSort = SortUtils.buildRandomIntArray(S);

        BitSet bitSet  = new BitSet(S);

        for(int element: arrayToSort) {
            bitSet.set(element);
        }

        Random generator = new Random();
        for(int i=0; i<100; i++) {
            int rand = generator.nextInt(S);
            if(bitSet.get(rand)) {
                System.out.println("Number " + rand + " is in array ...");
            }else {
                System.out.println("Number " + rand + " is not in array ...");
            }
        }

    }
}
