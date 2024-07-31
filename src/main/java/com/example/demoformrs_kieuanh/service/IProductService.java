package com.example.demoformrs_kieuanh.service;

import com.example.demoformrs_kieuanh.model.Product;

import java.util.List;

public interface IProductService {
    List<Product> findAll();
   Product findById(int id);
    void addProduct(Product product);
    void updateProduct(int id, Product product);
    void deleteProduct(int id);

}
