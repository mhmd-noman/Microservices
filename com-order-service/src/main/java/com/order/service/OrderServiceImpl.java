package com.order.service;

import com.order.service.external.client.ProductServiceClient;
import com.order.service.models.Order;
import com.order.service.repository.OrderRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@Log4j2
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductServiceClient productServiceClient;
    @Override
    public long placeOrder(Order order) {
        log.info("Placing order: {}", order);
        productServiceClient.reduceQuantity(order.getProductId(), order.getQuantity());
        order.setOrderStatus("CREATED");
        order.setOrderData(Instant.now());
        long orderId = orderRepository.save(order).getId();
        return orderId;
    }
}
