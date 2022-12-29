package com.order.service.controllers;

import com.order.service.OrderService;
import com.order.service.models.Order;
import com.order.service.repository.OrderRepository;
import jakarta.ws.rs.Path;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@Log4j2
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/placeOrder")
    public ResponseEntity<Long> placeOrder(@RequestBody Order order) {
        log.info("Going to place order: "+ order);
        long orderId = orderService.placeOrder(order);
        log.info("Order placed successfully with id["+ order.getId() +"]");
        return new ResponseEntity<>(order.getId(), HttpStatus.CREATED);
    }

    @GetMapping("/getOrder/{orderId}")
    public ResponseEntity<Order> getOrderDetails (@PathVariable long orderId) {
        Order order = orderService.getOrderDetail(orderId);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

}
