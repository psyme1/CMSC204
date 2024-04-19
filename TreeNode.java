package com.example.project5;

public class TreeNode<T> {
    private T data;
    TreeNode<T> left;
    TreeNode<T> right;
    public TreeNode(T data){
        this.data = data;
        this.left = null;
        this.right = null;
    }
    public TreeNode(TreeNode<T> node){
        this.data = node.data;
        this.left = node.left;
        this.right = node.right;
    }
    public T getData(){return this.data;}
}
