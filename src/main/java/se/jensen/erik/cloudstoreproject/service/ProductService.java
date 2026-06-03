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


    private final ProductRepository productRepository;
    private final RestTemplate restTemplate;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
        this.restTemplate = new RestTemplate();
    }
    
    /**
     * Fetches products from a fake store and saves them to a database
     * @return the saved products
     */
    public List<Product> fetchAndSaveProducts() {
        String fakeStoreUrl = "http://yahyatesting-env.eba-sarnymwd.eu-north-1.elasticbeanstalk.com/products";

        Product[] response = restTemplate.getForObject(fakeStoreUrl, Product[].class);

        List <Product> products = Arrays.asList(response);

        productRepository.saveAll(products);


        return productRepository.findAll();
    }


    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }


}
