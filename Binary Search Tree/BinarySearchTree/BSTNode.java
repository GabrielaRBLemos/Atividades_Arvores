package BinarySearchTree;

class BSTNode<T extends Comparable<T>>{
    private BSTNode<T> left;
    private T Value;
    private BSTNode<T> right;

    public BSTNode(T value) {
        Value = value;
    }

    public T getValue() {
        return Value;
    }
    
    public void setValue(T value) {
        Value = value;
    }

    public BSTNode<T> getLeft() {
        return left;
    }

    public void setLeft(BSTNode<T> left) {
        this.left = left;
    }

    public BSTNode<T> getRight() {
        return right;
    }

    public void setRight(BSTNode<T> right) {
        this.right = right;
    }
    
}