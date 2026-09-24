package com.scaler.productcatalogservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
@Entity
public class Product extends BaseModel{
    private String name;
    private String description;
    private String image_url;
    private double price;
    @ManyToOne
    private Category category;

    //bussiness Specific
    public Product(){
        this.setCreated_At(new Date());
        this.setLastUpdate(new Date());
        this.setState(State.Active);
    }
}
