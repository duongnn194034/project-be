package com.example.ecommerce.Service;

import com.example.ecommerce.Dto.Product.ProductDto;
import com.example.ecommerce.Model.Brand;
import com.example.ecommerce.Model.Product;
import com.example.ecommerce.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public Product getProductById(Long productId) {
        Optional<Product> product = productRepository.findById(productId);
        return product.isEmpty() ? new Product() : product.get();
    }

    public List<ProductDto> listProducts() {
        List<ProductDto> productDtoList = new ArrayList<>();
        List<Product> productList = productRepository.findAll();
        for (Product product : productList) {
            productDtoList.add(new ProductDto(product));
        }
        return productDtoList;
    }

    public void addProduct(ProductDto productDto, Brand brand) {
        Product product = new Product(productDto, brand);
        productRepository.save(product);
    }

    public void updateProduct(Long productID, ProductDto productDto, Brand brand) {
        Product product = new Product(productDto, brand);
        productRepository.updateById(productID, product);
    }
}
