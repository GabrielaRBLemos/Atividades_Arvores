package BTree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BTree {
    private BNode root;
    private int order;

    public BTree(int order) {
        this.order = order;
        this.root = new BNode(order, true);
    }

    public BNode getRoot() {
        return root;
    }

    public void setRoot(BNode root) {
        this.root = root;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public boolean isEmpty() {
        return root == null || root.getNumKeys() == 0;
    }

//
// --------Começo dos métodos relacionados a inserção--------
//

    // Método para inserir um valor na árvore B
    public void insert(int key) {
        BNode root = getRoot();
        if (root.getNumKeys() == order - 1) {
            // Se a raiz estiver cheia, precisa dividir
            BNode newRoot = new BNode(order, false);
            // Nova raiz, divide a antiga raiz
            newRoot.setChildAt(0, root);
            splitChild(newRoot, 0);
            setRoot(newRoot);
        }
        insertNonFull(getRoot(), key);
    }

        // Método para inserir uma chave em um nó não cheio
        private void insertNonFull(BNode node, int key) {
            int i = node.getNumKeys() - 1;
            if (node.isLeaf()) {
                while (i >= 0 && key < node.getKeyAt(i)) {
                    i--;
                }
                node.setKeyAt(i + 1, key);
                node.setNumKeys(node.getNumKeys() + 1);
            } else {
                while (i >= 0 && key < node.getKeyAt(i)) {
                    i--;
                }
                i++;
                BNode child = node.getChildAt(i);
                if (child.getNumKeys() == order - 1) {
                    splitChild(node, i);
                    if (key > node.getKeyAt(i)) {
                        i++;
                    }
                }
                insertNonFull(node.getChildAt(i), key);
            }
        }

    // Função auxiliar para dividir um nó quando ele está cheio
    private void splitChild(BNode parent, int index) {
        BNode fullNode = parent.getChildAt(index);
        BNode newNode = new BNode(order, fullNode.isLeaf());
        int median = fullNode.getKeyAt(order / 2);

        // chave do meio para o nó pai
        parent.setKeyAt(index, median);

        // segunda parte do fullNode para newNode
        for (int i = order / 2 + 1; i < order - 1; i++) {
            newNode.setKeyAt(i - (order / 2 + 1), fullNode.getKeyAt(i));
            fullNode.setKeyAt(i, 0);
        }

        if (!fullNode.isLeaf()) {
            for (int i = order / 2 + 1; i < order; i++) {
                newNode.setChildAt(i - (order / 2 + 1), fullNode.getChildAt(i));
                fullNode.setChildAt(i, null);
            }
        }

        fullNode.setNumKeys(order / 2);
        newNode.setNumKeys(order / 2);

        // Insert the new node as a child of the parent
        for (int i = parent.getNumKeys(); i > index; i--) {
            parent.setChildAt(i + 1, parent.getChildAt(i));
        }

        parent.setChildAt(index + 1, newNode);
        parent.setNumKeys(parent.getNumKeys() + 1);
    }
//
// --------Fim de métodos relacionados a inserção--------
//

//
// --------métodos relacionados a remoção--------
//
    public void remove(int key) {
        if (isEmpty()) {
            System.out.println("A árvore está vazia.");
            return;
        }

        removeHelper(root, key);

        // Se a raiz ficar com apenas 1 chave, e não for uma folha, o filho único da raiz se torna a nova raiz
        if (root.getNumKeys() == 0 && !root.isLeaf()) {
            root = root.getChildAt(0);
        }
    }
    
    private void removeHelper(BNode node, int key) {
        int index = node.searchKey(key);

        // Caso 1: A chave está em um nó folha
        if (node.isLeaf()) {
            if (index >= 0) {
                // chave encontrada, remover
                node.removeKeyAt(index);
            }
        } else { // Caso 2: A chave está em um nó interno
            if (index >= 0) {
                // A chave está no nó, precisamos de um substituto (menor chave à direita ou maior chave à esquerda)
                BNode child = node.getChildAt(index + 1);
                if (child.getNumKeys() >= order / 2) {
                    // Substitui a chave pela menor chave do filho à direita
                    replaceWithSuccessor(node, index);
                    removeHelper(child, node.getKeyAt(index));
                } else {
                    // Substitui pela maior chave do filho à esquerda
                    child = node.getChildAt(index);
                    if (child.getNumKeys() >= order / 2) {
                        replaceWithPredecessor(node, index);
                        removeHelper(child, node.getKeyAt(index));
                    } else {
                        mergeChildren(node, index);
                        removeHelper(child, key);  // Continua a remoção após a fusão
                    }
                }
            } else {
                // A chave não está no nó, recursivamente chama removeHelper para o filho adequado
                BNode child = node.getChildAt(index + 1);
                if (child.getNumKeys() < order / 2) {
                    BNode sibling = (index + 1 < node.getNumKeys()) ? node.getChildAt(index + 2) : node.getChildAt(index - 1);
                    if (sibling.getNumKeys() >= order / 2) {
                        // Se o irmão tem chaves suficientes, empresta uma chave
                        borrowFromSibling(node, index);
                        child = node.getChildAt(index + 1);
                    } else {
                        // Caso contrário, funde o nó com o irmão
                        mergeChildren(node, index);
                        child = node.getChildAt(index);
                    }
                }
                removeHelper(child, key);
            }
        }
    }

    private void replaceWithSuccessor(BNode node, int index) {
        BNode child = node.getChildAt(index + 1);
        while (!child.isLeaf()) {
            child = child.getChildAt(0);  // filho mais à esquerda
        }
        int successor = child.getKeyAt(0);
        node.setKeyAt(index, successor);
    }

    private void replaceWithPredecessor(BNode node, int index) {
        BNode child = node.getChildAt(index);
        while (!child.isLeaf()) {
            child = child.getChildAt(child.getNumKeys());  // filho mais à direita
        }
        int predecessor = child.getKeyAt(child.getNumKeys() - 1);
        node.setKeyAt(index, predecessor);
    }

    private void mergeChildren(BNode node, int index) {
        BNode child = node.getChildAt(index);
        BNode sibling = node.getChildAt(index + 1);

        // Move a chave do nó pai para o filho
        child.addKey(node.getKeyAt(index));

        // Move todas as chaves do irmão para o filho
        for (int i = 0; i < sibling.getNumKeys(); i++) {
            child.addKey(sibling.getKeyAt(i));
        }

        // Move todos os ponteiros de filhos do irmão para o filho
        if (!child.isLeaf()) {
            for (int i = 0; i <= sibling.getNumKeys(); i++) {
                child.addChild(sibling.getChildAt(i));
            }
        }

        // Remove a chave do nó pai e remove o irmão
        node.removeKeyAt(index);
        node.removeChildAt(index + 1);
    }

    private void borrowFromSibling(BNode node, int index) {
        BNode child = node.getChildAt(index);
        BNode sibling = (index + 1 < node.getNumKeys()) ? node.getChildAt(index + 1) : node.getChildAt(index - 1);

        if (index + 1 < node.getNumKeys()) {  // Irmão à direita
            child.addKey(node.getKeyAt(index));
            node.setKeyAt(index, sibling.getKeyAt(0));
            sibling.removeKeyAt(0);
            if (!sibling.isLeaf()) {
                child.addChild(sibling.getChildAt(0));
                sibling.removeChildAt(0);
            }
        } else {  // Irmão à esquerda
            child.addKey(node.getKeyAt(index - 1));
            node.setKeyAt(index - 1, sibling.getKeyAt(sibling.getNumKeys() - 1));
            sibling.removeKeyAt(sibling.getNumKeys() - 1);
            if (!sibling.isLeaf()) {
                child.addChild(sibling.getChildAt(sibling.getNumKeys()));
                sibling.removeChildAt(sibling.getNumKeys());
            }
        }
    }
//
// --------Fim de métodos relacionados a remoção--------
//

        // Exibir a maior chave
        public void displayMax() {
            BNode maxNode = findMax(root);
            if (maxNode != null) {
                System.out.println("Maior chave: " + maxNode.getKeyAt(maxNode.getNumKeys() - 1));
            } else {
                System.out.println("A árvore está vazia.");
            }
        }
    
        private BNode findMax(BNode node) {
            if (node == null) return null;
            while (!node.isLeaf()) {
                node = node.getChildAt(node.getNumKeys());
            }
            return node;
        }
    
        // Exibir a menor chave
        public void displayMin() {
            BNode minNode = findMin(root);
            if (minNode != null) {
                System.out.println("Menor chave: " + minNode.getKeyAt(0));
            } else {
                System.out.println("A árvore está vazia.");
            }
        }
    
        private BNode findMin(BNode node) {
            if (node == null) return null;
            while (!node.isLeaf()) {
                node = node.getChildAt(0);
            }
            return node;
        }

    // Função para calcular a altura da árvore
    public int getHeight() {
        if (this.isEmpty()) {
            return 0;
        }

        int height = 0;
        Queue<BNode> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);

        // Passeio por nível
        while (!nodeQueue.isEmpty()) {
            int nodeCount = nodeQueue.size();
            height++;

            while (nodeCount > 0) {
                BNode node = nodeQueue.poll();
                if (!node.isLeaf()) {
                    for (int i = 0; i <= node.getNumKeys(); i++) {
                        BNode child = node.getChildAt(i);
                        if (child != null) {
                            nodeQueue.add(child);
                        }
                    }
                }
                nodeCount--;
            }
        }

        return height;
    }

    // Buscar chave
    public void searchKey(int key) {
        if (isEmpty()) {
            System.out.println("A árvore está vazia.");
            return;
        }

        BNode currentNode = root;

        while (currentNode != null) {
            int i = 0;

            while (i < currentNode.getNumKeys() && key > currentNode.getKeyAt(i)) {
                i++;
            }
            if (i < currentNode.getNumKeys() && key == currentNode.getKeyAt(i)) {
                System.out.println("Chave " + key + " encontrada na árvore.");
                System.out.println("Posição dentro do nó: " + i);
                return;
            }

            if (currentNode.isLeaf()) {
                break;  // Chave não encontrada
            }

            // Segue para o filho adequado
            currentNode = currentNode.getChildAt(i);
        }

        System.out.println("Chave " + key + " não encontrada na árvore.");
    }

        // Passeio por Nível
        public void displayByLevel() {
            if (isEmpty()) {
                System.out.println("A árvore está vazia.");
                return;
            }
    
            Queue<BNode> queue = new LinkedList<>();
            queue.add(root);

            while (!queue.isEmpty()) {
                int nodeCount = queue.size();

                while (nodeCount > 0) {
                    BNode node = queue.poll();

                    for (int i = 0; i < node.getNumKeys(); i++) {
                        System.out.print(node.getKeyAt(i) + " ");
                    }
                    System.out.println();

                    if (!node.isLeaf()) {
                        for (int i = 0; i <= node.getNumKeys(); i++) {
                            BNode child = node.getChildAt(i);
                            if (child != null) {
                                queue.add(child);
                            }
                        }
                    }
                    nodeCount--;
                }
                System.out.println();
            }
        }

    // Passeio por ordem
    public void displayInOrder() {
        if (isEmpty()) {
            System.out.println("A árvore está vazia.");
            return;
        }

        Stack<BNode> stack = new Stack<>();
        BNode currentNode = root;

        while (currentNode != null || !stack.isEmpty()) {

            while (currentNode != null) {
                stack.push(currentNode);
                currentNode = currentNode.getChildAt(0);
            }

            currentNode = stack.pop();
            for (int i = 0; i < currentNode.getNumKeys(); i++) {
                System.out.print(currentNode.getKeyAt(i) + " ");
            }

            if (!currentNode.isLeaf()) {
                currentNode = currentNode.getChildAt(currentNode.getNumKeys());
            } else {
                currentNode = null;
            }
        }
        System.out.println();
    }

}