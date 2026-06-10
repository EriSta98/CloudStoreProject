package se.jensen.erik.cloudstoreproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import se.jensen.erik.cloudstoreproject.product.model.Product;
import se.jensen.erik.cloudstoreproject.service.Product.ProductService;

import java.util.List;

@Controller
@RequestMapping("/shop")
public class ShopController {

    private final ProductService service;


    public ShopController(ProductService service) {
        this.service = service;
    }

    @GetMapping("/products")
    public String products(Model model){
        List<Product> products = service.getAllProducts();

        System.out.println("PRODUCT COUNT: " + products.size());
        service.getAllProducts().forEach(p -> System.out.println(p.getTitle()));

        model.addAttribute("products", products);

        return "products";
    }
}
