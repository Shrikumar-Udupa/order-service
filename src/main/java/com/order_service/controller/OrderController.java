package com.order_service.controller;


import com.order_service.entity.OrderEntity;
import com.order_service.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/add")
    public ResponseEntity<OrderEntity> addOrder(@RequestBody OrderEntity order) {
        orderService.addOrder(order);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/get/{id}")
    private OrderEntity getOrder(@PathVariable Long id) {
        return orderService.getOrder(id);
    }

    @GetMapping("/orders")
    private List<OrderEntity> getOrdersByUserId(@RequestParam Long userId) {
        return orderService.getOrdersByUserId(userId);
    }
}
