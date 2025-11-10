package com.app.backend.repository;

import com.app.backend.model.user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.repository;
import java.util.optional;

@Repository
public interface SubcategoryRepository extends JpaRepository<Subcategory , Long>{
    List<Subcategory> findByCategoryId (Long categoryId);

}