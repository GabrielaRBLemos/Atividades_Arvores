package AVLTree;

import Queue.Queue;
import Stack.Stack;

public class AVLT<T extends Comparable<T>>{
    private AVLTNode<T> root;
    private boolean status;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public AVLTNode<T> getRoot() {
        return root;
    }

    public void setRoot(AVLTNode<T> root) {
        this.root = root;
    }

    public boolean isEmpty(){
        return this.root == null;
    }

    public void insert(T value){
        AVLTNode<T> newNode;
        newNode = new AVLTNode<T>(value);
        if (this.isEmpty() == true) {
            this.root = newNode;
        }
        else{
            this.root = insertNode(this.root, value);
            this.status = false;
        }
    }

    private AVLTNode<T> rotateRight (AVLTNode<T> a) {
        AVLTNode<T> b, c;
        b = a.getLeft();
        if (b.getbalFactor() == -1) {
            a.setLeft(b.getRight());
            b.setRight(a);
            a.setbalFactor(0);
            b.setbalFactor(0);
        }
        else {
            c = b.getRight();
            b.setRight(c.getLeft());
            c.setLeft(b);
            a.setLeft(c.getRight());
            c.setRight(a);
            if (c.getbalFactor() == -1) {
                a.setbalFactor(1);
            } 
            else{
                a.setbalFactor(0);
            }
            if (c.getbalFactor() == 1) {
                b.setbalFactor(-1);
            }
            else{
                b.setbalFactor(0);
            }
            a = c;
        }
        a.setbalFactor(0);
        this.status = false;
        return a;
    }

    private AVLTNode<T> rotateLeft (AVLTNode<T> a) {
        AVLTNode<T> b, c;
        b = a.getRight();
        if (b.getbalFactor() == 1) {
            a.setRight(b.getLeft());
            b.setLeft(a);
            a.setbalFactor(0);
            a = b;
        }
        else {
            c = b.getLeft();
            b.setLeft(c.getRight());
            c.setRight(b);
            a.setRight(c.getLeft());
            c.setLeft(a);
            if (c.getbalFactor() == -1) {
                a.setbalFactor(1);
            } 
            else{
                a.setbalFactor(0);
            }
            if (c.getbalFactor() == 1) {
                b.setbalFactor(-1);
            }
            else{
                b.setbalFactor(0);
            }
            a = c;
        }
        a.setbalFactor(0);
        this.status = false;
        return a;
    }


    private AVLTNode<T> insertNode (AVLTNode<T> r, T value) {
        if (r == null) {
            r = new AVLTNode<T> (value);
            this.status = true;
        }
        else if (r.getValue().compareTo(value) > 0) {
            r.setLeft(insertNode (r.getLeft(),value));
            if (this.status == true) {
                switch (r.getbalFactor()) {
                    case 1: 
                        r.setbalFactor(0);
                        this.status = false;
                        break;
                    case 0: 
                        r.setbalFactor(-1);
                        break;
                    case -1 : 
                        r = this.rotateRight(r);
                        break;
                }
            }
        }
        else {
            r.setRight(insertNode (r.getRight(),value));
                if (this.status) {
                    switch (r.getbalFactor()) {
                        case -1:
                            r.setbalFactor(0);
                            this.status = false;
                            break;
                        case 0:
                            r.setbalFactor(1);
                            break;
                        case 1 :
                            r = this.rotateLeft(r);
                            this.status = false;
                            break;
                    }
                }
            }
            return r;
    }

    public void inOrder(){
        if (isEmpty()) {
            System.out.println("A árvore está vazia.");
        }
        else{
            Stack<AVLTNode<T>> waitingStack = new Stack<AVLTNode<T>>();
            AVLTNode<T> currentNode = this.root;
            
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

    public void byLevel(){
        if (this.isEmpty()) {
            System.out.println("A árvore está vazia.");
        }
        else{
            AVLTNode<T> currentNode = this.root;
            Queue<AVLTNode<T>> waitingQueue = new Queue<AVLTNode<T>>();

            waitingQueue.enqueue(currentNode);
            while (!waitingQueue.isEmpty()) {
                currentNode = waitingQueue.dequeue();
                if (currentNode != null) {
                    System.out.print(currentNode.getValue()+" ");
                    if (currentNode.getLeft() != null) {
                        waitingQueue.enqueue(currentNode.getLeft());
                        if (currentNode.getRight() == null) {
                            waitingQueue.enqueue(null);
                        }
                    }
                    if (currentNode.getRight() != null) {
                        if (currentNode.getLeft() == null) {
                            waitingQueue.enqueue(null);
                        }
                        waitingQueue.enqueue(currentNode.getRight());
                    }
                    
                }
                else{
                    System.out.print("null ");
                }
            }
            System.out.println();
        }
    }

    public int height(){
        // TODO : function not working well
        if (isEmpty()) {
            return 0;
        }
        else{
            Stack<AVLTNode<T>> waitingStack = new Stack<AVLTNode<T>>();
            AVLTNode<T> currentNode = this.root;
            int nodeHeight = 0;
            int height = 0;
            
            while(currentNode != null || !waitingStack.isEmpty()){
                while (currentNode != null) {
                    waitingStack.push(currentNode);
                    currentNode = currentNode.getLeft();
                }
                currentNode = waitingStack.pop();
                if (currentNode != null && !currentNode.getbalFactor().equals(null)) {
                    nodeHeight = currentNode.getbalFactor() + 1;
                    height = Math.max(height,nodeHeight);
                }
                currentNode = currentNode.getRight();
            }
            return height;
        }
    }
}