package se.jensen.erik.cloudstoreproject.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import se.jensen.erik.cloudstoreproject.cart.Cart;
import se.jensen.erik.cloudstoreproject.product.model.Product;
import se.jensen.erik.cloudstoreproject.service.Product.ProductService;

@Controller
@RequestMapping("/cart")
public class CartController {

    private final ProductService productService;

    public CartController(ProductService productService) {
        this.productService = productService;
    }

    private Cart getCart(HttpSession session){
        Cart cart = (Cart) session.getAttribute("cart");
        if(cart == null){
            cart = new Cart();
            session.setAttribute("cart", cart);
        }
        return cart;
    }

    @PostMapping("/add/{id}")
    public String addToCart(@PathVariable long id,
                            @RequestParam int quantity,
                            HttpSession session){

        Product product = productService.getAllProducts()
                .stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElseThrow();

        Cart cart = getCart(session);
        cart.addProduct(product, quantity);

        return "redirect:/shop/products";
    }

    @GetMapping
    public String viewCart(HttpSession session, Model model){

        Cart cart = getCart(session);

        model.addAttribute("items", cart.getItems());
        model.addAttribute("total", cart.getTotal());

        return "ShopCart";
    }

    @PostMapping("/remove/{id}")
    public String remove(@PathVariable long id, HttpSession session){
        getCart(session).removeProduct(id);
        return "redirect:/cart";
    }
}
