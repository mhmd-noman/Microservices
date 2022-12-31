package com.order.service.external.client;

import com.order.service.exceptions.CustomException;
import com.order.service.external.response.Product;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@CircuitBreaker(name = "external", fallbackMethod = "fallback")
@FeignClient(name = "PRODUCT-SERVICE/product")
public interface ProductServiceClient {
    @PutMapping(path = "/reduceQuantity/{id}")
    ResponseEntity<Void> reduceQuantity(@PathVariable("id") long productId, @RequestParam long quantity);

    @GetMapping(path = "/get/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id);

    default void fallback(Exception e) {
        throw new CustomException("Product Service is not available", "UNAVAILABLE", 500);
    }
}
