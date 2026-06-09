package se.jensen.erik.cloudstoreproject.cart;

import se.jensen.erik.cloudstoreproject.product.model.Product;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Cart {

    private Map<Long, CartItem> items = new HashMap<>();

    public void addProduct(Product product, int quantity){
        if (items.containsKey(product.getId())) {
            CartItem existing = items.get(product.getId());
            existing.setQuantity(existing.getQuantity() + quantity);
        } else{
            items.put(product.getId(),
                    new CartItem(product.getId(), product.getTitle(), product.getPrice(), quantity));
        }
    }

    public void removeProduct(long productId){
        items.remove(productId);
    }

    public Collection<CartItem> getItems(){
        return items.values();
    }

    public double getTotal(){
        return items.values().stream()
                .mapToDouble(i -> i.getPrice() * i.getQuantity())
                .sum();
    }

    public void clear() {
        items.clear();
    }
}
