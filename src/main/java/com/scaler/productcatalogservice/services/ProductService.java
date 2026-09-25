package com.scaler.productcatalogservice.services;

import com.scaler.productcatalogservice.dtos.ProductDto;
import com.scaler.productcatalogservice.models.Category;
import com.scaler.productcatalogservice.models.Product;
import com.scaler.productcatalogservice.repositories.CategoryRepo;
import com.scaler.productcatalogservice.repositories.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Component("productService")
public class ProductService implements IProductService{
    @Autowired
    ProductRepo productrepo;
    @Autowired
    private CategoryRepo categoryrepo;

    @Override
    public Product getProductById(Long id) {
        return productrepo.findById(id).get();
    }

    @Override
    public List<Product> getAllProducts() {
        return productrepo.findAll();
    }

    @Override
    public Product createProduct(ProductDto productDto) {
        Product product =from(productDto);

        Optional<Category> category=categoryrepo.findByName(productDto.getCategoryDto().getName());
        if(category.isPresent()){
            //Category already exists
            product.setCategory(category.get());
        }else{
            // Category does not exist
            Category savedCategory = categoryrepo.save(product.getCategory());
            product.setCategory(savedCategory);
        }
        return productrepo.save(product);
    }

    @Override
    public Product replaceProduct(Long id, ProductDto productDto) {
        Product product =from(productDto);
        product.setId(id);//JPA checked if id is existed then replace or newly added product
        product.getCategory().setId(productDto.getCategoryDto().getId());
        return productrepo.save(product);
    }
    public Product from(ProductDto productDto) {
        Product product=new Product();
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setImage_url(productDto.getImage_url());
        product.setDescription(productDto.getDescription());
        if(productDto.getCategoryDto()!=null){
            Category category=new Category();
            category.setName(productDto.getCategoryDto().getName());
            category.setDescription(productDto.getCategoryDto().getDescription());
            product.setCategory(category);
        }
        return product;
    }
}
