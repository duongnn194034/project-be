package com.example.ecommerce.Controller;

import com.example.ecommerce.Common.ApiResponse;
import com.example.ecommerce.Dto.Product.ProductDto;
import com.example.ecommerce.Model.Brand;
import com.example.ecommerce.Service.BrandService;
import com.example.ecommerce.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    BrandService brandService;

    @GetMapping("/")
    public ResponseEntity<List<ProductDto>> getProducts() {
        List<ProductDto> body = productService.listProducts();
        return new ResponseEntity<List<ProductDto>>(body, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addProduct(@RequestBody ProductDto productDto) {
        Optional<Brand> optionalBrand = brandService.readBrand(productDto.getBrandId());
        if (!optionalBrand.isPresent()) {
            return new ResponseEntity<>(new ApiResponse(false, "brand is invalid"), HttpStatus.CONFLICT);
        }
        Brand brand = optionalBrand.get();
        productService.addProduct(productDto, brand);
        return new ResponseEntity<>(new ApiResponse(true, "Product has been added"), HttpStatus.CREATED);
    }

    @PostMapping("/update/{productID}")
    public ResponseEntity<ApiResponse> updateProduct(@PathVariable("productID") Long productID, @RequestBody @Valid ProductDto productDto) {
        Optional<Brand> optionalbrand = brandService.readBrand(productDto.getBrandId());
        if (!optionalbrand.isPresent()) {
            return new ResponseEntity<ApiResponse>(new ApiResponse(false, "brand is invalid"), HttpStatus.CONFLICT);
        }
        Brand brand = optionalbrand.get();
        productService.updateProduct(productID, productDto, brand);
        return new ResponseEntity<>(new ApiResponse(true, "Product has been updated"), HttpStatus.OK);
    }
}
