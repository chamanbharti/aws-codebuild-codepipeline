package com.javatechie.aws.cicd.example;

import com.javatechie.aws.cicd.example.model.Product;
import com.javatechie.aws.cicd.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
@RestController
@RequestMapping("/orders")
public class OrderServiceApplication {

    @Autowired
    private OrderDao orderDao;
    @Autowired
    private ProductService service;

    @GetMapping
    public List<Order> fetchOrders() {
        return orderDao.getOrders().stream().
                sorted(Comparator.comparing(Order::getPrice)).collect(Collectors.toList());
    }
    @GetMapping("/all")
    public List<Product> getAllTheProducts() {
        return service.getProducts().stream().sorted(Comparator.comparing(Product::getPrice)).collect(Collectors.toList());
    }
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable int id) {
        return service.getProduct(id);
    }


    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }

}
