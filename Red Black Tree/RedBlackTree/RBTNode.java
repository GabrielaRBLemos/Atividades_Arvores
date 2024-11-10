package RedBlackTree;

enum Color{
    RED, BLACK
}

public class RBTNode<T extends Comparable<T>> implements Comparable<RBTNode<T>>{
    private RBTNode<T> left, right, parent;
    private Color color;
    private T Value;
    private Boolean Deleted;

    public RBTNode(T value) {
        this.Value = value;
        this.color = Color.RED;
        this.left = null;
        this.right = null;
        this.parent = null;
        this.Deleted = false;
    }

    public RBTNode<T> getParent() {
        return parent;
    }

    public void setParent(RBTNode<T> parent) {
        this.parent = parent;
    }

    public T getValue() {
        return Value;
    }
    
    public void setValue(T value) {
        this.Value = value;
    }

    public RBTNode<T> getLeft() {
        return left;
    }

    public void setLeft(RBTNode<T> left) {
        this.left = left;
    }

    public RBTNode<T> getRight() {
        return right;
    }

    public void setRight(RBTNode<T> right) {
        this.right = right;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Boolean getDeleted() {
        return Deleted;
    }

    public void setDeleted(Boolean deleted) {
        Deleted = deleted;
    }

    // função que retorna o nó com menor valor na subárvore do filho direito do nó, ou seja, o sucessor
    public RBTNode<T> successor(){
        RBTNode<T> currentNode = this.right;
        while (currentNode.getLeft() != null)
        currentNode = currentNode.getLeft();
        return currentNode;
    }

    RBTNode<T> sibling() {
        if (this.parent.getValue() == null){
            return null;
        }
        else if (parent.getLeft()==this){
            return parent.getRight();
        }
        else{
        return parent.getLeft();
        }
    }

    @Override
    public int compareTo(RBTNode<T> rbtNode) {
        return this.Value.compareTo(rbtNode.getValue()); 
    }

    
}