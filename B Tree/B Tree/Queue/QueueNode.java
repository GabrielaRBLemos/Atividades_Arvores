package Queue;

public class QueueNode<T> {
    T Value;
    QueueNode<T> Prev, Next;
    public T getValue() {
        return Value;
    }
    public void setValue(T Value) {
        this.Value = Value;
    }
    public QueueNode<T> getPrev() {
        return Prev;
    }
    public void setPrev(QueueNode<T> Prev) {
        this.Prev = Prev;
    }
    public QueueNode<T> getNext() {
        return Next;
    }
    public void setNext(QueueNode<T> Next) {
        this.Next = Next;
    }
    public QueueNode(T Value) {
        this.Value = Value;
        this.Next = null;
    }
}
