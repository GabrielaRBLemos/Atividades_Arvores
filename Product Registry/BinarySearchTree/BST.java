package BinarySearchTree;

import ProductRegistry.Product;
import Queue.Queue;
import Stack.Stack;

public class BST{
    private BSTNode root;

    public BSTNode getRoot() {
        return root;
    }

    public void setRoot(BSTNode root) {
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

    public void insert(Product value){
        BSTNode newNode;
        newNode = new BSTNode(value);
        if (this.isEmpty() == true) {
            this.root = newNode;
        }
        else{
            BSTNode currentNode = this.root;
            while (true) {
                if (currentNode.getValue().getCode().compareTo(newNode.getValue().getCode())>0){
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

    public BSTNode search(String valuesCode){
        BSTNode currentNode = this.root;
        if (this.isEmpty()) {
            return null;
        }
        else{
            while (currentNode != null) {
                if(currentNode.getValue().getCode().compareTo(valuesCode)>0){
                    currentNode = currentNode.getLeft();
                }
                else if(currentNode.getValue().getCode().compareTo(valuesCode)<0){
                    currentNode = currentNode.getRight();
                }
                else if(currentNode.getValue().getCode().compareTo(valuesCode)<0){
                    return currentNode;
                }
            }
            return null;
        }
    }

    public void printNodeValue(String valuesCode){
        BSTNode result = search(valuesCode);
        if (result == null) {
            System.out.println("Código Não Encontrado");
        }
        else{
            System.out.println(result.toString());
        }
    }

    public void alterValuePrice(String valuesCode, double newPrice){
        BSTNode result = search(valuesCode);
        if (result == null) {
            System.out.println("Código Não Encontrado");
        }
        else{
            result.getValue().setPrice(newPrice);
        }
    }

    public void alterValueQntInStock(String valuesCode, int newQntInStock){
        BSTNode result = search(valuesCode);
        if (result == null) {
            System.out.println("Código Não Encontrado");
        }
        else{
            result.getValue().setQntInStock(newQntInStock);
        }
    }

    public void inOrder(){
        if (isEmpty()) {
            System.out.println("O registro está vazio.");
        }
        else{
            Stack<BSTNode> waitingStack = new Stack<BSTNode>();
            BSTNode currentNode = this.root;

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

    public void contain(String valuesCode){
        if (this.search(valuesCode)==null) {
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
            BSTNode currentNode = this.root;
            Queue<BSTNode> waitingQueue = new Queue<BSTNode>();

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
