package com.auth.backendserver.controller;

import com.auth.backendserver.model.Categories;
import com.auth.backendserver.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // Create or update a category
    @PostMapping("/save")
    public ResponseEntity<Categories> saveCategory(@RequestBody Categories category) {
        Categories savedCategory = categoryService.saveCategory(category);
        return ResponseEntity.ok(savedCategory);
    }

    // Get all categories
    @GetMapping("/all")
    public ResponseEntity<List<Categories>> getAllCategories() {
        List<Categories> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    // Get category by ID
    @GetMapping("/{id}")
    public ResponseEntity<Categories> getCategoryById(@PathVariable int id) {
        Optional<Categories> category = categoryService.getCategoryById(id);
        return category.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Get category by name
    @GetMapping("/name/{name}")
    public ResponseEntity<Categories> getCategoryByName(@PathVariable String name) {
        Optional<Categories> category = categoryService.getCategoryByName(name);
        return category.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Delete category by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable int id) {
        categoryService.deleteCategoryById(id);
        return ResponseEntity.noContent().build();
    }
}