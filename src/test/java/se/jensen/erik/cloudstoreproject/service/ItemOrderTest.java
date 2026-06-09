package se.jensen.erik.cloudstoreproject.service;


import org.junit.jupiter.api.Test;
import se.jensen.erik.cloudstoreproject.order.model.CustomerOrder;
import se.jensen.erik.cloudstoreproject.order.model.OrderItem;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ItemOrderTest {


    @Test
    void shouldAddItemsToOrder() {


        // Arrange
        CustomerOrder order = new CustomerOrder();
        OrderItem item = new OrderItem();

        // Act
        order.addItem(item);

        // Assert
        assertEquals(1, order.getItems().size());
        assertTrue(order.getItems().contains(item));

    }


}
