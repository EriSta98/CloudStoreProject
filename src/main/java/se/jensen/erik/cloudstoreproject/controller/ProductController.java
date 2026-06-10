package se.jensen.erik.cloudstoreproject.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.jensen.erik.cloudstoreproject.product.model.Product;
import se.jensen.erik.cloudstoreproject.service.Product.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/products")

public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping("/fetch")
    public List<Product> fetchProducts() {
        return service.fetchAndSaveProducts();
    }

    @GetMapping
    public List<Product> getAll() {
        return service.getAllProducts();
    }

}
