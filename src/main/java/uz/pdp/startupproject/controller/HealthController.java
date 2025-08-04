package uz.pdp.startupproject.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    @GetMapping("/")
    public String healthCheck() {
        return "Application is running!";
    }

    @GetMapping("/health")
    public String health() {
        return "OK";
    }
}
