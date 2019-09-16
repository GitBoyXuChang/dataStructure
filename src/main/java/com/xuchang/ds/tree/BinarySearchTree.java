package com.xuchang.ds.tree;


public class BinarySearchTree<K extends Comparable<K>, V> implements  SymbolTable<K , V> {
    private class TreeNode {
        private K key;
        private V val;
        private TreeNode left;
        private TreeNode right;

        private int size;

        TreeNode(K key, V val, int size) {
            this.key = key;
            this.val = val;
            this.size = 0;
        }
    }

    private TreeNode root;


    @Override
    public void insert(K key, V value) {
        if(key == null) {
            throw new IllegalArgumentException("key 不能为空...");
        }

        if(value == null) {
            remove(key);
        }

        root = insert(root, key, value);
    }

    private TreeNode insert(TreeNode root, K key, V value) {
        if(root == null) {
            return new TreeNode(key, value, 1);
        }

        int ret = key.compareTo(root.key);
        if(ret < 0) {
            root.left = insert(root.left, key, value);
        }else if (ret > 0) {
            root.right = insert(root.right, key, value);
        }else {
            root.val = value;
        }

        root.size = 1 + size(root.left) + size(root.right);

        return root;

    }

    private int size(TreeNode root){
        if(root == null) {
            return 0;
        }

        return root.size;
    }

    @Override
    public V get(K key) {
        if(key == null) {
            throw new IllegalArgumentException("key 不能为空...");
        }

        return get(root, key);
    }

    private V get(TreeNode root, K key) {
        if(root == null) {
            return null;
        }

        int ret = key.compareTo(root.key);
        if(ret < 0) {
            return get(root.left, key);
        }else if(ret > 0) {
            return get(root.right, key);
        }else
        {
            return root.val;
        }
    }

    @Override
    public void remove(K key) {
        if(key == null) {
            throw new IllegalArgumentException("key 不能为空...");
        }

        root = remove(root, key);
    }

    private TreeNode remove(TreeNode root, K key) {
        if(root == null) {
            return null;
        }

        int ret = key.compareTo(root.key);
        if(ret < 0) {
            root.left = remove(root.left, key);
        }
        else if(ret > 0) {
            root.right = remove(root.right, key);
        }else {
            if(root.right == null) {
                return root.left;
            }

            if(root.left == null) {
                return root.right;
            }

            TreeNode node = root;
            root = min(node.right); //后继
            root.right = deleteMin(root.right);
            root.left = node.left;
        }

        return root;
    }

    @Override
    public boolean contains(K key) {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public int size() {
        return size(root);
    }

    @Override
    public K min() {
        if(isEmpty()) {
            throw new RuntimeException("空树...");
        }

        return min(root).key;
    }

    private TreeNode min(TreeNode root) {
        if(root.left == null) {
            return root;
        } else{
            return min(root.left);
        }
    }

    @Override
    public K max() {
        if(isEmpty()) {
            throw new RuntimeException("空树...");
        }

        return max(root.right).key;

    }

    private TreeNode max(TreeNode root) {
        if(root.right == null) {
            return root;
        } else{
            return max(root.right);
        }
    }

    @Override
    public void deleteMin() {
        if(isEmpty()) {
            throw new RuntimeException("空树...");
        }

        root = deleteMin(root);

    }

    private TreeNode deleteMin(TreeNode root) {
        if(root.left == null) {
            return root.right;
        }

        root.left = deleteMin(root.left);
        root.size = size(root.left) + size(root.right) + 1;

        return root;
    }

    @Override
    public void deleteMax() {
        if(isEmpty()) {
            throw new RuntimeException("空树...");
        }

        root = deleteMax(root);

    }

    private TreeNode deleteMax(TreeNode root) {
        if(root.right == null) {
            return root.left;
        }

        root.right = deleteMax(root.right);
        root.size = size(root.left) + size(root.right) + 1;

        return root;
    }
}
