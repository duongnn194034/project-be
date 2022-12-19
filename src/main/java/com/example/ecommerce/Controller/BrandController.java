package com.example.ecommerce.Controller;

import com.example.ecommerce.Common.ApiResponse;
import com.example.ecommerce.Model.Brand;
import com.example.ecommerce.Service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/brand")

public class BrandController {

	@Autowired
	private BrandService brandService;

	@GetMapping("/")
    public ResponseEntity<List<Brand>> getCategories() {
        List<Brand> body = brandService.listBrand();
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

	@PostMapping("/create")
	public ResponseEntity<ApiResponse> createbrand(@Valid @RequestBody Brand brand) {
		if (brandService.readBrand(brand.getBrandName()).isPresent()) {
			return new ResponseEntity<ApiResponse>(new ApiResponse(false, "brand already exists"), HttpStatus.CONFLICT);
		}
		brandService.createBrand(brand);
		return new ResponseEntity<ApiResponse>(new ApiResponse(true, "created the brand"), HttpStatus.CREATED);
	}

	@PostMapping("/update/{brandID}")
	public ResponseEntity<ApiResponse> updatebrand(@PathVariable("brandID") long brandID, @Valid @RequestBody Brand brand) {
		if (brandService.readBrand(brandID) != null) {
			brandService.updateBrand(brandID, brand);
			return new ResponseEntity<>(new ApiResponse(true, "updated the brand"), HttpStatus.OK);
		}

		return new ResponseEntity<>(new ApiResponse(false, "brand does not exist"), HttpStatus.NOT_FOUND);
	}
}
