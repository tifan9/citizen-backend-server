package com.auth.backendserver.repo;

import com.auth.backendserver.model.Categories;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepo extends JpaRepository <Categories, Integer>{
    Optional <Categories> findByCategoryName(String categoryName);
    Optional <Categories> findByCategoryId(Integer categoryId);
}
