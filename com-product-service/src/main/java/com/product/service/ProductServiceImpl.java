package com.product.service;

import com.product.service.ProductService;
import com.product.service.exception.ProductNotFoundException;
import com.product.service.models.Product;
import com.product.service.repository.ProductRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;
    @Override
    public long addProduct(Product product) {
        log.info("Going to add Product: "+ product);
        productRepository.save(product);
        log.info("Product Created Successfully!");
        return product.getProductId();
    }

    @Override
    public Product getProductById(Long id) {
        log.info("Going to fetch Product with Id: "+ id);
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product with given id[" +id+ "] not found", "PRODUCT_NOT_FOUND"));
        return product;
    }

    @Override
    public List<Product> getProducts() {
        log.info("Going to fetch all Products");
        List<Product> products = productRepository.findAll();
        return products;
    }
}
