package com.scaler.productcatalogservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {
    private Long id;
    private String name;
    private String description;
    private String image_url;
    private double price;
    private CategoryDto categoryDto;
}