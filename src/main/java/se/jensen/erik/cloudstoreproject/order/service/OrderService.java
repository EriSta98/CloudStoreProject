package se.jensen.erik.cloudstoreproject.order.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import se.jensen.erik.cloudstoreproject.order.model.CreateOrderItemRequest;
import se.jensen.erik.cloudstoreproject.order.model.CreateOrderRequest;
import se.jensen.erik.cloudstoreproject.order.model.CustomerOrder;
import se.jensen.erik.cloudstoreproject.order.model.OrderItem;
import se.jensen.erik.cloudstoreproject.order.repository.OrderRepository;
import se.jensen.erik.cloudstoreproject.product.model.Product;
import se.jensen.erik.cloudstoreproject.product.client.ProductClient;



import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductClient productClient;

    public OrderService(OrderRepository orderRepository, ProductClient productClient) {
        this.orderRepository = orderRepository;
        this.productClient = productClient;
    }

    public CustomerOrder createOrder(String userEmail, CreateOrderRequest request){
        List<Product> products = productClient.getProducts();

        CustomerOrder order = new CustomerOrder(userEmail);

        for (CreateOrderItemRequest itemRequest : request.items()) {
            Product product = findProduct(products, itemRequest.productId());

            OrderItem item = new OrderItem(
                    product.getId(),
                    product.getTitle(),
                    product.getPrice(),
                    itemRequest.quantity()
            );

            order.addItem(item);
        }
        return orderRepository.save(order);
    }

    public List<CustomerOrder> getOrderForUser(String userEmail){
        return orderRepository.findByUserEmail(userEmail);
    }

    private Product findProduct(List<Product> products, long productId){
        return products.stream()
                .filter(product -> product.id() == productId)
                .findFIrst()
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,"Product not found"
                ));
    }
}
