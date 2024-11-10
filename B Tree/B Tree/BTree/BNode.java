package BTree;

import java.util.ArrayList;

class BNode <T extends Comparable<T>>{
    private ArrayList<T> valueArray;
    private ArrayList<BNode<T>> pointerArray;

    BNode(int degree) {
        valueArray = new ArrayList<T>(degree - 1);
        pointerArray = new ArrayList<BNode<T>>(degree);
    }

    public ArrayList<T> getValueArray() {
        return this.valueArray;
    }

    public void setValueArray(ArrayList<T> valueArray) {
        this.valueArray = valueArray;
    }

    public ArrayList<BNode<T>> getPointerArray() {
        return pointerArray;
    }

    public void setPointerArray(ArrayList<BNode<T>> pointerArray) {
        this.pointerArray = pointerArray;
    }

    public T getValueAt(int index) {
        if (isInvalidIndexValue(index)) {
            throw new IndexOutOfBoundsException("Índice fora dos limites.");
        }
        return this.valueArray.get(index);
    }

    public void setValueAt(int index, T value) {
        if (isInvalidIndexValue(index)) {
            throw new IndexOutOfBoundsException("Índice fora dos limites.");
        }
        this.valueArray.set(index, value);
    }

    public BNode<T> getPointerAt(int index) {
        if (isInvalidIndexPointer(index)) {
            throw new IndexOutOfBoundsException("Índice fora dos limites.");
        }
        return this.pointerArray.get(index);
    }

    public void setPointerAt(int index, BNode<T> value) {
        if (isInvalidIndexPointer(index)) {
            throw new IndexOutOfBoundsException("Índice fora dos limites.");
        }
        this.pointerArray.set(index, value);
    }

    public boolean isInvalidIndexValue(int index){
        return index < 0 || index >= this.valueArray.size();
    }

    public boolean isInvalidIndexPointer(int index){
        return index < 0 || index >= this.pointerArray.size();
    }

    public boolean isLeaf(){
        return pointerArray.isEmpty();
    }
}