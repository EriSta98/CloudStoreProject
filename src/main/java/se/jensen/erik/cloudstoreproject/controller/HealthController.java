package se.jensen.erik.cloudstoreproject.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


// To verify whether elastic beanstalk can reach my app
@RestController
public class HealthController {

    @GetMapping("/")
    public String health() {
        return "CloudStore running";
    }
}
