package com.xuchang.ds.tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

 
public class BinaryTreeTraverse {

    public static void traversalByLevel(TreeNode root) {
        if (root == null ) {
            return ;
        }

        Queue<TreeNode> queue = new LinkedList<>();

        queue.offer(root);

        while(!queue.isEmpty()) {
            int size = queue.size();

            for(int i=0; i<size; i++) {
                TreeNode node = queue.poll();
                System.out.print(" " + node.val + "");

                if(node.left != null) {
                    queue.offer(node.left);
                }

                if(node.right != null) {
                    queue.offer((node.right));
                }
            }

            System.out.println("");
        }

    }

    public static List<Integer> preorderTraversalIter(TreeNode root) {
        List<Integer>  result = new ArrayList<>();

        if(root == null) {
            return result;
        }

        //Deque<TreeNode> stack = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while(!stack.isEmpty()) {
            TreeNode node = stack.pop();
            result.add(node.val);

            if(node.right != null) {
                stack.push(node.right);
            }

            if(node.left != null) {
                stack.push(node.left);
            }
        }

        return result;
    }


    public static List<Integer> inorderTraversalIter(TreeNode root) {
        List<Integer>  result = new ArrayList<>();

        if(root == null) {
            return result;
        }

        Stack<TreeNode> stack = new Stack<>();

        TreeNode curr = root;

        while(curr != null || !stack.isEmpty() ) {
            while(curr != null) {
                stack.push(curr);
                curr = curr.left;
            }

            curr = stack.pop();
            result.add(curr.val);
            curr = curr.right;
        }


        return result;
    }

    public static List<Integer> postorderTraversalIter(TreeNode root) {
        List<Integer>  result = new ArrayList<>();

        if(root == null) {
            return result;
        }

        Stack<TreeNode> stack = new Stack<>();
        Stack<TreeNode> outputStack = new Stack<>();

        stack.push(root);
        while(!stack.isEmpty()) {
            TreeNode node = stack.pop();
            outputStack.push(node);

            if(node.left != null) {
                stack.push(node.left);
            }

            if(node.right != null) {
                stack.push(node.right);
            }
        }

        while(!outputStack.isEmpty()) {
            TreeNode node = outputStack.pop();
            result.add(node.val);
        }

        return result;
    }


    public static List<Integer> preorderTraversal(TreeNode  root) {
        List<Integer>  result = new ArrayList<>();

        preorderHelper(root, result);
        return result;
    }

    static void  preorderHelper(TreeNode root, List<Integer> result) {
        if(root == null){
            return;
        }

        result.add(root.val);
        preorderHelper(root.left, result);
        preorderHelper(root.right, result);
    }

    public static List<Integer> inorderTraversal(TreeNode  root) {
        List<Integer>  result = new ArrayList<>();

        inorderHelper(root, result);
        return result;
    }

    static void  inorderHelper(TreeNode root, List<Integer> result) {
        if(root == null){
            return;
        }

        inorderHelper(root.left, result);
        result.add(root.val);
        inorderHelper(root.right, result);
    }

    public static List<Integer> postorderTraversal(TreeNode  root) {
        List<Integer>  result = new ArrayList<>();

        postorderHelper(root, result);
        return result;
    }

    static void   postorderHelper(TreeNode root, List<Integer> result) {
        if(root == null){
            return;
        }

        postorderHelper(root.left, result);
        postorderHelper(root.right, result);
        result.add(root.val);

    }
}
