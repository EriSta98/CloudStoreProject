package se.jensen.erik.cloudstoreproject.service;

import org.junit.jupiter.api.Test;
import se.jensen.erik.cloudstoreproject.model.Product;
import se.jensen.erik.cloudstoreproject.repository.ProductRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    @Test
    void getAllProducts() {
        // Arrange
        ProductRepository repository = mock(ProductRepository.class);

        Product product1 = new Product();
        product1.setTitle("Test product1");

        Product product2 = new Product();
        product2.setTitle("Test product2");

        List<Product> fakeProducts = List.of(product1, product2);

        when(repository.findAll()).thenReturn(fakeProducts);

        ProductService service = new ProductService(repository);

        // Act
        List<Product> result = service.getAllProducts();


        // Assert
        assertEquals(2, result.size());
        assertEquals("Test product1", result.get(0).getTitle());
        assertEquals("Test product2", result.get(1).getTitle());

        verify(repository).findAll();
    }
}