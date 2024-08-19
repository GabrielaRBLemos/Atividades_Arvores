public class BST{
    private BSTNode root;

    public BSTNode getRoot() {
        return root;
    }

    public void setRoot(BSTNode root) {
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

    public void insert(int value){
        BSTNode newNode;
        newNode = new BSTNode(value);
        if (this.isEmpty() == true) {
            this.root = newNode;
        }
        else{
            BSTNode currentNode = this.root;
            while (true) {
                if (currentNode.getValue() > newNode.getValue()){
                    if (currentNode.getLeft() == null){
                        currentNode.setLeft(newNode);
                        System.err.println("Inserção Efetuada");
                        return;
                    }
                    else{
                        currentNode = currentNode.getLeft();
                    }
                }
                else{
                    if (currentNode.getRight() == null){
                        currentNode.setRight(newNode);
                        System.err.println("Inserção Efetuada");
                        return;
                    }
                    else{
                        currentNode = currentNode.getRight();
                    }
                }
            }
        }
    }

    private BSTNode search(int value){
        BSTNode currentNode = this.root;
        while (true) {
            if(currentNode.getValue() > value){
                currentNode = currentNode.getLeft();
            }
            else if(currentNode.getValue() < value){
                currentNode = currentNode.getRight();
            }
            else if(currentNode.getValue() == value){
                return currentNode;
            }
            else{
                return null;
            }
        }
    }

    public void contain(int value){
        if (this.search(value)==null) {
            System.err.println("Valor Não Encontrado na Árvore");
        }
        else{
            System.err.println("Valor Encontrado na Árvore");
        }
    }

    public void insertTwoAux(int value){
        if (this.isEmpty() == true) {
            this.root.setValue(value);
        }
        else{
            BSTNode currentNode = this.root;
            BSTNode parentNode = null;
            while (true) {
                if (currentNode.getValue() > value){
                    parentNode = currentNode;
                    currentNode = currentNode.getLeft();
                    if (currentNode.getLeft() == null){
                        parentNode.setValue(value);
                        System.err.println("Inserção Efetuada");
                        return;
                    }
                }
                else{
                    parentNode = currentNode;
                    currentNode = currentNode.getRight();
                    if (currentNode.getRight() == null){
                        parentNode.setValue(value);
                        System.err.println("Inserção Efetuada");
                        return;
                    }
                }
            }
        }
    }

    private BSTNode leftestNode(){
        BSTNode currentNode = this.root;
        while(currentNode.getLeft() != null){
            currentNode = currentNode.getLeft();
        }
        return currentNode;
    }

    private BSTNode rightestNode(){
            BSTNode currentNode = this.root;
            while(currentNode.getRight() != null){
                currentNode = currentNode.getRight();
            }
            return currentNode;
    }

    public void min(){
        int min = leftestNode().getValue();
        System.out.println(String.format("O Menor Valor Na Árvore: %d", min));
    }

    public void max(){
        int max = rightestNode().getValue();
        System.out.println(String.format("O Maior Valor Na Árvore: %d", max));
    }

}