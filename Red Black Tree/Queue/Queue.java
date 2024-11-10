package Queue;

public class Queue<T> {
    private int Size;
    private QueueNode<T> Front, Rear;

    
    public Queue() {
        this.Size = 0;
        this.Front = null;
        this.Rear = null;
    }
    public int getSize() {
        return Size;
    }
    public void setSize(int Size) {
        this.Size = Size;
    }
    public QueueNode<T> getFront() {
        return Front;
    }
    public QueueNode<T> getRear() {
        return Rear;
    }

    public boolean isEmpty(){
        return Size == 0 && this.Front == null && this.Rear == null;
    }

    public boolean isFull(){
        return false;
    }

    public void enqueue(T Value) {
        QueueNode<T> newNode = new QueueNode<T>(Value);
        if (isEmpty()) {
            Front = Rear = newNode;
        } else {
            Rear.setNext(newNode);
            newNode.setPrev(Rear);
            Rear = newNode;
        }
        Size++;
    }

    public T dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Fila está vazia");
        }
        T Value = Front.getValue();
        Front = Front.getNext();
        if (Front == null) {
            Rear = null;
        } else {
            Front.setPrev(null);
        }
        Size--;
        return Value;
    }
}
