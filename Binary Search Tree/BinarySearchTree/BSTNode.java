package BinarySearchTree;

class BSTNode{
    private BSTNode left;
    private int Value;
    private BSTNode right;

    public BSTNode(int value) {
        Value = value;
    }

    public int getValue() {
        return Value;
    }
    
    public void setValue(int value) {
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