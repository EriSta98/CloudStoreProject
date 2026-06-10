package se.jensen.erik.cloudstoreproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import se.jensen.erik.cloudstoreproject.model.user.dto.RegisterRequest;
import se.jensen.erik.cloudstoreproject.service.user.UserService;

@Controller
public class RegistrationController {

    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {

        model.addAttribute(
                "registerRequest",
                new RegisterRequest()
        );

        return "register";
    }

    @PostMapping("/register")
    public String register(
            @ModelAttribute RegisterRequest request
    ){
        userService.register(request);

        return "redirect:/login";
    }
}
