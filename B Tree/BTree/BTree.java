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
        return (this.isEmpty()) ? null : root.search(key);
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
        //TODO: implement removal of key
        System.out.println("Remover chave não implementado");
    }

    public void printInOrder() {
        if (this.isEmpty()) {
            System.out.println("Árvore Vazia")
        }
        else{
            root.printInOrder();
            System.out.println();
        }
    }

    public void printLevelOrder() {
        if (this.isEmpty()) {
            System.out.println("Árvore Vazia")
        }
    }

}