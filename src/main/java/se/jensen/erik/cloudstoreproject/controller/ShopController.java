package se.jensen.erik.cloudstoreproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import se.jensen.erik.cloudstoreproject.service.Product.ProductService;

@Controller
@RequestMapping("/shop")
public class ShopController {

    private final ProductService productService;


    public ShopController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public String products(Model model){
        model.addAttribute("products", productService.getAllProducts());
        return "products";
    }
}
