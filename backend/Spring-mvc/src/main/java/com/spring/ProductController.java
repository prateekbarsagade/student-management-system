package com.spring;

import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
public class ProductController {

    @GetMapping("/test")
    public String test() {
    	return "Spring MVC is working";
    }
    
    @GetMapping("/")
    public String start() {
    	return "Hello World";
    }
    
    @GetMapping("/products")
    public List<String> getProducts() {
        return Arrays.asList("Laptop", "Mobile", "Tablet");
    }
}