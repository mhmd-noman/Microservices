package com.product.service;

import com.product.service.models.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
public interface ProductService {
    long addProduct(Product product);

    Product getProductById(Long id);

    List<Product> getProducts();
    void reduceQuantity(long productId, long quantity);
}
