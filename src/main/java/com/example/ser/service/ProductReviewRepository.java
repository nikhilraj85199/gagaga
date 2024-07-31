package com.example.ser.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ser.model.ProductReview;

@Repository
public interface ProductReviewRepository extends JpaRepository<ProductReview, Long> {
	List<ProductReview> findByProductId(Long productId);
    List<ProductReview> findByUserId(Long userId);
}
