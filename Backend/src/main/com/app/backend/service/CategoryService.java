package com.app.backend.service;

import com.app.backend.model.Category;
import com.app.backend.model.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.streotype.service;
import java.util.list;

@service
public class CategoryService{

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> findAll(){
        return categoryRepository.findAll();
    } 

    public Category findById(Long id){
        return categoryRepository.findById(id).orElseThrow(()-> new RunTimeException("Categoria no encontrada"));
    }

    public Category create(Category category){
        return CategoryRepository.save(category);
    }

    public Category update(Long id, Category categoryDetails){
        Category category = findById(id);
        category.setName(categoryDetails.getName());
        category.setDescription(categoryDetails.getDescription());
        category.setActive(categoryDetails.getActive());
        return categoryRepository.save(category);
    }

    public void delete(Long id){
        Category category = findById(id);
        categoryRepository.delete(category);
    }

}
