package se.jensen.erik.cloudstoreproject.service.Product;




import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import se.jensen.erik.cloudstoreproject.product.model.Product;
import se.jensen.erik.cloudstoreproject.repository.product.ProductRepository;

import java.util.Arrays;
import java.util.List;

@Service
public class ProductService {

    @Value("${fakestore.url}")

    private final String fakeStoreUrl;
    private final ProductRepository productRepository;
    private final RestTemplate restTemplate;

    public ProductService(ProductRepository productRepository, RestTemplate restTemplate, @Value("${fakestore.url}") String fakeStoreUrl) {
        this.productRepository = productRepository;
        this.restTemplate = restTemplate;
        this.fakeStoreUrl = fakeStoreUrl;
    }
    
    /**
     * Fetches products from a fake store and saves them to a database
     * @return the saved products
     */
    public List<Product> fetchAndSaveProducts() {

        // RestTemplate is used to make HTTP requests to external API
        Product[] response = restTemplate.getForObject(fakeStoreUrl, Product[].class);


        if (response == null) {
            throw new IllegalStateException("Failed to fetch products from the fake store");
        }

        List <Product> products = Arrays.asList(response);


        productRepository.saveAll(products);


        return productRepository.findAll();

    }


    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }


}
