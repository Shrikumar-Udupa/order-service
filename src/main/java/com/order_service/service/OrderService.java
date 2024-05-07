package com.order_service.service;


import com.order_service.entity.OrderEntity;
import com.order_service.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private RestTemplate restTemplate;

    private final String inventoryServiceUrl = "http://localhost:8081/";

    public OrderEntity getOrder(Long id) {
        return orderRepository.findById(id).get();
    }

    public OrderEntity addOrder(OrderEntity order) {
        OrderEntity savedOrder = orderRepository.save(order);

        updateInventory(savedOrder.getProductId(), savedOrder.getQuantity());

        return savedOrder;
    }

    private void updateInventory(Long productId, int quantity) {
        // Prepare the URL for updating inventory based on the order
        String url = inventoryServiceUrl + "?productId=" + productId + "&quantity=" + quantity;
        // Make HTTP PUT request to update inventory
        restTemplate.put(url, null);

    }

    public List<OrderEntity> getOrdersByUserId(Long userId) {
        return orderRepository.findByuserId(userId);
    }
}
