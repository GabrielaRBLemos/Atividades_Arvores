package BTree;

import Queue.Queue;

public class BTree {
    private BNode root;
    private int minDegree;

    public BTree(int minDegree) {
        this.minDegree = minDegree;
        this.root = null;
    }

    public boolean isEmpty(){
        return root == null;
    }

    public void insert(int key) {
        if (this.isEmpty()) {
            root = new BNode(minDegree, true);
            root.setKeyAt(key, 0);
            root.setNumKeys(1);
        } else {
            if (root.getNumKeys() == 2 * minDegree - 1) {
                BNode newRoot = new BNode(minDegree, false);
                newRoot.setChildAt(root, 0);
                newRoot.splitChild(0, root);
                root = newRoot;
            }
            root.insertNonFull(key);
        }
    }

    public BNode search(int key) {
        if (this.isEmpty()) {
            return null;
        } else {
            return root.search(key);
        }
    }    

    public void displayMax() {
        if(isEmpty()){
            System.out.println("Árvore vazia.");
        }
        else{
            BNode node = root;
            while (!node.isLeaf()) {
                node = node.getChildAt(node.getNumKeys());
            }
            System.out.println("Maior chave: " + node.getKeyAt(node.getNumKeys() - 1));
        }
    }

    public void displayMin() {
        if(isEmpty()){
            System.out.println("Árvore vazia.");
        }
        else{
            BNode node = root;
            while (!node.isLeaf()) {
                node = node.getChildAt(0);
            }
            System.out.println("Menor chave: " + node.getKeyAt(node.getNumKeys() - 1));
        }
    }

    public int calculateHeight() {
        return calculateHeight(root);
    }

    private int calculateHeight(BNode node) {
        if (node == null) return 0;
        if (node.isLeaf()) return 1;
        return 1 + calculateHeight(node.getChildAt(0));
    }

    public void remove(int key) {
        if (isEmpty()) {
            System.out.println("Árvore Vazia");
            return;
        }
        else{
            BNode node = root.search(key);
            if (node == null) {
                System.out.println("Valor não encontrado na árvore.");
                return;
            }
            if (node.isLeaf()) {
                if (node.getNumKeys()-1 >= node.getMinDegree()) {
                    node.setKeys(newKeys(node, key));
                }
                else{
                    handleChildUnderflow(node, key);
                }
            }
            else{
                removeInternalNode(node, key);
            }
        }
    }

    private int[] newKeys(BNode node, int keyToDelete) {
        int indexToDelete = node.searchKeyIndex(keyToDelete);
        int[] result = new int[2 * minDegree - 1];

        // Antes do elemento
        for (int i = 0; i < indexToDelete; i++) {
            result[i] = node.getKeyAt(i);
        }

        // Depois do elemento
        for (int i = indexToDelete + 1; i < 2 * minDegree - 1; i++) {
            result[i - 1] = node.getKeyAt(i);
        }

        return result;
    }

    private void handleChildUnderflow(BNode node, int key) {
        int indexToDelete = node.searchKeyIndex(key);

        if (indexToDelete > 0 && node.getChildAt(indexToDelete - 1).getNumKeys() > minDegree - 1) {
            borrowFromLeftSibling(node, indexToDelete);
        }

        else if (indexToDelete < node.getNumKeys() - 1 && node.getChildAt(indexToDelete + 1).getNumKeys() > minDegree - 1) {
            borrowFromRightSibling(node, indexToDelete);
        }

        else {
            if (indexToDelete > 0) {
                mergeWithLeftSibling(node, indexToDelete);
            } else {
                mergeWithRightSibling(node, indexToDelete);
            }
        }
    }

    private void borrowFromLeftSibling(BNode node, int indexToDelete) {
        BNode leftSibling = node.getChildAt(indexToDelete - 1);
        BNode currentNode = node.getChildAt(indexToDelete);
    
        // chave do pai para o nó atual
        currentNode.setKeyAt(node.getKeyAt(indexToDelete - 1), 0);
        currentNode.setNumKeys(currentNode.getNumKeys() + 1);
    
        // maior chave do irmão à esquerda para o pai
        node.setKeyAt(leftSibling.getKeyAt(leftSibling.getNumKeys() - 1), indexToDelete - 1);

        leftSibling.setNumKeys(leftSibling.getNumKeys() - 1);
    }
    
    private void borrowFromRightSibling(BNode node, int indexToDelete) {
        BNode rightSibling = node.getChildAt(indexToDelete + 1);
        BNode currentNode = node.getChildAt(indexToDelete);
    
        // chave do pai para o nó atual
        currentNode.setKeyAt(node.getKeyAt(indexToDelete), currentNode.getNumKeys());
        currentNode.setNumKeys(currentNode.getNumKeys() + 1);
    
        // menor chave do irmão à direita para o pai
        node.setKeyAt(rightSibling.getKeyAt(0), indexToDelete);

        for (int i = 1; i < rightSibling.getNumKeys(); i++) {
            rightSibling.setKeyAt(rightSibling.getKeyAt(i), i - 1);
        }
    
        rightSibling.setNumKeys(rightSibling.getNumKeys() - 1);
    }
    
