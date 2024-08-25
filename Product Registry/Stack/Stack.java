package Stack;

public class Stack<T> {
    private int size;
    private StackNode<T> top;

    public Stack() {
        this.size = 0;
        this.top = null;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public StackNode<T> getTop() {
        return top;
    }

    public boolean isEmpty() {
        return size == 0 && this.top == null;
    }

    public boolean isFull() {
        return false;
    }

    public void push(T value) {
        StackNode<T> newNode = new StackNode<T>(value);
        if (isEmpty()) {
            top = newNode;
        } else {
            newNode.setNext(top);
            top = newNode;
        }
        size++;
    }

    public T pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        T value = top.getValue();
        top = top.getNext();
        size--;
        return value;
    }
}
