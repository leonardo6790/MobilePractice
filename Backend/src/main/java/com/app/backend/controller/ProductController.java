package com.app.backend.controller;

import com.app.backend.model.Product;
import com.app.backend.service.ProductService;
import com.app.backend.dto.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.Http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.acces.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Locale.Category;

@RestController
@RequestMapping("/api/product")
@CrossOrigin(origins = "*")
public class ProductController {
    @Autowired
    private ProductService productService;
    
    @GetMapping
    @PreAuthorized("hasAnyRole('ADMIN','COORDINADOR')")
    public ResponseEntity<List<Product>> getAllProducts(){
    }

    @GetMapping("/product/{categoryId}")
    @PreAuthorized("hasAnyRole('ADMIN','COORDINADOR')")
    public ResponseEntity<List<Product>> getProductByCategory(@PathVariable Long categoryId){
        return ResponseEntity.ok(productService.findByCategoryId(categoryId));
    }

    @GetMapping("/product/{categoryId}")
    @PreAuthorized("hasAnyRole('ADMIN','COORDINADOR')")
    public ResponseEntity<List<Product>> getProductBySubcategory(@PathVariable Long subategoryId){
        return ResponseEntity.ok(productService.findBySubcategoryId(subategoryId));
    }

    @GetMapping("/{id}")
    @PreAuthorized("hasAnyRole('ADMIN','COORDINADOR')")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.findById(id));
    }

    @PostMapping
    @PreAuthorized("hasAnyRole('ADMIN','COORDINADOR')")
    public ResponseEntity<Product> CreateProduct(@RequestBody Product product){
        return ResponseEntity.ok(productService.create(product));
    }

    @PutMapping("/{id}")
    @PreAuthorized("hasAnyRole('ADMIN','COORDINADOR')")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id,@Valid @RequestBody Product product) {
        return ResponseEntity.ok(productService.update(id,product));
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorized("hasAnyRole('ADMIN')")
    public ResponseEntity<Product> deleteProduct(@PathVariable Long id,@Valid @RequestBody Product product) {
        productService.delete(id);
        return ResponseEntity.ok(new MessageResponse("producto eliminado exitosamente"));
    }
    

    
}
