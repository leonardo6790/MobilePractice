package com.app.backend.service;

import com.app.backend.model.Subcategory;
import com.app.backend.repository.SubcategoryRepository;
import com.app.backend.model.Category;
import com.app.backend.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SubcategoryService{

    @Autowired
    private SubcategoryRepository subcategoryRepository;

    @Autowired
    private CategoryRepository categoryRepository;


    public List<Subcategory> findAll(){
        return subcategoryRepository.findAll();
    } 

    public List<Subcategory> findByCategoryId(Long categoryId){
        return subcategoryRepository.findByCategory_Id(categoryId);
    } 

    public Subcategory findById(Long id){
        return subcategoryRepository.findById(id).orElseThrow(()-> new RuntimeException("Subcategoria no encontrada"));
    }

    public Subcategory create(Subcategory subcategory){
        return subcategoryRepository.save(subcategory);
    }

    public Subcategory createWithCategoryId(String name, String description, Long categoryId, Boolean active){
        Category category = categoryRepository.findById(categoryId)
            .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
        
        Subcategory subcategory = new Subcategory();
        subcategory.setName(name);
        subcategory.setDescription(description);
        subcategory.setActive(active != null ? active : true);
        subcategory.setCategory(category);
        
        return subcategoryRepository.save(subcategory);
    }


    public Subcategory updateWithCategoryId(Long id, String name, String description, Long categoryId, Boolean active){
        Subcategory subcategory = findById(id);
        Category category = categoryRepository.findById(categoryId)
            .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
        
        subcategory.setName(name);
        subcategory.setDescription(description);
        subcategory.setActive(active != null ? active : true);
        subcategory.setCategory(category);
        
        return subcategoryRepository.save(subcategory);
    }

    public void delete(Long id){
        Subcategory subcategory = findById(id);
        subcategoryRepository.delete(subcategory);
    }

}
