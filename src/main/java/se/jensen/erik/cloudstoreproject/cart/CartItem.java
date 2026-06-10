package se.jensen.erik.cloudstoreproject.cart;

public class CartItem {

    private long productId;
    private String title;
    private Double price;
    private int quantity;

    public CartItem(long productId, String title, Double price, int quantity) {
        this.productId = productId;
        this.title = title;
        this.price = price;
        this.quantity = quantity;
    }
    public long getProductId() {
        return productId;
    }
    public String getTitle() {
        return title;
    }
    public Double getPrice() {
        return price;
    }
    public int getQuantity() {
        return quantity;
    }


    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
