package ProductRegistry;

import BinarySearchTree.BST;

public class ProductRegistry {
    BST tree = new BST();

    public ProductRegistry() {
    }

    public BST getTree() {
        return tree;
    }

    public void setTree(BST tree) {
        this.tree = tree;
    }
}
