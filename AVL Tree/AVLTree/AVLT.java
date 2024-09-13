package AVLTree;

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
            a = b;
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
        if (b.getbalFactor() == -1) {
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
                case 1: r.setbalFactor(0);
                this.status = false;
                break;
                case 0: r.setbalFactor(-1);
                break;
                case -1 : r = this.rotateRight(r);
                break;
                }
            }
        }
        else {
            r.setRight(insertNode (r.getRight(),value));
                if (this.status == true) {
                    switch (r.getbalFactor()) {
                    case -1: r.setbalFactor(0);
                    this.status = false;
                    break;
                    case 0: r.setbalFactor(1);
                    break;
                    case 1 : r = this.rotateLeft(r);
                    break;
                    }
                }
            }
            return r;
    }

    // private AVLTNode<T> removeNode(AVLTNode<T> root,T value){
    //     if (root != null) {
    //         int result = value.compareTo(root.AVLTNode());
    //         if (result == 0) {
    //             if(root.getLeft()==null && root.getRight()==null){
    //                 root = null;
    //             }
    //             else if (root.getLeft()==null) {
    //                 root=root.getRight();
    //             }
    //             else if (root.getRight()==null) {
    //                 root=root.getLeft();
    //             }
    //             else{
    //                 //node with two children
    //                 AVLTNode<T> parent, child;
    //                 parent = root;
    //                 child = root.getLeft();
    //                 if (child.getRight() != null) {
    //                     while (child.getRight() != null) {
    //                         parent = child;
    //                         child.getRight();
    //                     }
    //                 }
    //                 root.setValue(child.AVLTNode());
    //                 parent.setRight(child.getLeft());
                    
    //             }
    //         }
    //         else if (result < 0){
    //             root.setLeft(this.removeNode(root.getLeft(), value));
    //         }
    //         else{
    //             root.setRight(this.removeNode(root.getRight(), value));
    //         }
    //     }
    //     return root;
    // }


    // public void remove(T value) {
    //     if (this.isEmpty()) {
    //         System.out.println("Árvore está vazia");
    //         return;
    //     }
    
    //     AVLTNode<T> currentNode = this.root;
    //     AVLTNode<T> parentNode = null;
    //     int result;
    
    //     while (currentNode != null) {
    //         result = value.compareTo(currentNode.AVLTNode());
    
    //         if (result == 0) {
    //             if (currentNode.getLeft() == null && currentNode.getRight() == null) {
    //                 if (parentNode == null) {
    //                     this.root = null; // Nó raiz sendo removido
    //                 } else if (parentNode.getLeft() == currentNode) {
    //                     parentNode.setLeft(null);
    //                 } else {
    //                     parentNode.setRight(null);
    //                 }
    //             }
    //             else if (currentNode.getLeft() == null) {
    //                 if (parentNode == null) {
    //                     this.root = currentNode.getRight();
    //                 } else if (parentNode.getLeft() == currentNode) {
    //                     parentNode.setLeft(currentNode.getRight());
    //                 } else {
    //                     parentNode.setRight(currentNode.getRight());
    //                 }
    //             } else if (currentNode.getRight() == null) {
    //                 if (parentNode == null) {
    //                     this.root = currentNode.getLeft();
    //                 } else if (parentNode.getLeft() == currentNode) {
    //                     parentNode.setLeft(currentNode.getLeft());
    //                 } else {
    //                     parentNode.setRight(currentNode.getLeft());
    //                 }
    //             }
    //             else {
    //                 AVLTNode<T> successorParent = currentNode;
    //                 AVLTNode<T> successor = currentNode.getRight();
    
    //                 while (successor.getLeft() != null) {
    //                     successorParent = successor;
    //                     successor = successor.getLeft();
    //                 }
    
    //                 currentNode.setValue(successor.AVLTNode());
    
    //                 if (successorParent.getLeft() == successor) {
    //                     successorParent.setLeft(successor.getRight());
    //                 } else {
    //                     successorParent.setRight(successor.getRight());
    //                 }
    //             }
    
    //             System.out.println("Remoção efetuada.");
    //             return;
    //         } else if (result < 0) {
    //             parentNode = currentNode;
    //             currentNode = currentNode.getLeft();
    //         } else {
    //             parentNode = currentNode;
    //             currentNode = currentNode.getRight();
    //         }
    //     }
    
    //     System.out.println("Valor não encontrado.");
    // }

    // private AVLTNode<T> search(T searchValue){
    //     AVLTNode<T> currentNode = this.root;
    //     if (this.isEmpty()) {
    //         return null;
    //     }
    //     else{
    //         while (currentNode != null) {
    //             if(currentNode.AVLTNode().compareTo(searchValue)>0){
    //                 currentNode = currentNode.getLeft();
    //             }
    //             else if(currentNode.AVLTNode().compareTo(searchValue)<0){
    //                 currentNode = currentNode.getRight();
    //             }
    //             else if(currentNode.AVLTNode().compareTo(searchValue)<0){
    //                 return currentNode;
    //             }
    //         }
    //         return null;
    //     }
    // }

    // public T lookup(T searchValue){
    //     AVLTNode<T> result = this.search(searchValue);
    //     if (result == null) {
    //         return null;
    //     }
    //     else{
    //         return result.AVLTNode();
    //     }
    // }

    // public void contain(T value){
    //     if (this.search(value)==null) {
    //         System.err.println("Valor Não Encontrado na Árvore");
    //     }
    //     else{
    //         System.err.println("Valor Encontrado na Árvore");
    //     }
    // }

    // private AVLTNode<T> leftestNode(){
    //     AVLTNode<T> currentNode = this.root;
    //     while(currentNode.getLeft() != null){
    //         currentNode = currentNode.getLeft();
    //     }
    //     return currentNode;
    // }

    // private AVLTNode<T> rightestNode(){
    //         AVLTNode<T> currentNode = this.root;
    //         while(currentNode.getRight() != null){
    //             currentNode = currentNode.getRight();
    //         }
    //         return currentNode;
    // }

    // public void min(){
    //     T min = leftestNode().AVLTNode();
    //     System.out.println(String.format("O Menor Valor Na Árvore: %d", min));
    // }

    // public void max(){
    //     T max = rightestNode().AVLTNode();
    //     System.out.println(String.format("O Maior Valor Na Árvore: %d", max));
    // }

    // public void byLevel(){
    //     if (this.isEmpty()) {
    //         System.out.println("A árvore está vazia.");
    //     }
    //     else{
    //         AVLTNode<T> currentNode = this.root;
    //         Queue<AVLTNode<T>> waitingQueue = new Queue<AVLTNode<T>>();

    //         waitingQueue.enqueue(currentNode);
    //         while (!waitingQueue.isEmpty()) {
    //             currentNode = waitingQueue.getFront().AVLTNode();
    //             System.out.print(currentNode.AVLTNode()+" ");
    //             waitingQueue.dequeue();
    //             if (currentNode.getLeft() != null) {
    //                 waitingQueue.enqueue(currentNode.getLeft());
    //             }
    //             if (currentNode.getRight() != null) {
    //                 waitingQueue.enqueue(currentNode.getRight());
    //             }
    //         }
    //         System.out.println();
    //     }
    // }

    // public void inOrder(){
    //     if (isEmpty()) {
    //         System.out.println("A árvore está vazia.");
    //     }
    //     else{
    //         Stack<AVLTNode<T>> waitingStack = new Stack<AVLTNode<T>>();
    //         AVLTNode<T> currentNode = this.root;

    //         waitingStack.push(currentNode);
            
    //         while(currentNode != null || !waitingStack.isEmpty()){
    //             while (currentNode != null) {
    //                 waitingStack.push(currentNode);
    //                 currentNode = currentNode.getLeft();
    //             }
    //             currentNode = waitingStack.pop();
    //             System.out.print(currentNode.AVLTNode()+" ");
    //             currentNode = currentNode.getRight();
    //         }
    //         System.out.println();
    //     }
    // }

    // public void preOrder(){
    //     if (isEmpty()) {
    //         System.out.println("A árvore está vazia.");
    //     }
    //     else{
    //         Stack<AVLTNode<T>> waitingStack = new Stack<AVLTNode<T>>();
    //         AVLTNode<T> currentNode;

    //         waitingStack.push(this.root);
            
    //         while(!waitingStack.isEmpty()){
    //             currentNode = waitingStack.getTop().AVLTNode();
    //             System.out.print(currentNode.AVLTNode()+" ");

    //             waitingStack.pop();

    //             if (currentNode.getRight() != null) {
    //                 waitingStack.push(currentNode.getRight());
    //             }
    //             if (currentNode.getLeft() != null) {
    //                 waitingStack.push(currentNode.getLeft());
    //             }
    //         }
    //         System.out.println();
    //     }
    // }

    // public int numberOfNodesRecursive(AVLTNode<T> root){
    //     if (this.isEmpty()) {
    //         return 0;
    //     }
    //     else{
    //         if (root == null){
    //             return 0;
    //         }
    //         return 1 + numberOfNodesRecursive(root.getLeft()) + numberOfNodesRecursive(root.getRight());
    //     }
    // }

    // public int numberOfNodes(){
    //     if (this.isEmpty()) {
    //         return 0;
    //     }
    //     else{
    //         AVLTNode<T> currentNode = this.root;
    //         Queue<AVLTNode<T>> waitingQueue = new Queue<AVLTNode<T>>();

    //         int numOfNodes = 0;

    //         waitingQueue.enqueue(currentNode);
    //         while (!waitingQueue.isEmpty()) {
    //             currentNode = waitingQueue.getFront().AVLTNode();
    //             numOfNodes++;
    //             waitingQueue.dequeue();
    //             if (currentNode.getLeft() != null) {
    //                 waitingQueue.enqueue(currentNode.getLeft());
    //             }
    //             if (currentNode.getRight() != null) {
    //                 waitingQueue.enqueue(currentNode.getRight());
    //             }
    //         }
    //         return numOfNodes;
    //     }
    // }

    // public int numberOfLeafsRecursive(AVLTNode<T> root){
    //     if (this.isEmpty()) {
    //         return 0;
    //     }
    //     else{
    //         if (root == null){
    //             return 0;
    //         }
    //         else if (root.getLeft() == null && root.getRight() == null){
    //             return 1 + numberOfLeafsRecursive(root.getLeft()) + numberOfLeafsRecursive(root.getRight());
    //         }
    //         else{
    //             return 0 + numberOfLeafsRecursive(root.getLeft()) + numberOfLeafsRecursive(root.getRight());
    //         }
    //     }
    // }

    // public int numberOfLeafs(){
    //     if (this.isEmpty()) {
    //         return 0;
    //     }
    //     else{
    //         AVLTNode<T> currentNode = this.root;
    //         Queue<AVLTNode<T>> waitingQueue = new Queue<AVLTNode<T>>();

    //         int numOfLeafs = 0;

    //         waitingQueue.enqueue(currentNode);
    //         while (!waitingQueue.isEmpty()) {
    //             currentNode = waitingQueue.getFront().AVLTNode();
    //             if (currentNode.getLeft() == null && currentNode.getRight() == null) {
    //                 numOfLeafs++;                    
    //             }
    //             waitingQueue.dequeue();
    //             if (currentNode.getLeft() != null) {
    //                 waitingQueue.enqueue(currentNode.getLeft());
    //             }
    //             if (currentNode.getRight() != null) {
    //                 waitingQueue.enqueue(currentNode.getRight());
    //             }
    //         }
    //         return numOfLeafs;
    //     }
    // }

    // public int numberOfNonTerminalsRecursive(AVLTNode<T> root){
    //     if (this.isEmpty()) {
    //         return 0;
    //     }
    //     else{
    //         if (root == null){
    //             return 0;
    //         }
    //         else if (root.getLeft() != null || root.getRight() != null){
    //             return 1 + numberOfNonTerminalsRecursive(root.getLeft()) + numberOfNonTerminalsRecursive(root.getRight());
    //         }
    //         else{
    //             return 0 + numberOfNonTerminalsRecursive(root.getLeft()) + numberOfNonTerminalsRecursive(root.getRight());
    //         }
    //     }
    // }

    // public int numberOfNonTerminals(){
    //     if (this.isEmpty()) {
    //         return 0;
    //     }
    //     else{
    //         AVLTNode<T> currentNode = this.root;
    //         Queue<AVLTNode<T>> waitingQueue = new Queue<AVLTNode<T>>();

    //         int numOfLeafs = 0;

    //         waitingQueue.enqueue(currentNode);
    //         while (!waitingQueue.isEmpty()) {
    //             currentNode = waitingQueue.getFront().AVLTNode();
    //             if (currentNode.getLeft() != null || currentNode.getRight() != null) {
    //                 numOfLeafs++;                    
    //             }
    //             waitingQueue.dequeue();
    //             if (currentNode.getLeft() != null) {
    //                 waitingQueue.enqueue(currentNode.getLeft());
    //             }
    //             if (currentNode.getRight() != null) {
    //                 waitingQueue.enqueue(currentNode.getRight());
    //             }
    //         }
    //         return numOfLeafs;
    //     }
    // }

    // public int heightRecursive(){
    //     if(this.isEmpty()  == true){
    //         return 0;
    //     }
    //     else if (this.root.getLeft() ==  null && this.root.getRight() ==  null) {
    //         return 0;
    //     }
    //     else{
    //         return nodeHeight(this.root);
    //     }
    // }

    // private int nodeHeight(AVLTNode<T> root){
    //     if (root.getLeft()==null && root.getRight() == null) {
    //         return 0;
    //     }
    //     else if (root.getLeft() == null) {
    //         int rightHeight = nodeHeight(root.getRight());
    //         return rightHeight + 1;
    //     }
    //     else if (root.getRight() == null) {
    //         int leftHeight = nodeHeight(root.getLeft());
    //         return leftHeight + 1;
    //     }
    //     else{
    //         int leftHeight = nodeHeight(root.getLeft());
    //         int rightHeight = nodeHeight(root.getRight());
            
    //         if(leftHeight>rightHeight){
    //             return leftHeight + 1;
    //         }
    //         else {
    //             return rightHeight + 1;
    //         }
    //     }
    // }

    // public int frequency(T Value){
    //     if (isEmpty()) {
    //         return 0;
    //     }
    //     else{
    //         int frequencyNum = 0;
    //         AVLTNode<T> currentNode = search(Value);

    //         while (currentNode.AVLTNode()==Value) {
    //             frequencyNum =+ 1;
    //             currentNode = currentNode.getRight();
    //         }
            
    //         return frequencyNum;
    //     }
    // }
}
