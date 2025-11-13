package com.app.backend.controller;

import com.app.backend.model.Category;
import com.app.backend.service.CategoryService;
import com.app.backend.dto.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.Http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.acces.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
@CrossOrigin(origins = "*")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    
    @GetMapping
    @PreAuthorized("hasAnyRole('ADMIN','COORDINADOR')")
    public ResponseEntity<List<Category>> getAllCategories(){
    }

    @GetMapping("/{id}")
    @PreAuthorized("hasAnyRole('ADMIN','COORDINADOR')")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
        return ResponseEntity.ok(categoryService.findById(id));
    }

    @PostMapping
    @PreAuthorized("hasAnyRole('ADMIN','COORDINADOR')")
    public ResponseEntity<Category> CreateCategory(@RequestBody Category category){
        return ResponseEntity.ok(categoryService.create(category));
    }

    @PutMapping("/{id}")
    @PreAuthorized("hasAnyRole('ADMIN','COORDINADOR')")
    public ResponseEntity<Category> updateCategory(@PathVariable Long id,@Valid @RequestBody Category category) {
        return ResponseEntity.ok(categoryService.update(id,category));
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorized("hasAnyRole('ADMIN')")
    public ResponseEntity<Category> deleteCategory(@PathVariable Long id,@Valid @RequestBody Category category) {
        categoryService.delete(id);
        return ResponseEntity.ok(new MessageResponse("categoria eliminada exitosamente"));
    }
    

    
}
