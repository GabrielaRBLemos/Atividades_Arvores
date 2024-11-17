package Stack;

public class StackNode<T> {
    T Value;
    StackNode<T> Next;
    public T getValue() {
        return Value;
    }
    public void setValue(T Value) {
        this.Value = Value;
    }
    public StackNode<T> getNext() {
        return Next;
    }
    public void setNext(StackNode<T> Next) {
        this.Next = Next;
    }
    public StackNode(T Value) {
        this.Value = Value;
        this.Next = null;
    }
    
}
    