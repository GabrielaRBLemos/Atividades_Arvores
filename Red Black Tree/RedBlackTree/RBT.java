package RedBlackTree;

import Queue.Queue;
import Stack.Stack;

public class RBT<T extends Comparable<T>>{
    private RBTNode<T> root;
    private RBTNode<T> TNULL;

    public RBT() {
        // TNULL é um nó nulo para representar a folha nula
        TNULL = new RBTNode<T>(null);
        TNULL.setColor(Color.BLACK);
        this.root = TNULL;
    }

    public RBTNode<T> getRoot() {
        return root;
    }

    public void setRoot(RBTNode<T> root) {
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

    private RBTNode<T> search(T searchValue){
        RBTNode<T> currentNode = this.root;
        if (this.isEmpty()) {
            return null;
        }
        else{
            while (currentNode!= TNULL) {
                if(currentNode.getValue().compareTo(searchValue)>0){
                    currentNode = currentNode.getLeft();
                }
                else if(currentNode.getValue().compareTo(searchValue)<0){
                    currentNode = currentNode.getRight();
                }
                else if(currentNode.getValue().compareTo(searchValue)==0){
                    return currentNode;
                }
            }
            return null;
        }
    }

    public void insert(T value) {
        RBTNode<T> node = new RBTNode<T>(value);

        // Preenchendo informações do nó em preparo ( todo nó já é criado vermelho)
        node.setParent(null);
        node.setLeft(TNULL);
        node.setRight(TNULL);

        // Criando nós auxiliares
        RBTNode<T> parentNode = null;
        RBTNode<T> currentNode= this.root;

        // Loop que roda até chegar na folha nula procurando o lugar em que o novo valor se encaixa
        while (currentNode!= TNULL) {
            parentNode = currentNode;
            if (currentNode.compareTo(node) < 0) {
                currentNode = currentNode.getLeft();
            } else {
                currentNode = currentNode.getRight();
            }
        }

        //preenchendo as informações do nó, agora levando em conta a posição dele
        node.setParent(parentNode);
        if (parentNode == null) {
            root = node;
        } else if (parentNode.compareTo(node) < 0) {
            parentNode.setLeft(node);
        } else {
            parentNode.setRight(node);
        }

        if (node.getParent() == null) {
            node.setColor(Color.BLACK);
            return;
        }

        if (node.getParent().getParent() == null) {
            return;
        }

        //função que faz alterações necessárias para que as regras 4 e 5 não sejam violadas
        fixInsert(node);
    }


    private void fixInsert(RBTNode<T> k) {
        RBTNode<T> g = k.getParent().getParent();
        RBTNode<T> p = k.getParent();
        RBTNode<T> s;

        // Enquanto o pai de k (p) for vermelho, continua a correção
        while (p.getColor() == Color.RED) {
            if (p == g.getRight()) {
                s = g.getLeft();
                // se o tio for vermelho
                if (s.getColor() == Color.RED) {
                    s.setColor(Color.BLACK);
                    p.setColor(Color.BLACK);
                    g.setColor(Color.RED);
                    k = g;
                }
                // se tio é preto 
                else {
                    if (k == p.getLeft()) {
                        k = p;
                        rightRotate(k);
                    }
                    p.setColor(Color.BLACK);
                    g.setColor(Color.RED);
                    leftRotate(g);
                }
            } else {
                s = g.getRight();
                // se o tio for vermelho
                if (s.getColor() == Color.RED) {
                    s.setColor(Color.BLACK);
                    p.setColor(Color.BLACK);
                    g.setColor(Color.RED);
                    k = g;
                }
                // se o tio for preto 
                else {
                    if (k == p.getRight()) {
                        k = p;
                        leftRotate(k);
                    }
                    p.setColor(Color.BLACK);
                    g.setColor(Color.RED);
                    rightRotate(g);
                }
            }
            if (k == root) {
                break;
            }
        }
        // garantindo a regra 1
        root.setColor(Color.BLACK);
    }

    private void leftRotate(RBTNode<T> currentNode) {
        RBTNode<T> auxNode = currentNode.getRight();
        currentNode.setRight(auxNode.getLeft());
        if (auxNode.getLeft()!= TNULL) {
            auxNode.getLeft().setParent(currentNode);
        }
        auxNode.setParent(currentNode.getParent());
        if (currentNode.getParent() == null) {
            this.root = auxNode;
        } else if (currentNode == currentNode.getParent().getLeft()) {
            currentNode.getParent().setLeft(auxNode);
        } else {
            currentNode.getParent().setRight(auxNode);
        }
        auxNode.setLeft(currentNode);
        currentNode.setParent(auxNode);
    }

    private void rightRotate(RBTNode<T> currentNode) {
        RBTNode<T> auxNode = currentNode.getLeft();
        currentNode.setLeft(auxNode.getRight());
        if (auxNode.getRight()!= TNULL) {
            auxNode.getRight().setParent(currentNode);
        }
        auxNode.setParent(currentNode.getParent());
        if (currentNode.getParent() == null) {
            this.root = auxNode;
        } else if (currentNode == currentNode.getParent().getRight()) {
            currentNode.getParent().setRight(auxNode);
        } else {
            currentNode.getParent().setLeft(auxNode);
        }
        auxNode.setRight(currentNode);
        currentNode.setParent(auxNode);
    }

    public void byLevel(){
        if (this.isEmpty()) {
            System.out.println("A árvore está vazia.");
        }
        else{
            RBTNode<T> currentNode = this.root;
            Queue<RBTNode<T>> waitingQueue = new Queue<RBTNode<T>>();

            // Enfileirando a raiz em preparo para o loop
            waitingQueue.enqueue(currentNode);

            while (!waitingQueue.isEmpty()) {
                // Atribuir nó da frente da fila como nó atual
                currentNode = waitingQueue.getFront().getValue();

                // Exibir informações do nó atual
                if(!currentNode.getDeleted()){
                    System.out.print(currentNode.getValue()+" "+currentNode.getColor()+" ");
                }
                // Tirar nó da fila
                waitingQueue.dequeue();
                // Adicionar novos nós na fila
                if (currentNode.getRight() != null) {
                    waitingQueue.enqueue(currentNode.getRight());
                }
                if (currentNode.getLeft() != null) {
                    waitingQueue.enqueue(currentNode.getLeft());
                }
            }
            // Exibir quebra de linha quando o processo acabar
            System.out.println();
        }
    }

    public void inOrder(){
        if (isEmpty()) {
            System.out.println("A árvore está vazia.");
        }
        else{
            Stack<RBTNode<T>> waitingStack = new Stack<RBTNode<T>>();
            RBTNode<T> currentNode = this.root;
            
            while(currentNode != null || !waitingStack.isEmpty()){
                while (currentNode != null) {
                    waitingStack.push(currentNode);
                    currentNode = currentNode.getLeft();
                }
                currentNode = waitingStack.pop();
                if(currentNode!= TNULL && !currentNode.getDeleted()){
                    System.out.print(currentNode.getValue()+" "+currentNode.getColor()+" ");
                }
                currentNode = currentNode.getRight();
            }
            System.out.println();
        }
    }

    public void logicalDeleteNode(T value){
        RBTNode<T> node = search(value);
        node.setDeleted(true);
        System.out.println(node.getDeleted());
    }

    private void fixBothBlack(RBTNode<T> node){
        if(node == root){
            return;
        }
        if(node.sibling()==null){
            fixBothBlack(node.getParent());
        }
        else if (node.sibling().getColor() == Color.RED){
            node.getParent().setColor(Color.RED);
            node.sibling().setColor(Color.BLACK);
            if (node.sibling().getParent().getLeft() == node.sibling()) {
                rightRotate(node.getParent());
            }
            else{
                leftRotate(node.getParent());
            }
            fixBothBlack(node);
        }
        else{
            if (node.sibling().getLeft().getColor() == Color.RED || node.sibling().getRight().getColor() == Color.RED) {
                if (node.sibling().getLeft()!= TNULL && node.sibling().getLeft().getColor() == Color.RED) {
                    if (node.sibling().getParent().getLeft() == node.sibling()) {
                        node.sibling().getLeft().setColor(node.sibling().getColor());
                        node.sibling().setColor(node.getParent().getColor());
                        rightRotate(node.getParent());
                    } 
                    else {
                        node.sibling().getLeft().setColor(node.getParent().getColor());
                        rightRotate(node.sibling());
                        leftRotate(node.getParent());
                    }
                } 
                else {
                    if (node.sibling().getParent().getLeft() == node.sibling()) {
                        node.sibling().getRight().setColor(node.getParent().getColor());
                        leftRotate(node.sibling());
                        rightRotate(node.getParent());
                    } else {
                        node.sibling().getRight().setColor(node.sibling().getColor());
                        node.sibling().setColor(node.getParent().getColor());
                        leftRotate(node.getParent());
                    }
                }
                node.getParent().setColor(Color.BLACK);
            } 
            else {
                node.sibling().setColor(Color.RED);
                if (node.getParent().getColor() == Color.BLACK){
                    fixBothBlack(node.getParent());
                }
                else{
                    node.getParent().setColor(Color.BLACK);
                }
            }
        }
    }

    private void deleteNode(RBTNode<T> node){
        RBTNode<T> substitute;
        RBTNode<T> parentNode = node.getParent();
        
        // Achando o nó com o valor que irá substituir o valor a ser deletado 
        if (node.getLeft()!= TNULL && node.getRight()!= TNULL){
            substitute = node.successor();
        }
        else if (node.getLeft()== TNULL && node.getRight()== TNULL){
            substitute = TNULL;
        }
        else if (node.getLeft() != null){
            substitute = node.getLeft();
        }
        else{
            substitute = node.getRight();
        }
        // Se o nó for folha
        if (substitute== TNULL) {
            if (node == this.root)
             //Nó também é raiz, então é só deletar a raiz
                this.root = TNULL;
            else {
                if ((substitute.getColor() == Color.BLACK) && (node.getColor() == Color.BLACK)){
                    fixBothBlack(node);
                }
                // Ou o substituto ou o nó são vermelhos
                else if (node.sibling()!= TNULL)
                    node.sibling().setColor(Color.RED);
                if (node.getParent().getLeft() == node)
                    parentNode.setLeft(TNULL);
                else
                    parentNode.setRight(TNULL);
            }
            return;
        }
        // Se um dos filhos for nulo
        else if (node.getLeft()== TNULL || node.getRight()== TNULL) {
            if (node == this.root) {
                node.setValue(substitute.getValue());
                node.setLeft(TNULL);
                node.setRight(TNULL);
            } else {
                if (node.getParent().getLeft() == node)
                    parentNode.setLeft(substitute);
                else{
                    parentNode.setRight(substitute);
                }
                substitute.setParent(parentNode);
                if ((substitute.getColor() == Color.BLACK) && (node.getColor() == Color.BLACK)){
                    fixBothBlack(node);
                }
                else{
                    substitute.setColor(Color.BLACK);
                }
            }
            return;
        }
        else{
        // nó com 2 filhos
        T aux = substitute.getValue();
        substitute.setValue(node.getValue());
        node.setValue(aux);
        deleteNode(substitute);
        }
    }

    public void effectiveDelete(){
        if (this.isEmpty()) {
            System.out.println("A árvore está vazia.");
        }
        else{
            RBTNode<T> currentNode = this.root;
            Queue<RBTNode<T>> waitingQueue = new Queue<RBTNode<T>>();

            // Enfileirando a raiz em preparo para o loop
            waitingQueue.enqueue(currentNode);

            while (!waitingQueue.isEmpty()) {
                // Atribuir nó da frente da fila como nó atual
                currentNode = waitingQueue.getFront().getValue();

                // deletar efetivamente
                if(currentNode.getDeleted()){
                    deleteNode(currentNode);
                }
                // Tirar nó da fila
                waitingQueue.dequeue();
                // Adicionar novos nós na fila
                if (currentNode.getRight() != null) {
                    waitingQueue.enqueue(currentNode.getRight());
                }
                if (currentNode.getLeft() != null) {
                    waitingQueue.enqueue(currentNode.getLeft());
                }
            }
            // Exibir quebra de linha quando o processo acabar
            System.out.println();
        }
    }
}