package com.auth.backendserver.service;

import com.auth.backendserver.model.Categories;
import com.auth.backendserver.repo.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepo categoryRepo;

    @Autowired
    public CategoryService(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    // Create or update a category
    public Categories saveCategory(Categories category) {
        return categoryRepo.save(category);
    }

    // Retrieve all categories
    public List<Categories> getAllCategories() {
        return categoryRepo.findAll();
    }

    // Retrieve a category by ID
    public Optional<Categories> getCategoryById(int id) {
        return categoryRepo.findById(id);
    }

    // Retrieve a category by name
    public Optional<Categories> getCategoryByName(String name) {
        return categoryRepo.findByCategoryName(name);
    }

    // Delete a category by ID
    public void deleteCategoryById(int id) {
        categoryRepo.deleteById(id);
    }
}