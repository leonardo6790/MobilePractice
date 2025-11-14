package com.app.backend.model;

import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.list;

@Data
@Entity
@Table(name ="products")

public class Product {
    @Id
    @GeneretedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (nullable = false, unique = true)
    private String name;

    @Column (length = 1000)
    private String description;
    
    @Column (nullable = false, unique = true)
    private Double price ;

    private Integer stock;

    @Column (nullable =  false)
    private Boolean active = true;


    @ManyToOne
    @JoinColumn(name="category_id", nullable = false)
    private Category category;


    @ManyToOne
    @JoinColumn(name="subcategory_id", nullable = false)
    private SubCategory subcategory;


    public String getName(){
        this.name = name;
    }

    public void setName(){
        this.name = name;
    }

    public String getDescription(){
        return description;
    }

    public Void setDescription(){
        this.description = description;
    }

    public Double getPrice(){
        return price;
    }

    public void setPrice(Double price){
        this.price = price;
    }

    public Integer getStock(){
        return stock;
    }

    public void setStock(Integer stock){
        this.stock = stock;
    }

    public Boolean getActive(){
        return active;
    }

    public Void setActive(){
        this.active = active;
    }

    public Category getsubCategory(){
        return subcategory;
    }

    public void setSubCategory(SubCategory subcategory){
        this.subcategory =  subcategory;
    }
}
