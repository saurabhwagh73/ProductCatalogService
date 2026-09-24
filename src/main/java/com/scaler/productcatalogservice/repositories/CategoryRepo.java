package com.scaler.productcatalogservice.repositories;

import com.scaler.productcatalogservice.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface CategoryRepo extends JpaRepository<Category,Long> {
    Optional<Category> findCategoryById(Long id);
    Optional<Category> findByName(String name);
}
