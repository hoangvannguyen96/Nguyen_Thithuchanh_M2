package model;

public class Product {
    private long IdProduct;
    private String nameProduct;
    private float price;
    private int quantity;
    private String description;

    public Product() {
    }

    public Product(long idProduct, String nameProduct, float price, int quantity, String description) {
        IdProduct = idProduct;
        this.nameProduct = nameProduct;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
    }

    public long getIdProduct() {
        return IdProduct;
    }

    public void setIdProduct(long idProduct) {
        IdProduct = idProduct;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return getIdProduct()+","+getNameProduct()+","+getPrice()+","+getQuantity()+","+getDescription();
    }
}
