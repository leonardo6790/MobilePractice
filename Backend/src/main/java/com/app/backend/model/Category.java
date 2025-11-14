package com.app.backend.model;

import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.list;

@Data
@Entity
@Table(name ="categories")

public class Category {
    @Id
    @GeneretedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (nullable = false, unique = true)
    private String name;

    @Column (length = 500)
    private String description;

    @Column (nullable =  false)
    private Boolean active = true;

    @OneToMany (mapperBy = "category" Cascade = CascadeType.All)
    @JsonIgnore
    private List<SubCategory> subCategories;

    public Long getId (){
        return id;
    }

    public void setId (){
        this.id = id;
    }

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

    public List<SubCategory> getSubcategories(){
        return subCategories;
    }

    public Void setSubcategories(List<SubCategory> subCategories){
        this.subCategories = subCategories;
    }
}
