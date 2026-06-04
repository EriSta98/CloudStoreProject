package se.jensen.erik.cloudstoreproject.service;




import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import se.jensen.erik.cloudstoreproject.model.Product;
import se.jensen.erik.cloudstoreproject.repository.ProductRepository;

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

        /*
        String response = restTemplate.getForObject(
                fakeStoreUrl,
                String.class
        );

        System.out.println(response);
        return List.of();

        */


        // RestTemplate is used to make HTTP requests to external API
        Product[] response = restTemplate.getForObject(fakeStoreUrl, Product[].class);


        if (response == null) {
            throw new IllegalStateException("Failed to fetch products from the fake store");
        }

        List <Product> products = Arrays.asList(response);

        for (Product p : products) {
            System.out.println("ID: " + p.getId());
            System.out.println("TITLE LENGTH: " + (p.getTitle() != null ? p.getTitle().length() : 0));
            System.out.println("DESCRIPTION LENGTH: " + (p.getDescription() != null ? p.getDescription().length() : 0));
            System.out.println("DESCRIPTION: " + p.getDescription());
            System.out.println("----------------");
        }

        productRepository.saveAll(products);


        return productRepository.findAll();



    }


    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }


}
