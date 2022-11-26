package com.example.ecommerce.Service;

import com.example.ecommerce.Model.Product;
import com.example.ecommerce.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public Product getProductById(Long productId) {
        Optional<Product> product = productRepository.findById(productId);
        return product.isEmpty() ? new Product() : product.get();
    }
}
