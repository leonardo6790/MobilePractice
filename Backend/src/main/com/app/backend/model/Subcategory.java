package com.app.backend.model;

import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.list;

@Data
@Entity
@Table(name ="Subcategory")

public class Subcategory {
    @Id
    @GeneretedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (nullable = false, unique = true)
    private String name;

    @Column (length = 500)
    private String description;

    @Column (nullable =  false)
    private Boolean active = true;

    @OneToMany (mapperBy = "subcategory" Cascade = CascadeType.All)
    @JsonIgnore
    private List<Product> products;

    @ManyToOne
    @JoinColumn(name="category_id", nullable = false)
    private Category category;


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

    public Boolean getActive(){
        return active;
    }

    public Void setActive(){
        this.active = active;
    }

    public Category getCategory(){
        return category;
    }

    public void setCategory(Category category){
        this.category =  category;
    }
}
