package com.xuchang.ds.bit;

import com.xuchang.algo.sort.SortUtils;

import java.util.BitSet;
import java.util.Random;


public class BloomFilter {
    private final BitSet bs;

    final int[] hashSeeds;

    final int capacity;

    public BloomFilter(int capacity, int hashFunctions) {
        bs = new BitSet(capacity);
        Random r = new Random(System.currentTimeMillis());
        hashSeeds = new int[hashFunctions];
        for (int i = 0; i < hashFunctions; i++) {
            hashSeeds[i] = r.nextInt();
        }

        this.capacity = capacity;
    }

    public void add(int value) {
        byte[] b = new byte[]{
                (byte) (value >>> 24),
                (byte) (value >>> 16),
                (byte) (value >>> 8),
                (byte) (value)
        };
        for (int i = 0; i < hashSeeds.length; i++) {
            int h = MurmurHash.hash32(b, 4, hashSeeds[i]);
            bs.set(Math.abs(h) % capacity, true);
        }
    }

    public boolean mightContain(int value) {
        byte[] b = new byte[]{
                (byte) (value >>> 24),
                (byte) (value >>> 16),
                (byte) (value >>> 8),
                (byte) (value)
        };

        for (int i = 0; i < hashSeeds.length; i++) {
            int h = MurmurHash.hash32(b, 4, hashSeeds[i]);
            if (!bs.get(Math.abs(h) % capacity)) {
                return false;
            }
        }

        return true;
    }

    public void clear() {
        bs.clear();
    }

    public static void main(String[] args) {
        final int S = 200000;
        int[] arrayToSort = SortUtils.buildRandomIntArray(S);
        BloomFilter bf = new BloomFilter(S, 2);

        BitSet bitSet = new BitSet(S);

        for (int element : arrayToSort) {
            bitSet.set(element);
            bf.add(element);
        }

        Random generator = new Random();
        for (int i = 0; i < 10000; i++) {
            int rand = generator.nextInt(S);
            if (bitSet.get(rand)) {
                System.out.println("Number " + rand + " is in array ...");
            } else {
                if (bf.mightContain(rand)) {
                    System.out.println("Number " + rand + " is not in array ..., bloom filter error");
                }

            }
        }

    }
}
