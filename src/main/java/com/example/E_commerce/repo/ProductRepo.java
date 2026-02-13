package com.example.E_commerce.repo;

import com.example.E_commerce.model.Product;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo  extends JpaRepository<Product,Long> {
    boolean existsByName(@NotBlank String name);
}
