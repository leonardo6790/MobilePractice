package com.app.backend.controller;

import com.app.backend.model.Subcategory;
import com.app.backend.service.SubcategoryService;
import com.app.backend.dto.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/subcategories")
public class SubcategoryController {
    @Autowired
    private SubcategoryService subcategoryService;
    
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','COORDINADOR')")
    public ResponseEntity<List<Subcategory>> getAllSubcategories(){
        return ResponseEntity.ok(subcategoryService.findAll());
    }

    @GetMapping("/category/{categoryId}")
    @PreAuthorize("hasAnyRole('ADMIN','COORDINADOR')")
    public ResponseEntity<List<Subcategory>> getSubcategoriesByCategory(@PathVariable Long categoryId){
        return ResponseEntity.ok(subcategoryService.findByCategoryId(categoryId));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','COORDINADOR')")
    public ResponseEntity<Subcategory> getSubcategoryById(@PathVariable Long id) {
        return ResponseEntity.ok(subcategoryService.findById(id));
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','COORDINADOR')")
    public ResponseEntity<Subcategory> createSubcategory(@RequestBody Map<String, Object> request){
        String name = (String) request.get("name");
        String description = (String) request.get("description");
        Long categoryId = ((Number) request.get("categoryId")).longValue();
        Boolean active = (Boolean) request.get("active");
        
        return ResponseEntity.ok(subcategoryService.createWithCategoryId(name, description, categoryId, active));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','COORDINADOR')")
    public ResponseEntity<Subcategory> updateSubcategory(@PathVariable Long id,@RequestBody Map<String, Object> request) {
        String name = (String) request.get("name");
        String description = (String) request.get("description");
        Long categoryId = ((Number) request.get("categoryId")).longValue();
        Boolean active = (Boolean) request.get("active");
        
        return ResponseEntity.ok(subcategoryService.updateWithCategoryId(id, name, description, categoryId, active));
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<MessageResponse> deleteSubcategory(@PathVariable Long id) {
        subcategoryService.delete(id);
        return ResponseEntity.ok(new MessageResponse("Subcategoria eliminada exitosamente"));
    }
}
