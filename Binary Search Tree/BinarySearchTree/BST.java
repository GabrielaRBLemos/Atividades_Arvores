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
                if (currentNode.getValue().compareTo(value)>0){
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

    public void remove(T value) {
        if (this.isEmpty()) {
            System.out.println("Árvore está vazia");
            return;
        }
    
        BSTNode<T> currentNode = this.root;
        BSTNode<T> parentNode = null;
        int result;
    
        while (currentNode != null) {
            result = value.compareTo(currentNode.getValue());
    
            if (result == 0) {
                // Caso 1: O nó a ser removido não tem filhos
                if (currentNode.getLeft() == null && currentNode.getRight() == null) {
                    if (parentNode == null) {
                        this.root = null; // Nó raiz sendo removido
                    } else if (parentNode.getLeft() == currentNode) {
                        parentNode.setLeft(null);
                    } else {
                        parentNode.setRight(null);
                    }
                }
                // Caso 2: O nó a ser removido tem apenas um filho
                else if (currentNode.getLeft() == null) {
                    if (parentNode == null) {
                        this.root = currentNode.getRight(); // Nó raiz sendo removido
                    } else if (parentNode.getLeft() == currentNode) {
                        parentNode.setLeft(currentNode.getRight());
                    } else {
                        parentNode.setRight(currentNode.getRight());
                    }
                } else if (currentNode.getRight() == null) {
                    if (parentNode == null) {
                        this.root = currentNode.getLeft(); // Nó raiz sendo removido
                    } else if (parentNode.getLeft() == currentNode) {
                        parentNode.setLeft(currentNode.getLeft());
                    } else {
                        parentNode.setRight(currentNode.getLeft());
                    }
                }
                // Caso 3: O nó a ser removido tem dois filhos
                else {
                    BSTNode<T> successorParent = currentNode;
                    BSTNode<T> successor = currentNode.getRight();
    
                    // Encontre o sucessor (menor valor na subárvore da direita)
                    while (successor.getLeft() != null) {
                        successorParent = successor;
                        successor = successor.getLeft();
                    }
    
                    // Substitua o valor do nó atual pelo sucessor
                    currentNode.setValue(successor.getValue());
    
                    // Remova o sucessor
                    if (successorParent.getLeft() == successor) {
                        successorParent.setLeft(successor.getRight());
                    } else {
                        successorParent.setRight(successor.getRight());
                    }
                }
    
                System.out.println("Remoção efetuada.");
                return;
            } else if (result < 0) {
                parentNode = currentNode;
                currentNode = currentNode.getLeft();
            } else {
                parentNode = currentNode;
                currentNode = currentNode.getRight();
            }
        }
    
        System.out.println("Valor não encontrado.");
    }

    public void removeRecursive(T value){
        if (this.isEmpty()==true) {
            System.out.println("Árvore está vazia");
        }
        else{
            this.root = this.removeNodeRecursive(this.root,value);
            int result = value.compareTo(root.getValue());
            if (result == 0) {
                if(root.getLeft()==null && root.getRight()==null){
                    root = null;
                }
                else if (root.getLeft()==null) {
                    root=root.getRight();
                }
                else if (root.getRight()==null) {
                    root=root.getLeft();
                }
                else{
                    //node with two children
                    BSTNode<T> parentNode, childNode;
                    parentNode = root;
                    childNode = root.getLeft();
                    if (childNode.getRight() != null) {
                        while (childNode.getRight() != null) {
                            parentNode = childNode;
                            childNode.getRight();
                        }
                    }
                    root.setValue(childNode.getValue());
                    parentNode.setRight(childNode.getLeft());
                    
                }
            }
        }
    }

    private BSTNode<T> removeNodeRecursive(BSTNode<T> root,T value){
        if (root != null) {
            int result = value.compareTo(root.getValue());
            if (result == 0) {
                if(root.getLeft()==null && root.getRight()==null){
                    root = null;
                }
                else if (root.getLeft()==null) {
                    root=root.getRight();
                }
                else if (root.getRight()==null) {
                    root=root.getLeft();
                }
                else{
                    //node with two children
                    BSTNode<T> parent, child;
                    parent = root;
                    child = root.getLeft();
                    if (child.getRight() != null) {
                        while (child.getRight() != null) {
                            parent = child;
                            child.getRight();
                        }
                    }
                    root.setValue(child.getValue());
                    parent.setRight(child.getLeft());
                    
                }
            }
            else if (result < 0){
                root.setLeft(this.removeNodeRecursive(root.getLeft(), value));
            }
            else{
                root.setRight(this.removeNodeRecursive(root.getRight(), value));
            }
        }
        return root;
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
        BSTNode<T> result = this.search(searchValue);
        if (result == null) {
            return null;
        }
        else{
            return result.getValue();
        }
    }

    public void contain(T value){
        if (this.search(value)==null) {
            System.err.println("Valor Não Encontrado na Árvore");
        }
        else{
            System.err.println("Valor Encontrado na Árvore");
        }
    }

    public void insertTwoAux(T value){
        if (this.isEmpty() == true) {
            this.root.setValue(value);
        }
        else{
            BSTNode<T> currentNode = this.root;
            BSTNode<T> parentNode = null;
            while (true) {
                if (currentNode.getValue().compareTo(value)>0){
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

    private BSTNode<T> leftestNode(){
        BSTNode<T> currentNode = this.root;
        while(currentNode.getLeft() != null){
            currentNode = currentNode.getLeft();
        }
        return currentNode;
    }

    private BSTNode<T> rightestNode(){
            BSTNode<T> currentNode = this.root;
            while(currentNode.getRight() != null){
                currentNode = currentNode.getRight();
            }
            return currentNode;
    }

    public void min(){
        T min = leftestNode().getValue();
        System.out.println(String.format("O Menor Valor Na Árvore: %d", min));
    }

    public void max(){
        T max = rightestNode().getValue();
        System.out.println(String.format("O Maior Valor Na Árvore: %d", max));
    }

    public void byLevel(){
        if (this.isEmpty()) {
            System.out.println("A árvore está vazia.");
        }
        else{
            BSTNode<T> currentNode = this.root;
            Queue<BSTNode<T>> waitingQueue = new Queue<BSTNode<T>>();

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
            Stack<BSTNode<T>> waitingStack = new Stack<BSTNode<T>>();
            BSTNode<T> currentNode = this.root;

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
            Stack<BSTNode<T>> waitingStack = new Stack<BSTNode<T>>();
            BSTNode<T> currentNode;

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

    public int numberOfNodesRecursive(BSTNode<T> root){
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

    public int numberOfLeafsRecursive(BSTNode<T> root){
        if (this.isEmpty()) {
            return 0;
        }
        else{
            if (root == null){
                return 0;
            }
            else if (root.getLeft() == null && root.getRight() == null){
                return 1 + numberOfLeafsRecursive(root.getLeft()) + numberOfLeafsRecursive(root.getRight());
            }
            else{
                return 0 + numberOfLeafsRecursive(root.getLeft()) + numberOfLeafsRecursive(root.getRight());
            }
        }
    }

    public int numberOfLeafs(){
        if (this.isEmpty()) {
            return 0;
        }
        else{
            BSTNode<T> currentNode = this.root;
            Queue<BSTNode<T>> waitingQueue = new Queue<BSTNode<T>>();

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

    public int numberOfNonTerminalsRecursive(BSTNode<T> root){
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
            BSTNode<T> currentNode = this.root;
            Queue<BSTNode<T>> waitingQueue = new Queue<BSTNode<T>>();

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