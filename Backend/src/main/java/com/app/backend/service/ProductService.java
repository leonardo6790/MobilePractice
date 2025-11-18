package com.app.backend.service;

import com.app.backend.model.Product;
import com.app.backend.model.Category;
import com.app.backend.model.Subcategory;
import com.app.backend.repository.ProductRepository;
import com.app.backend.repository.SubcategoryRepository;
import com.app.backend.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService{

    @Autowired
    private SubcategoryRepository subcategoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Product> findAll(){
        return productRepository.findAll();
    } 

    public List<Product> findByCategoryId(Long categoryId){
        return productRepository.findByCategory_Id(categoryId);
    } 

    public List<Product> findBySubcategoryId(Long subcategoryId){
        return productRepository.findBySubcategory_Id(subcategoryId);
    } 

    public Product findById(Long id){
        return productRepository.findById(id).orElseThrow(()-> new RuntimeException("Producto no encontrado"));
    }

    public Product create(Product product){
        if (product.getCategoryId() != null) {
            Category category = categoryRepository.findById(product.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
            product.setCategory(category);
        }
        
        if (product.getSubcategoryId() != null) {
            Subcategory subcategory = subcategoryRepository.findById(product.getSubcategoryId())
                .orElseThrow(() -> new RuntimeException("Subcategoría no encontrada"));
            product.setSubcategory(subcategory);
        }
        
        return productRepository.save(product);
    }

    public Product update(Long id, Product productDetails){
        Product product = findById(id);
        product.setName(productDetails.getName());
        product.setDescription(productDetails.getDescription());
        product.setPrice(productDetails.getPrice());
        product.setStock(productDetails.getStock());
        product.setActive(productDetails.getActive());
        
        if (productDetails.getCategoryId() != null) {
            Category category = categoryRepository.findById(productDetails.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
            product.setCategory(category);
        }
        
        if (productDetails.getSubcategoryId() != null) {
            Subcategory subcategory = subcategoryRepository.findById(productDetails.getSubcategoryId())
                .orElseThrow(() -> new RuntimeException("Subcategoría no encontrada"));
            product.setSubcategory(subcategory);
        }
        
        return productRepository.save(product);
    }

    public void delete(Long id){
        Product product = findById(id);
        productRepository.delete(product);
    }
}
