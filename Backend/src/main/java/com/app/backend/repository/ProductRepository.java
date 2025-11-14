package com.app.backend.repository;

import com.app.backend.model.user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.repository;
import java.util.optional;

@Repository
public interface ProductRepository extends JpaRepository<product , Long>{
    List<Product> findByCategoryId (Long categoryId);

    List<Product> findBySubcategoryId (Long subcategoryId);
}