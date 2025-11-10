package com.app.backend.repository;

import com.app.backend.model.user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.repository;
import java.util.optional;

@Repository
public interface CategoryRepository extends JpaRepository<category , Long>{
    Optional<Category> findByName (String name);

    boolean existsByName(String name);
}