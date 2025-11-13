package com.app.backend.controller;

import com.app.backend.model.Subcategory;
import com.app.backend.service.SubcategoryService;
import com.app.backend.dto.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.Http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.acces.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/Subcategories")
@CrossOrigin(origins = "*")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    
    @GetMapping("/category/{categoryId}")
    @PreAuthorized("hasAnyRole('ADMIN','COORDINADOR')")
    public ResponseEntity<List<Category>> getSubcategoriesByCategory(){
    }

    @GetMapping("/{id}")
    @PreAuthorized("hasAnyRole('ADMIN','COORDINADOR')")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
        return ResponseEntity.ok(categoryService.findById(id));
    }

    @PostMapping
    @PreAuthorized("hasAnyRole('ADMIN','COORDINADOR')")
    public ResponseEntity<Subategory> CreateCategory(@RequestBody Subategory subcategory){
        return ResponseEntity.ok(categoryService.create(subcategory));
    }

    @PutMapping("/{id}")
    @PreAuthorized("hasAnyRole('ADMIN','COORDINADOR')")
    public ResponseEntity<Subcategory> updateSubcategory(@PathVariable Long id,@Valid @RequestBody Subategory subcategory) {
        return ResponseEntity.ok(categoryService.update(id,subcategory));
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorized("hasAnyRole('ADMIN')")
    public ResponseEntity<MessageResponse> deleteSubategory(@PathVariable Long id,@Valid @RequestBody Category category) {
        subcategoryService.delete(id);
        return ResponseEntity.ok(new MessageResponse("Subcategoria eliminada exitosamente"));
    }
    

    
}
