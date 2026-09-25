package com.scaler.productcatalogservice.controllers;

import com.scaler.productcatalogservice.dtos.CategoryDto;
import com.scaler.productcatalogservice.dtos.ProductDto;
import com.scaler.productcatalogservice.models.Product;
import com.scaler.productcatalogservice.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {
    @Autowired
    @Qualifier("productService")
    private IProductService productService;

    @GetMapping("/product/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable("id") Long id) {
        if(id<=0){
            throw new IllegalArgumentException("product id available in between 3 to 14");
        }
        Product product=productService.getProductById(id);
        if(product==null){
            throw new RuntimeException("product not found and Please provide valid input");
        }
        ProductDto productDto=from(product);
        MultiValueMap<String,String> header=new LinkedMultiValueMap<>();
        header.add("id","Showing to Learners");
        return new ResponseEntity<>(productDto,header, HttpStatus.OK);
    }
    @GetMapping("/allProducts")
    public List<ProductDto> getAllProducts(){
        List<ProductDto> productDtos=new ArrayList<>();
        List<Product> products=productService.getAllProducts();
        for(Product product:products){
            ProductDto productDto=from(product);
            productDtos.add(productDto);
        }
        return productDtos;
    }
    @PutMapping("/product/{id}")
    public ProductDto replace(@PathVariable("id") Long id,@RequestBody ProductDto productDto){
        Product product=productService.replaceProduct(id,productDto);
        return from(product);
    }
    @PostMapping("/product")
    public ProductDto createProduct(@RequestBody ProductDto productDto){
        Product product=productService.createProduct(productDto);
        if(product==null){
            return null;
        }
        ProductDto productDto1=from(product);
        return productDto1;
    }
    private ProductDto from(Product product){
        //product object transfer to the ProductDto
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setPrice(product.getPrice());
        productDto.setImage_url(product.getImage_url());
        if(product.getCategory()!=null){
            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setId(product.getCategory().getId());
            categoryDto.setName(product.getCategory().getName());
            categoryDto.setDescription(product.getCategory().getDescription());
            productDto.setCategoryDto(categoryDto);
        }
        return productDto;
    }
}
