package se.jensen.erik.cloudstoreproject.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import se.jensen.erik.cloudstoreproject.cart.Cart;
import se.jensen.erik.cloudstoreproject.order.model.CustomerOrder;
import se.jensen.erik.cloudstoreproject.order.model.OrderItem;
import se.jensen.erik.cloudstoreproject.order.repository.OrderRepository;
import se.jensen.erik.cloudstoreproject.order.service.OrderService;

@Controller
@RequestMapping("/order")
public class OrderController {

    private final OrderRepository orderRepository;

    public OrderController(OrderService orderService, OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    @PostMapping("/confirm")
    public String confirm(HttpSession session, Authentication auth, Model model){

        Cart cart = (Cart) session.getAttribute("cart");

        if(cart == null || cart.getItems().isEmpty()){
            return "redirect:/cart";
        }

        CustomerOrder order = new CustomerOrder(auth.getName());

        cart.getItems().forEach(i -> {
            order.addItem(new OrderItem(
                    i.getProductId(),
                    i.getTitle(),
                    i.getPrice(),
                    i.getQuantity()
            ));
        });

        orderRepository.save(order);

        session.removeAttribute("cart");

        model.addAttribute("orderId", order.getId());

        return "order-confirmed";
    }
}
