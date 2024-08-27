package BinarySearchTree;

import Queue.Queue;
import Stack.Stack;

public class BST<T extends Comparable<T>>{
    private BSTNode<T> root;

    public BSTNode<T> getRoot() {
        return root;
    }

    public void setRoot(BSTNode<T> root) {
        this.root = root;
    }

    public boolean isEmpty(){
        if(this.root == null){
            return true;
        }
        else{
            return false;
        }
    }

    public void insert(T value){
        BSTNode<T> newNode;
        newNode = new BSTNode<T>(value);
        if (this.isEmpty() == true) {
            this.root = newNode;
        }
        else{
            BSTNode<T> currentNode = this.root;
            while (true) {
                if (currentNode.getValue().compareTo(newNode.getValue())>0){
                    if (currentNode.getLeft() == null){
                        currentNode.setLeft(newNode);
                        System.err.println("Inserção Efetuada");
                        return;
                    }
                    else{
                        currentNode = currentNode.getLeft();
                    }
                }
                else{
                    if (currentNode.getRight() == null){
                        currentNode.setRight(newNode);
                        System.err.println("Inserção Efetuada");
                        return;
                    }
                    else{
                        currentNode = currentNode.getRight();
                    }
                }
            }
        }
    }

    private BSTNode<T> search(T searchValue){
        BSTNode<T> currentNode = this.root;
        if (this.isEmpty()) {
            return null;
        }
        else{
            while (currentNode != null) {
                if(currentNode.getValue().compareTo(searchValue)>0){
                    currentNode = currentNode.getLeft();
                }
                else if(currentNode.getValue().compareTo(searchValue)<0){
                    currentNode = currentNode.getRight();
                }
                else if(currentNode.getValue().compareTo(searchValue)<0){
                    return currentNode;
                }
            }
            return null;
        }
    }

    public T lookup(T searchValue){
        BSTNode<T>result = this.search(searchValue);
        if (result == null) {
            return null;
        }
        else{
            return result.getValue();
        }
    }

    public void inOrder(){
        if (isEmpty()) {
            System.out.println("O registro está vazio.");
        }
        else{
            Stack<BSTNode<T>> waitingStack = new Stack<BSTNode<T>>();
            BSTNode<T> currentNode = this.root;

            waitingStack.push(currentNode);
            
            while(currentNode != null || !waitingStack.isEmpty()){
                while (currentNode != null) {
                    waitingStack.push(currentNode);
                    currentNode = currentNode.getLeft();
                }
                currentNode = waitingStack.pop();
                System.out.println(currentNode.getValue().toString());
                currentNode = currentNode.getRight();
            }
        }
    }

    public void contain(T searchValue){
        if (this.search(searchValue)==null) {
            System.err.println("Produto cadastrado");
        }
        else{
            System.err.println("Código não encontrado");
        }
    }

    public int numberOfNodes(){
        if (this.isEmpty()) {
            return 0;
        }
        else{
            BSTNode<T> currentNode = this.root;
            Queue<BSTNode<T>> waitingQueue = new Queue<BSTNode<T>>();

            int numOfNodes = 0;

            waitingQueue.enqueue(currentNode);
            while (!waitingQueue.isEmpty()) {
                currentNode = waitingQueue.getFront().getValue();
                numOfNodes++;
                waitingQueue.dequeue();
                if (currentNode.getLeft() != null) {
                    waitingQueue.enqueue(currentNode.getLeft());
                }
                if (currentNode.getRight() != null) {
                    waitingQueue.enqueue(currentNode.getRight());
                }
            }
            return numOfNodes;
        }
    }
}
