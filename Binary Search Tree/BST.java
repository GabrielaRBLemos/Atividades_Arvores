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
}