package com.example.ecommerce.Service;

import com.example.ecommerce.Model.Category;
import com.example.ecommerce.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public Optional<Category> readCategory(long id) {
        return categoryRepository.getCategoryById(id);
    }

    public Optional<Category> readCategory(String categoryName) {
        return categoryRepository.getCategoryByCategoryName(categoryName);
    }

    public List<Category> listCategory() {
        return categoryRepository.findAll();
    }

    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    public void updateCategory(long categoryID, Category category) {
        Category category1 = categoryRepository.getCategoryById(categoryID).get();
        category1.setCategoryName(category.getCategoryName());
        category1.setDescription(category.getDescription());
        category1.setImageUrl(category1.getImageUrl());
        categoryRepository.save(category1);
    }
}
