package com.example.ecommerce.Controller;

import com.example.ecommerce.Common.ApiResponse;
import com.example.ecommerce.Model.Category;
import com.example.ecommerce.Model.Product;
import com.example.ecommerce.Repository.ProductRepository;
import com.example.ecommerce.Service.CategoryService;
import com.example.ecommerce.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/category")

public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@GetMapping("/")
    public ResponseEntity<List<Category>> getCategories() {
        List<Category> body = categoryService.listCategory();
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

	@PostMapping("/create")
	public ResponseEntity<ApiResponse> createCategory(@Valid @RequestBody Category category) {
		if (categoryService.readCategory(category.getCategoryName()).isPresent()) {
			return new ResponseEntity<>(new ApiResponse(false, "category already exists"), HttpStatus.CONFLICT);
		}
		categoryService.createCategory(category);
		return new ResponseEntity<>(new ApiResponse(true, "created the category"), HttpStatus.CREATED);
	}

	@PostMapping("/update/{categoryID}")
	public ResponseEntity<ApiResponse> updateCategory(@PathVariable("categoryID") long categoryID, @Valid @RequestBody Category category) {
		if (categoryService.readCategory(categoryID) != null) {
			categoryService.updateCategory(categoryID, category);
			return new ResponseEntity<>(new ApiResponse(true, "updated the category"), HttpStatus.OK);
		}

		return new ResponseEntity<>(new ApiResponse(false, "category does not exist"), HttpStatus.NOT_FOUND);
	}
}
