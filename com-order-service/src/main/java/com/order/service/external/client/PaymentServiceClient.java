package com.order.service.external.client;

import com.order.service.exceptions.CustomException;
import com.order.service.external.request.TransactionDetails;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@CircuitBreaker(name = "external", fallbackMethod = "fallback")
@FeignClient(name = "PAYMENT-SERVICE/payment")
public interface PaymentServiceClient {
    @PostMapping("/doPayment")
    public ResponseEntity<Long> doPayment(@RequestBody TransactionDetails transactionDetails);

    default void fallback(Exception e) {
        throw new CustomException("Payment Service is not available", "UNAVAILABLE", 500);
    }
}
