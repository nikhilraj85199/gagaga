package com.example.ser.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.ser.model.Product;
import com.example.ser.model.ProductReview;
import com.example.ser.model.User;
import com.example.ser.service.ProductService;
import com.example.ser.service.UserRepository;

import com.example.ser.service.ProductReviewService;

import java.security.Principal;
import java.util.List;

@Controller
public class ProductController {
    
    @Autowired
    private ProductService productService;
    
    @Autowired
    private ProductReviewService productReviewService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/product/{productId}")
    public String getProductDetails(@PathVariable Long productId, Model model) {
        Product product = productService.getProduct(productId);
        List<ProductReview> reviews = productReviewService.getProductReviews(productId);
        model.addAttribute("product", product);
        model.addAttribute("reviews", reviews);
        return "disc/product-details"; // Assuming you have a Thymeleaf template named "product-details.html"
    }

    @PostMapping("/add-review")
    public String addReview(@RequestParam Long productId, @RequestParam int rating, @RequestParam String comment, Principal principal) {
        if (principal != null) {
            String username = principal.getName();
            User user = userRepository.findByUsername(username);
            if (user != null) {
                Product product = productService.getProduct(productId);
                ProductReview review = new ProductReview();
                review.setProduct(product);
                review.setUser(user);
                review.setRating(rating);
                review.setComment(comment);
                productReviewService.save(review);
            }
        }
        return "redirect:/disc/" + productId;
    }
}
