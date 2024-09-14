package AVLTree;

class AVLTNode<T extends Comparable<T>>{
    private AVLTNode<T> left,right;
    private T Value;
    private int balFactor;

    

    public AVLTNode(T value) {
        this.left = null;
        this.right = null;
        Value = value;
        this.balFactor = 0;
    }

    public int getbalFactor() {
        return balFactor;
    }

    public void setbalFactor(int balFactor) {
        this.balFactor = balFactor;
    }

    public T getValue() {
        return Value;
    }
    
    public void setValue(T value) {
        Value = value;
    }

    public AVLTNode<T> getLeft() {
        return left;
    }

    public void setLeft(AVLTNode<T> left) {
        this.left = left;
    }

    public AVLTNode<T> getRight() {
        return right;
    }

    public void setRight(AVLTNode<T> right) {
        this.right = right;
    }
}