    private void mergeWithLeftSibling(BNode node, int indexToDelete) {
        BNode leftSibling = node.getChildAt(indexToDelete - 1);
        BNode currentNode = node.getChildAt(indexToDelete);
    
        // chave do pai para o irmão à esquerda
        leftSibling.setKeyAt(node.getKeyAt(indexToDelete - 1), leftSibling.getNumKeys());
        leftSibling.setNumKeys(leftSibling.getNumKeys() + 1);
    
        // todas as chaves do nó atual para o irmão à esquerda
        for (int i = 0; i < currentNode.getNumKeys(); i++) {
            leftSibling.setKeyAt(currentNode.getKeyAt(i), leftSibling.getNumKeys());
            leftSibling.setNumKeys(leftSibling.getNumKeys() + 1);
        }
    
        // remover o nó
        node.setChildAt(null, indexToDelete);
    
        for (int i = indexToDelete; i < node.getNumKeys(); i++) {
            node.setChildAt(node.getChildAt(i + 1), i);
        }
    
        node.setNumKeys(node.getNumKeys() - 1);
    }
    
    private void mergeWithRightSibling(BNode node, int indexToDelete) {
        BNode rightSibling = node.getChildAt(indexToDelete + 1);
        BNode currentNode = node.getChildAt(indexToDelete);
    
        // chave do pai para o nó atual
        currentNode.setKeyAt(node.getKeyAt(indexToDelete), currentNode.getNumKeys());
        currentNode.setNumKeys(currentNode.getNumKeys() + 1);
    
        // todas as chaves do irmão à direita para o nó atual
        for (int i = 0; i < rightSibling.getNumKeys(); i++) {
            currentNode.setKeyAt(rightSibling.getKeyAt(i), currentNode.getNumKeys());
            currentNode.setNumKeys(currentNode.getNumKeys() + 1);
        }
    
        // remover nó
        node.setChildAt(null, indexToDelete + 1);

        for (int i = indexToDelete + 1; i < node.getNumKeys(); i++) {
            node.setChildAt(node.getChildAt(i + 1), i);
        }
    
        node.setNumKeys(node.getNumKeys() - 1);
    }

    private void removeInternalNode(BNode node, int key) {
        int indexToDelete = node.searchKeyIndex(key);

        // chave está no nó atual, e filho com chave é folha
        if (node.getChildAt(indexToDelete).isLeaf()) {

            if (node.getChildAt(indexToDelete).getNumKeys() > minDegree - 1) {
                node.setKeys(newKeys(node, key));
            } else {
                handleChildUnderflow(node, indexToDelete);
            }
        } else {
            // chave está no nó atual, mas filho com chave é um nó interno

            // predecessor
            if (indexToDelete > 0 && node.getChildAt(indexToDelete - 1).getNumKeys() > minDegree - 1) {
                int predecessorKey = getPredecessorKey(node, indexToDelete);
                node.setKeyAt(predecessorKey, indexToDelete);
                removeInternalNode(node.getChildAt(indexToDelete - 1), predecessorKey);
            }

            // successor
            else if (indexToDelete < node.getNumKeys() - 1 && node.getChildAt(indexToDelete + 1).getNumKeys() > minDegree - 1) {
                int successorKey = getSuccessorKey(node, indexToDelete);
                node.setKeyAt(successorKey, indexToDelete);
                removeInternalNode(node.getChildAt(indexToDelete + 1), successorKey);
            }

            // unir com irmão
            else {
                if (indexToDelete > 0) {
                    mergeWithLeftSibling(node, indexToDelete);
                    removeInternalNode(node.getChildAt(indexToDelete - 1), key);
                } else {
                    mergeWithRightSibling(node, indexToDelete);
                    removeInternalNode(node.getChildAt(indexToDelete), key);
                }
            }
        }
    }

    private int getPredecessorKey(BNode node, int index) {
        BNode childNode = node.getChildAt(index);
        while (!childNode.isLeaf()) {
            childNode = childNode.getChildAt(childNode.getNumKeys());
        }
        return childNode.getKeyAt(childNode.getNumKeys() - 1);
    }

    private int getSuccessorKey(BNode node, int index) {
        BNode childNode = node.getChildAt(index + 1);
        while (!childNode.isLeaf()) {
            childNode = childNode.getChildAt(0);
        }
        return childNode.getKeyAt(0);
    }

    public void printInOrder() {
        if (this.isEmpty()) {
            System.out.println("Árvore Vazia");
        }
        else{
            root.printInOrder();
            System.out.println();
        }
    }

    public void printLevelOrder() {
        if (this.isEmpty()) {
            System.out.println("Árvore Vazia");
        }
        else{
            Queue<BNode> queue = new Queue<BNode>();
            BNode node;
            BNode child;
            queue.enqueue(this.root);

            while (!queue.isEmpty()) {
                node = queue.dequeue();
                node.printKeys();
                for (int i = 0; i <= node.getNumKeys(); i++) {
                    child = node.getChildAt(i);
                    if (child != null) {
                        queue.enqueue(child);
                    }
                }
            }
        }
    }

}