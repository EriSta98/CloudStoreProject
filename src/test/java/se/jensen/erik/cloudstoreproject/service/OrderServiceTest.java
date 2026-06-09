package se.jensen.erik.cloudstoreproject.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.jensen.erik.cloudstoreproject.order.model.CreateOrderItemRequest;
import se.jensen.erik.cloudstoreproject.order.model.CreateOrderRequest;
import se.jensen.erik.cloudstoreproject.order.model.CustomerOrder;
import se.jensen.erik.cloudstoreproject.order.model.OrderItem;
import se.jensen.erik.cloudstoreproject.order.repository.OrderRepository;
import se.jensen.erik.cloudstoreproject.order.service.OrderService;
import se.jensen.erik.cloudstoreproject.product.client.ProductClient;
import se.jensen.erik.cloudstoreproject.product.model.Product;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

// Validates important business logic
// Ensures order creation doesn't break

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private ProductClient productClient;

    @InjectMocks
    private OrderService orderService;

    @Test
    void createOrder_shouldCreateOrderWithCorrectItems() {

        // Arrange
        Product product = new Product(
                1L,
                "Test Product",
                10.0,
                "desc",
                "cat",
                "img",
                null
        );

        when(productClient.getProducts()).thenReturn(List.of(product));

        CreateOrderItemRequest itemRequest =
                new CreateOrderItemRequest(1L, 2);

        CreateOrderRequest request =
                new CreateOrderRequest(List.of(itemRequest));

        CustomerOrder savedOrder = new CustomerOrder("test@mail.com");
        savedOrder.setId(1L);

        when(orderRepository.save(any(CustomerOrder.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        CustomerOrder result =
                orderService.createOrder("test@mail.com", request);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getItems().size());

        OrderItem item = result.getItems().get(0);
        assertEquals(1L, item.getProductId());
        assertEquals("Test Product", item.getProductTitle());
        assertEquals(10.0, item.getPrice());
        assertEquals(2, item.getQuantity());

        verify(orderRepository, times(1)).save(any(CustomerOrder.class));
    }
}
