package BinarySearchTree;

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

    public void insert(int value){
        BSTNode newNode;
        newNode = new BSTNode(value);
        if (this.isEmpty() == true) {
            this.root = newNode;
        }
        else{
            BSTNode currentNode = this.root;
            while (true) {
                if (currentNode.getValue() > newNode.getValue()){
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

    private BSTNode search(int value){
        BSTNode currentNode = this.root;
        while (true) {
            if(currentNode.getValue() > value){
                currentNode = currentNode.getLeft();
            }
            else if(currentNode.getValue() < value){
                currentNode = currentNode.getRight();
            }
            else if(currentNode.getValue() == value){
                return currentNode;
            }
            else{
                return null;
            }
        }
    }

    public void contain(int value){
        if (this.search(value)==null) {
            System.err.println("Valor Não Encontrado na Árvore");
        }
        else{
            System.err.println("Valor Encontrado na Árvore");
        }
    }

    public void insertTwoAux(int value){
        if (this.isEmpty() == true) {
            this.root.setValue(value);
        }
        else{
            BSTNode currentNode = this.root;
            BSTNode parentNode = null;
            while (true) {
                if (currentNode.getValue() > value){
                    parentNode = currentNode;
                    currentNode = currentNode.getLeft();
                    if (currentNode.getLeft() == null){
                        parentNode.setValue(value);
                        System.err.println("Inserção Efetuada");
                        return;
                    }
                }
                else{
                    parentNode = currentNode;
                    currentNode = currentNode.getRight();
                    if (currentNode.getRight() == null){
                        parentNode.setValue(value);
                        System.err.println("Inserção Efetuada");
                        return;
                    }
                }
            }
        }
    }

    private BSTNode leftestNode(){
        BSTNode currentNode = this.root;
        while(currentNode.getLeft() != null){
            currentNode = currentNode.getLeft();
        }
        return currentNode;
    }

    private BSTNode rightestNode(){
            BSTNode currentNode = this.root;
            while(currentNode.getRight() != null){
                currentNode = currentNode.getRight();
            }
            return currentNode;
    }

    public void min(){
        int min = leftestNode().getValue();
        System.out.println(String.format("O Menor Valor Na Árvore: %d", min));
    }

    public void max(){
        int max = rightestNode().getValue();
        System.out.println(String.format("O Maior Valor Na Árvore: %d", max));
    }

    public void byLevel(){
        if (this.isEmpty()) {
            System.out.println("A árvore está vazia.");
        }
        else{
            BSTNode currentNode = this.root;
            Queue<BSTNode> waitingQueue = new Queue<BSTNode>();

            waitingQueue.enqueue(currentNode);
            while (!waitingQueue.isEmpty()) {
                currentNode = waitingQueue.getFront().getValue();
                System.out.print(currentNode.getValue()+" ");
                waitingQueue.dequeue();
                if (currentNode.getLeft() != null) {
                    waitingQueue.enqueue(currentNode.getLeft());
                }
                if (currentNode.getRight() != null) {
                    waitingQueue.enqueue(currentNode.getRight());
                }
            }
            System.out.println();
        }
    }

    public void inOrder(){
        if (isEmpty()) {
            System.out.println("A árvore está vazia.");
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
                System.out.print(currentNode.getValue()+" ");
                currentNode = currentNode.getRight();
            }
            System.out.println();
        }
    }

    public void preOrder(){
        if (isEmpty()) {
            System.out.println("A árvore está vazia.");
        }
        else{
            Stack<BSTNode> waitingStack = new Stack<BSTNode>();
            BSTNode currentNode;

            waitingStack.push(this.root);
            
            while(!waitingStack.isEmpty()){
                currentNode = waitingStack.getTop().getValue();
                System.out.print(currentNode.getValue()+" ");

                waitingStack.pop();

                if (currentNode.getRight() != null) {
                    waitingStack.push(currentNode.getRight());
                }
                if (currentNode.getLeft() != null) {
                    waitingStack.push(currentNode.getLeft());
                }
            }
            System.out.println();
        }
    }

    public int numberOfNodesRecursive(BSTNode root){
        if (this.isEmpty()) {
            return 0;
        }
        else{
            if (root == null){
                return 0;
            }
            return 1 + numberOfNodesRecursive(root.getLeft()) + numberOfNodesRecursive(root.getRight());
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

    public int numberOfLeafsRecursive(BSTNode root){
        if (this.isEmpty()) {
            return 0;
        }
        else{
            if (root == null){
                return 0;
            }
            else if (root.getLeft() == null && root.getRight() == null){
                return 1 + numberOfLeafsRecursive(root.getLeft()) + numberOfNodesRecursive(root.getRight());
            }
            else{
                return 0 + numberOfLeafsRecursive(root.getLeft()) + numberOfNodesRecursive(root.getRight());
            }
        }
    }

    public int numberOfLeafs(){
        if (this.isEmpty()) {
            return 0;
        }
        else{
            BSTNode currentNode = this.root;
            Queue<BSTNode> waitingQueue = new Queue<BSTNode>();

            int numOfLeafs = 0;

            waitingQueue.enqueue(currentNode);
            while (!waitingQueue.isEmpty()) {
                currentNode = waitingQueue.getFront().getValue();
                if (currentNode.getLeft() == null && currentNode.getRight() == null) {
                    numOfLeafs++;                    
                }
                waitingQueue.dequeue();
                if (currentNode.getLeft() != null) {
                    waitingQueue.enqueue(currentNode.getLeft());
                }
                if (currentNode.getRight() != null) {
                    waitingQueue.enqueue(currentNode.getRight());
                }
            }
            return numOfLeafs;
        }
    }

    public int numberOfNonTerminalsRecursive(BSTNode root){
        if (this.isEmpty()) {
            return 0;
        }
        else{
            if (root == null){
                return 0;
            }
            else if (root.getLeft() != null || root.getRight() != null){
                return 1 + numberOfNonTerminalsRecursive(root.getLeft()) + numberOfNonTerminalsRecursive(root.getRight());
            }
            else{
                return 0 + numberOfNonTerminalsRecursive(root.getLeft()) + numberOfNonTerminalsRecursive(root.getRight());
            }
        }
    }

    public int numberOfNonTerminals(){
        if (this.isEmpty()) {
            return 0;
        }
        else{
            BSTNode currentNode = this.root;
            Queue<BSTNode> waitingQueue = new Queue<BSTNode>();

            int numOfLeafs = 0;

            waitingQueue.enqueue(currentNode);
            while (!waitingQueue.isEmpty()) {
                currentNode = waitingQueue.getFront().getValue();
                if (currentNode.getLeft() != null || currentNode.getRight() != null) {
                    numOfLeafs++;                    
                }
                waitingQueue.dequeue();
                if (currentNode.getLeft() != null) {
                    waitingQueue.enqueue(currentNode.getLeft());
                }
                if (currentNode.getRight() != null) {
                    waitingQueue.enqueue(currentNode.getRight());
                }
            }
            return numOfLeafs;
        }
    }
}