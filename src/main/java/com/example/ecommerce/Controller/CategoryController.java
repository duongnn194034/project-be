package com.example.ecommerce.Controller;

import com.example.ecommerce.Common.ApiResponse;
import com.example.ecommerce.Model.Category;
import com.example.ecommerce.Model.Product;
import com.example.ecommerce.Repository.ProductRepository;
import com.example.ecommerce.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/category")

public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private ProductRepository productRepository;

	@GetMapping("/")
    public ResponseEntity<List<Category>> getCategories() {
        List<Category> body = categoryService.listCategory();
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

	@PostMapping("/create")
	public ResponseEntity<ApiResponse> createcategory(@Valid @RequestBody Category category) {
		if (categoryService.readCategory(category.getCategoryName()).isPresent()) {
			return new ResponseEntity<>(new ApiResponse(false, "category already exists"), HttpStatus.CONFLICT);
		}
		categoryService.createCategory(category);
		return new ResponseEntity<>(new ApiResponse(true, "created the category"), HttpStatus.CREATED);
	}

	@PostMapping("/update/{categoryID}")
	public ResponseEntity<ApiResponse> updatecategory(@PathVariable("categoryID") long categoryID, @Valid @RequestBody Category category) {
		if (categoryService.readCategory(categoryID) != null) {
			categoryService.updateCategory(categoryID, category);
			return new ResponseEntity<>(new ApiResponse(true, "updated the category"), HttpStatus.OK);
		}

		return new ResponseEntity<>(new ApiResponse(false, "category does not exist"), HttpStatus.NOT_FOUND);
	}

//	@GetMapping("/show/{categoryID}")
//	public ResponseEntity<Category> showCategory(@PathVariable("categoryID") long categoryID) {
//		Optional<Category> category = categoryService.readCategory(categoryID);
//		if (category.isEmpty()) {
//			return new ResponseEntity<>(new Category(), HttpStatus.NOT_FOUND);
//		}
//		List<Product> products = productService.getProductsByCategory(category.get());
//			Product[] productSet = new Product[products.size()];
//			for (int i = 0; i < products.size(); i++) {
//				productSet[i] = products.get(i);
//			}
//		Category category1 = category.get();
//		category1.setProducts(products);
//		return new ResponseEntity<>(category1, HttpStatus.OK);
//	}
}
