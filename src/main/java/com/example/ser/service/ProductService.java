package com.example.ser.service;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ser.model.Product;
import com.example.ser.service.ProductRepository; // Correct import

@SuppressWarnings("unused")
@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product getProduct(Long productId) {
        return productRepository.findById(productId)
                                 .orElseThrow(() -> new NoSuchElementException("Product not found with id: " + productId));
    }

}
