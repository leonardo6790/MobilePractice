package com.app.backend.repository;

import com.app.backend.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
    List<Product> findByCategory_Id(Long categoryId);

    List<Product> findBySubcategory_Id(Long subcategoryId);
}