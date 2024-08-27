package ProductRegistry;

import BinarySearchTree.BST;

public class ProductRegistry {
    BST<Product> tree;

    public ProductRegistry() {
        tree = new BST<Product>();
    }

    public BST<Product> getTree() {
        return tree;
    }

    public void setTree(BST<Product> tree) {
        this.tree = tree;
    }

    public Product search(String searchCode){
        Product searchProduct = new Product(searchCode,null,null,0.0,0);
        return tree.lookup(searchProduct);
    }

    public void register(String ProductCode,String ProductDescription,String ProductSupplier,double ProductPrice,int ProductQntInStock){
        
        if (this.search(ProductCode)==null) {
            Product newProduct = new Product(ProductCode, ProductDescription, ProductSupplier, ProductPrice, ProductQntInStock);
            this.tree.insert(newProduct);
            System.out.println("Cadastro do produto efetuado!");
        }
        else{
            System.out.println("Esse código já está em uso.");
        }
    }

    public void alterValuePrice(String searchCode, double newPrice){
        Product result = this.search(searchCode);
        if (result == null) {
            System.out.println("Código Não Encontrado");
        }
        else{
            result.setPrice(newPrice);
        }
    }

    public void alterValueQntInStock(String searchCode, int newQntInStock){
        Product result = this.search(searchCode);
        if (result == null) {
            System.out.println("Código Não Encontrado");
        }
        else{
            result.setQntInStock(newQntInStock);
        }
    }

    public void printProductData(String searchCode){
        Product result = search(searchCode);
        if (result == null) {
            System.out.println("Código Não Encontrado");
        }
        else{
            System.out.println(result.toString());
        }
    }
}
