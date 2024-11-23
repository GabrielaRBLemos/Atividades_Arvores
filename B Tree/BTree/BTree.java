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

    public void check(int key) {
        BNode node = this.search(key)[0];

        if (node != null) {
            System.out.println("Valor encontrado na árvore.");
        } else {
            System.out.println("Valor não encontrado na árvore.");
        }
    }

    private BNode[] search(int key) {
        if (this.isEmpty()) {
            return null;
        } 
        else {
            return root.search(key, null);  // raiz não tem pai
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

    // -------------- REMOVE QUARANTINE --------------
    public void remove(int key){
        BNode node,parent;
        node = this.search(key)[0];
        parent = this.search(key)[1];
        if (node == null || this.isEmpty()) {
            System.out.println("Valor não está presente na árvore.");
        }
        else if (node.isLeaf()) {
            if (node.getNumKeys() > node.getMinDegree() - 1) {
                node.deleteKey(key);
            }
            else{
                int nodeIndex = parent.searchChildIndex(node),siblingKey, parentKey;
                int[] newNodeKeys = new int[ 2 * minDegree - 1], newParentKeys = new int[ 2 * minDegree - 1];
                BNode leftNode = parent.getChildAt(nodeIndex-1), rightNode = parent.getChildAt(nodeIndex+1);
                if (leftNode!=null && leftNode.getNumKeys() > leftNode.getMinDegree() - 1) {
                    siblingKey = leftNode.getKeyAt(leftNode.getNumKeys()-1);
                    parentKey = parent.getKeyAt(0);
                    newNodeKeys[0] = parentKey;
                    for (int i = 1; i < node.getKeys().length ; i++) {
                        newNodeKeys[i] = node.getKeyAt(i-1);
                    }
                    newParentKeys[0] = siblingKey;
                    for (int i = 1; i < node.getKeys().length ; i++) {
                        newParentKeys[i] = parent.getKeyAt(i-1);
                    }
                }
                else if (rightNode != null && rightNode.getNumKeys() > rightNode.getMinDegree() - 1) {
                    siblingKey = rightNode.getKeyAt(0);
                    parentKey = parent.getKeyAt(nodeIndex);
                    
                    newNodeKeys[node.getNumKeys()-1] = parentKey;
                    for (int i = 0; i < node.getKeys().length - 1; i++) {
                        newNodeKeys[i+1] = node.getKeyAt(i);
                    }

                    newParentKeys[0] = siblingKey;
                    for (int i = 1; i < node.getKeys().length ; i++) {
                        newParentKeys[i] = parent.getKeyAt(i-1);
                    }
                }
                else {
                    if (leftNode != null) {
                        mergeNodes(leftNode, node, parent, nodeIndex - 1);
                    } else {
                        mergeNodes(node, rightNode, parent, nodeIndex);
                    }
                }
            }
        }
        else{
            //TODO: Remove internal nodes
            System.out.println("Não Implementado - Nó interno");
        }
        System.out.println(key + " Deletado");
    }

    private void mergeNodes(BNode leftNode, BNode rightNode, BNode parent, int parentIndex) {
        // o parent key node esquerdo
        leftNode.setKeyAt(parent.getKeyAt(parentIndex), leftNode.getNumKeys());
        leftNode.setNumKeys(leftNode.getNumKeys() + 1);
    
        // todas chaves e filhos para nó da esquerda
        for (int i = 0; i < rightNode.getNumKeys(); i++) {
            leftNode.setKeyAt(rightNode.getKeyAt(i), leftNode.getNumKeys());
            leftNode.setNumKeys(leftNode.getNumKeys() + 1);
        }
    
        if (!rightNode.isLeaf()) {
            for (int i = 0; i <= rightNode.getNumKeys(); i++) {
                leftNode.setChildAt(rightNode.getChildAt(i), leftNode.getNumKeys());
            }
        }
    
        // Remova a chave do pai e a referência do filho à direita
        for (int i = parentIndex; i < parent.getNumKeys() - 1; i++) {
            parent.setKeyAt(parent.getKeyAt(i + 1), i);
            parent.setChildAt(parent.getChildAt(i + 2), i + 1);
        }
        parent.setNumKeys(parent.getNumKeys() - 1);

        rightNode.setNumKeys(0);
    }

    // ---------- END  OF REMOVE QUARANTINE ----------

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
            System.out.println();
        }
    }

}