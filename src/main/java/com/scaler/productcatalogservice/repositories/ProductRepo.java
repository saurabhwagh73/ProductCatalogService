package com.scaler.productcatalogservice.repositories;

import com.scaler.productcatalogservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {
    public Product save(Product product);
    public Optional<Product> findById(Long id);
    @Query("select p.description from Product p where p.id=:id")
    public String findProductDescriptionById(Long id);
}
