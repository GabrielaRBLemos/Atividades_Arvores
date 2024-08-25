package ProductRegistry;
public class Product {
    private String code, description, supplier;
    private double price;
    private int qntInStock;
    
    public Product(String code, String description, String supplier, double price, int qntInStock) {
        this.code = code;
        this.description = description;
        this.supplier = supplier;
        this.price = price;
        this.qntInStock = qntInStock;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getSupplier() {
        return supplier;
    }
    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public int getQntInStock() {
        return qntInStock;
    }
    public void setQntInStock(int qntInStock) {
        this.qntInStock = qntInStock;
    }
    @Override
    public String toString() {
        return "Código:" + code + ", Descrição:" + description + ", Fornecedor:" + supplier + ", Preço:" + price
                + ", Quantidade no estoque:" + qntInStock;
    }

}
