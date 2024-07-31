package com.example.ser.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ser.model.ProductReview;
import com.example.ser.service.ProductReviewRepository; // Correct import

@Service
public class ProductReviewService {

    @Autowired
    private ProductReviewRepository productReviewRepository;

    public List<ProductReview> getProductReviews(Long productId) {
        return productReviewRepository.findByProductId(productId);
    }

    public void save(ProductReview review) {
        productReviewRepository.save(review);
    }
}
