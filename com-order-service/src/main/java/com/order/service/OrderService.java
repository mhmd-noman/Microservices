package com.order.service;

import com.order.service.models.Order;

public interface OrderService {
    long placeOrder(Order order);
}
