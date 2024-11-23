package BTree;

public class BNode {
    private int[] keys;
    private int minDegree;
    private BNode[] children;
    private int numKeys;
    private boolean isLeaf;

    public BNode(int minDegree, boolean isLeaf) {
        this.minDegree = minDegree;
        this.isLeaf = isLeaf;
        this.keys = new int[2 * minDegree - 1];
        this.children = new BNode[2 * minDegree];
        this.numKeys = 0;
    }

    public boolean isLeaf(){
        return this.isLeaf;
    }

    public int[] getKeys() {
        return keys;
    }

    public void setKeys(int[] keys) {
        this.keys = keys;
    }

    public int getKeyAt(int index){
        return this.keys[index];
    }

    public void setKeyAt(int key, int index){
        this.keys[index] = key;
    }

    public BNode getChildAt(int index){
        return this.children[index];
    }

    public void setChildAt(BNode child, int index){
        this.children[index] = child;
    }

    public int getNumKeys(){
        return this.numKeys;
    }

    public void setNumKeys(int numKeys){
        this.numKeys = numKeys;
    }

    public int getMinDegree() {
        return minDegree;
    }

    public void setMinDegree(int minDegree) {
        this.minDegree = minDegree;
    }

    public BNode[] search(int key, BNode parent) {
        int i = 0;
        while (i < numKeys && key > keys[i])
            i++;
        if (i < numKeys && keys[i] == key) {
            return new BNode[] { this, parent };
        }
        if (isLeaf) {
            return null;
        }
        return children[i].search(key, this);
    }

    public int searchKeyIndex(int targetKey) {
        for (int i = 0; i < numKeys; i++) {
            if (keys[i] == targetKey) {
                return i;
            }
        }
        return -1;
    }

    public int searchChildIndex(BNode targetChild) {
        for (int i = 0; i < numKeys+1; i++) {
            if (children[i] == targetChild) {
                return i;
            }
        }
        return -1;
    }

    public void insertNonFull(int key) {
        int i = numKeys - 1;
        if (isLeaf) {
            while (i >= 0 && key < keys[i]) {
                keys[i + 1] = keys[i];
                i--;
            }
            keys[i + 1] = key;
            numKeys++;
        } else {
            while (i >= 0 && key < keys[i])
                i--;
            i++;

            if (children[i].numKeys == 2 * minDegree - 1) {
                splitChild(i, children[i]);
                if (key > keys[i])
                    i++;
            }
            children[i].insertNonFull(key);
        }
    }

    public void splitChild(int index, BNode fullChildNode) {
        BNode newNode = new BNode(fullChildNode.minDegree, fullChildNode.isLeaf);
        newNode.numKeys = minDegree - 1;

        // Mover as últimas (minDegree - 1) chaves do nó filho completo para o novo nó
        for (int j = 0; j < minDegree - 1; j++)
            newNode.keys[j] = fullChildNode.keys[j + minDegree];

        // Se o filho completo não for uma folha, mover também os ponteiros dos filhos
        if (!fullChildNode.isLeaf) {
            for (int j = 0; j < minDegree; j++)
                newNode.children[j] = fullChildNode.children[j + minDegree];
        }

        fullChildNode.numKeys = minDegree - 1;

        // Deslocar os filhos do nó atual para abrir espaço para o novo filho
        for (int j = numKeys; j >= index + 1; j--)
            children[j + 1] = children[j];

        children[index + 1] = newNode;

        // Deslocar as chaves no nó atual para abrir espaço para a chave do meio
        for (int j = numKeys - 1; j >= index; j--)
            keys[j + 1] = keys[j];

        // Promover a chave do meio para o nó atual
        keys[index] = fullChildNode.keys[minDegree - 1];
        numKeys++;
    }

    public void printInOrder() {
        for (int i = 0; i < numKeys; i++) {
            if (!isLeaf)
                children[i].printInOrder();
            System.out.print(keys[i] + " ");
        }
        if (!isLeaf)
            children[numKeys].printInOrder();
    }

    public void printKeys(){
        for (int i = 0; i < this.numKeys; i++) {
            System.out.print(this.keys[i] + " ");
        }
    }

    public void deleteKey(int value){
        int index = this.searchKeyIndex(value);
        int[] newKeys = new int[2 * minDegree - 1];

        // Antes do elemento
        for (int i = 0; i < index; i++) {
            newKeys[i] = this.keys[i];
        }

        // Depois do elemento
        for (int i = index+1; i < keys.length; i++) {
            newKeys[i - 1] = this.keys[i];
        }

        this.numKeys -= 1;
        this.keys = newKeys;
    }

}