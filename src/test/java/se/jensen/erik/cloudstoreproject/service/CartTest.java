package se.jensen.erik.cloudstoreproject.service;

import org.junit.jupiter.api.Test;
import se.jensen.erik.cloudstoreproject.cart.Cart;
import se.jensen.erik.cloudstoreproject.cart.CartItem;
import se.jensen.erik.cloudstoreproject.product.model.Product;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Ensures shopping logic is correct
// Prevents silent checkout bugs
class CartTest {

    @Test
    void addProduct_shouldIncreaseQuantity_whenProductAlreadyExists() {

        // Arrange
        Cart cart = new Cart();

        Product product = new Product(
                1L,
                "Phone",
                100.0,
                "desc",
                "cat",
                "img",
                null
        );

        // Act
        cart.addProduct(product, 1);
        cart.addProduct(product, 2);

        // Assert
        assertEquals(1, cart.getItems().size());

        CartItem item = cart.getItems().iterator().next();
        assertEquals(3, item.getQuantity());
        assertEquals(100, item.getPrice());
    }
}