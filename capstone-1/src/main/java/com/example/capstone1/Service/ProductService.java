package com.example.capstone1.Service;


import com.example.capstone1.Model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ProductService {
    private final CategoryService categoryService;
    ArrayList<Product> Products = new ArrayList<>();
    ArrayList<Product> listOrder = new ArrayList<>();

    public ArrayList<Product> getListOrder() {
        return listOrder;
    }

    public ProductService(CategoryService categoryService) {
        this.categoryService = categoryService;

    }

    public ArrayList<Product> getProducts() {
        return Products;
    }

    public boolean addProduct(Product product){
        for (int i = 0; i < categoryService.getCategories().size(); i++) {
            if (product.getCategoryId().equals(categoryService.getCategories().get(i).getId())) {
                Products.add(product);
                return true;
            }

        }
        return false;
    }

    public boolean updateProduct(String id,Product product){
        for (int i = 0; i < Products.size(); i++) {
            if(Products.get(i).getId().equals(id)){
                Products.set(i,product);
                return true;
            }
        }
        return false;
    }

    public boolean deleteProduct(String id){
        for (int i = 0; i < Products.size(); i++) {
            if(Products.get(i).getId().equals(id)){
                Products.remove(i);
                return true;
            }
        }
        return false;
    }




}
