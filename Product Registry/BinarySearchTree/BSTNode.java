package BinarySearchTree;

import ProductRegistry.Product;

class BSTNode{
    private BSTNode left;
    private Product Value;
    private BSTNode right;

    public BSTNode(Product value) {
        Value = value;
    }

    public Product getValue() {
        return Value;
    }
    
    public void setValue(Product value) {
        Value = value;
    }

    public BSTNode getLeft() {
        return left;
    }

    public void setLeft(BSTNode left) {
        this.left = left;
    }

    public BSTNode getRight() {
        return right;
    }

    public void setRight(BSTNode right) {
        this.right = right;
    }
    
}