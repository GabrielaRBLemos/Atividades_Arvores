package BTree;

public class BNode {
    private int[] keys;
    private BNode[] children;
    private int numKeys;
    private boolean isLeaf;

    public BNode(int order, boolean isLeaf) {
        keys = new int[order - 1];
        children = new BNode[order];
        this.isLeaf = isLeaf;
        numKeys = 0;
    }

    public int[] getKeys() {
        return keys;
    }

    public void setKeys(int[] keys) {
        this.keys = keys;
    }

    public BNode[] getChildren() {
        return children;
    }

    public void setChildren(BNode[] children) {
        this.children = children;
    }

    public int getNumKeys() {
        return numKeys;
    }

    public void setNumKeys(int numKeys) {
        this.numKeys = numKeys;
    }

    public boolean isLeaf() {
        return isLeaf;
    }

    public void setLeaf(boolean isLeaf) {
        this.isLeaf = isLeaf;
    }

    public BNode getChildAt(int index) {
        if (index >= 0 && index < children.length) {
            return children[index];
        }
        return null;
    }

    public void setChildAt(int index, BNode child) {
        if (index >= 0 && index < children.length) {
            children[index] = child;
        }
    }

    public int getKeyAt(int index) {
        if (index >= 0 && index < this.numKeys) {
            return keys[index];
        }
        return -1;
    }

    public void setKeyAt(int index, int value) {
        if (index >= 0 && index < this.numKeys) {
            keys[index] = value;
        }
    }

    public int searchKey(int key) {
        int i = 0;
        while (i < this.numKeys && key > keys[i]) {
            i++;
        }
        return (i < this.numKeys && keys[i] == key) ? i : -1;
    }

    public void addKey(int key) {
        int i = numKeys - 1;

        // chave para a direita para fazer espaço para a nova chave
        while (i >= 0 && keys[i] > key) {
            keys[i + 1] = keys[i];
            i--;
        }
        keys[i + 1] = key;
        numKeys++;
    }

    public void removeKeyAt(int index) {
        if (index < 0 || index >= this.numKeys) {
            throw new IllegalArgumentException("Invalid index for key removal.");
        }
        for (int i = index; i < this.numKeys - 1; i++) {
            keys[i] = keys[i + 1];
        }
        keys[this.numKeys - 1] = -1;

        this.numKeys--;
    }

    public void addChild(BNode newChild) {
        if (isLeaf) {
            children[this.numKeys] = newChild;
        } else {
            int i = this.numKeys - 1;
            // ponteiros para a direita para abrir espaço para o novo ponteiro
            while (i >= 0 && keys[i] > newChild.keys[0]) {
                children[i + 1] = children[i];
                i--;
            }

            children[i + 1] = newChild;
        }

        numKeys++;
    }

    public void removeChildAt(int index) {
        if (index < 0 || index >= numKeys) {
            throw new IllegalArgumentException("Invalid index for pointer removal.");
        }

        // filhos para a esquerda para preencher o espaço criado pelo filho removido
        for (int i = index; i < numKeys - 1; i++) {
            children[i] = children[i + 1];
        }
        children[numKeys - 1] = null;
        numKeys--;
    }
}