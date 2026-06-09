package se.jensen.erik.cloudstoreproject.service;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;
import se.jensen.erik.cloudstoreproject.product.model.Product;
import se.jensen.erik.cloudstoreproject.repository.product.ProductRepository;
import se.jensen.erik.cloudstoreproject.service.Product.ProductService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    @Value("${fakestore.url}")
    private static final String FAKE_STORE_URL = "fakeStoreUrl";

    @Test
    void getAllProducts() {
        // Arrange
        ProductRepository repository = mock(ProductRepository.class);
        RestTemplate restTemplate = mock(RestTemplate.class);

        Product product1 = new Product();
        product1.setTitle("Test product1");

        Product product2 = new Product();
        product2.setTitle("Test product2");

        List<Product> fakeProducts = List.of(product1, product2);

        when(repository.findAll()).thenReturn(fakeProducts);

        ProductService service = new ProductService(repository, restTemplate, FAKE_STORE_URL);

        // Act
        List<Product> result = service.getAllProducts();


        // Assert
        assertEquals(2, result.size());
        assertEquals("Test product1", result.get(0).getTitle());
        assertEquals("Test product2", result.get(1).getTitle());

        verify(repository).findAll();
    }

    @Test
    void fetchAndSaveProducts(){
        // Arrange
        ProductRepository repository = mock(ProductRepository.class);
        RestTemplate restTemplate = mock(RestTemplate.class);

        Product product1 = new Product();
        product1.setTitle("Test product1");

        Product product2 = new Product();
        product2.setTitle("Test product2");

        Product[] apiResponse = {product1, product2};

        when(restTemplate.getForObject(
                FAKE_STORE_URL,
                Product[].class))
                .thenReturn(apiResponse);

        when(repository.findAll()).thenReturn(List.of(product1, product2));

        ProductService service = new ProductService(repository, restTemplate, FAKE_STORE_URL);

        // Act
        List<Product> result = service.fetchAndSaveProducts();

        // Assert
        assertEquals(2, result.size());
        assertEquals("Test product1", result.get(0).getTitle());
        assertEquals("Test product2", result.get(1).getTitle());

        verify(restTemplate).getForObject(
                FAKE_STORE_URL,
                Product[].class);
        verify(repository).saveAll(List.of(product1, product2));
        verify(repository).findAll();
    }
}

