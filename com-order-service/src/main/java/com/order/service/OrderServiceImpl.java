package com.order.service;

import com.order.service.exceptions.CustomException;
import com.order.service.external.client.PaymentServiceClient;
import com.order.service.external.client.ProductServiceClient;
import com.order.service.external.request.TransactionDetails;
import com.order.service.external.response.Product;
import com.order.service.models.Order;
import com.order.service.repository.OrderRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;

@Service
@Log4j2
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductServiceClient productServiceClient;
    @Autowired
    private PaymentServiceClient paymentServiceClient;
    @Autowired
    private RestTemplate restTemplate;
    @Override
    public long placeOrder(Order order) {
        log.info("Placing order: {}", order);
        productServiceClient.reduceQuantity(order.getProductId(), order.getQuantity());
        order.setOrderStatus("CREATED");
        order.setOrderData(Instant.now());
        long orderId = orderRepository.save(order).getId();
        log.info("Calling payment service to complete the payment");
        TransactionDetails transactionDetails = TransactionDetails.builder()
                .orderId(orderId)
                .paymentMode(order.getPaymentMode().toString())
                .amount(order.getAmount()).build();
        String orderStatus = null;
        try {
            paymentServiceClient.doPayment(transactionDetails);
            log.info("Payment Done Successfully, changing the order status to changed!");
            orderStatus = "PLACED";
        } catch(Exception e) {
            log.error("Change the order status to failed!");
            orderStatus = "PAYMENT_FAILED";
        }
        order.setOrderStatus(orderStatus);
        orderRepository.save(order);
        log.info("Order placed Successfully!");
        return orderId;
    }

    @Override
    public Order getOrderDetail(long orderId) {
        log.info("Get order detail for orderId: {}", orderId);
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new CustomException("Order not found for orderId: {}"+ orderId, "NOT_FOUND", 404));
        log.info("Invoking payment service to get payment details using orderId{}", order.getId());
        order.setTransactionDetails(restTemplate.getForObject("http://PAYMENT-SERVICE/payment/getPaymentDetailsByOrderId/"+Long.valueOf(order.getId()), TransactionDetails.class));
        log.info("Invoking product service to fetch product{} information relevant to this order", order.getProductId());
        order.setProduct(restTemplate.getForObject("http://PRODUCT-SERVICE/product/get/"+order.getProductId(), Product.class)); // Another example of communicating with microservice instead of using feign client
        return order;
    }
}
