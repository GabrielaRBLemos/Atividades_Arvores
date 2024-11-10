package BTree;
import Stack.Stack;

public class BTree <T extends Comparable<T>>{
    private BNode<T> root;
    private int degree;    

    public BTree(int degree) {
        if (isPowerOfTwo(degree + 1)&& degree >=2) {
            this.degree = degree;
            this.root = null;
        }
        else{
            throw new IllegalArgumentException("O grau deve ser uma potência de 2 e igual ou maior que 2.");
        }
    }
    public BNode<T> getRoot() {
        return root;
    }
    public void setRoot(BNode<T> root) {
        this.root = root;
    }
    public int getDegree() {
        return degree;
    }
    public void setDegree(int degree) {
        this.degree = degree;
    }

    private boolean isPowerOfTwo(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }

    public boolean isEmpty() {
        return this.root == null;
    }

    public void insert(T value){
        if (this.isEmpty()) {
            this.root = new BNode<T>(degree);
            this.root.setValueAt(0, value);
            System.out.println("Inserção Efetuada!");
        }
        else{
            BNode<T> currentNode = this.root;
            int currentIndex;
            Stack<BNode<T>> nodeStack = new Stack<BNode<T>>();

            //Localizando a folha
            while (!currentNode.isLeaf()) {
                for (currentIndex = 0; currentIndex < degree; currentIndex++) {
                    if (currentNode.getValueAt(currentIndex)==null) {
                        break;
                    }
                }
                if (value.compareTo(currentNode.getValueAt(currentIndex)) == 0) {
                    //valor repetido
                    System.out.println("Valores não podem ser repetidos");
                }
                else if (value.compareTo(currentNode.getValueAt(currentIndex)) < 0) {
                    break;
                }
                nodeStack.push(currentNode);
                currentNode = currentNode.getPointerAt(currentIndex)
            }

            // int i = leaf.getDegree() - 1;
            // while (i >= 0 && leaf.getValueAt(i).compareTo(value) > 0) {
            //     leaf.setValueAt(i + 1, leaf.getValueAt(i));
            //     i--;
            // }
            // leaf.setValueAt(i + 1, value);
            // leaf.getValueArray().add(null);
        }
        return;
    }

}