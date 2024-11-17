package BTree;

import Queue.Queue;

public class BTree {
    private BNode root;
    private int minDegree;

    public BTree(int minDegree) {
        this.minDegree = minDegree;
        this.root = null;
    }

    public void insert(int key) {
        if (root == null) {
            root = new BNode(minDegree, true);
            root.keys[0] = key;
            root.numKeys = 1;
        } else {
            if (root.numKeys == 2 * minDegree - 1) {
                BNode newRoot = new BNode(minDegree, false);
                newRoot.children[0] = root;
                newRoot.splitChild(0, root);
                root = newRoot;
            }
            root.insertNonFull(key);
        }
    }

    public BNode search(int key) {
        return (root == null) ? null : root.search(key);
    }

    public void printBTree() {
        if (root != null)
            root.printInOrder();
        System.out.println();
    }

    public void displayMax() {
        if (root != null) {
            BNode node = root;
            while (!node.isLeaf) {
                node = node.children[node.numKeys];
            }
            System.out.println("Maior chave: " + node.keys[node.numKeys - 1]);
        } else {
            System.out.println("Árvore vazia.");
        }
    }

    public void displayMin() {
        if (root != null) {
            System.out.println("Menor chave: " + root.keys[0]);
        } else {
            System.out.println("Árvore vazia.");
        }
    }

    public int calculateHeight() {
        return calculateHeight(root);
    }

    private int calculateHeight(BNode node) {
        if (node == null) return 0;
        if (node.isLeaf) return 1;
        return 1 + calculateHeight(node.children[0]);
    }

    public void remove(int key) {
        //TODO: remove node from tree
        System.out.println("Remover não implementado.");
    }

    public void printLevelOrder() {
        //TODO: print level order
        System.out.println("Passeio por nível não implementado");
    }

}