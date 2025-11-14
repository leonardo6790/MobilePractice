package com.app.backend.service;

import com.app.backend.model.Product;
import com.app.backend.model.PoductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.streotype.service;
import java.util.list;

@service
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
        return productRepository.findByCategoryId(categoryId);
    } 

    public List<Product> findBySubcategoryId(Long subcategoryId){
        return productRepository.findBySubcategoryId(subcategoryId);
    } 

    public Product findById(Long id){
        return productRepository.findById(id).orElseThrow(()-> new RunTimeException("Categoria no encontrada"));
    }

    public Product create(Product product){
        return productRepository.save(product);
    }

    public Product update(Long id, Product productDetails){
        Product product = findById(id);
        product.setName(productDetails.getName());
        product.setDescription(productDetails.getDescription());
        product.setPrice(productDetails.getPrice());
        product.setStock(productDetails.getStock());
        product.setActive(productDetails.getActive());
        product.setCategory(productDetails.getCategory());
        product.setSubCategory(productDetails.getsubCategory());
        return productRepository.save(product);
    }

    public void delete(Long id){
        Product product = findById(id);
        productRepository.delete(product);
    }
}
