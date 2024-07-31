package com.example.demoformrs_kieuanh.service;

import com.example.demoformrs_kieuanh.model.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductService implements IProductService {
    private static List<Product> products;
    static {
        products =new ArrayList<>();
        products.add(new Product(0,"Ao PA",25000));
        products.add(new Product(1,"kiem PA",50000));
        products.add(new Product(2,"chan PA",100000));
    }
    @Override
    public List<Product> findAll() {
        return products ;
    }

    @Override
    public Product findById(int id) {
        for (int i=0;i<products.size();i++) {
            Product product = products.get(i);
            if (product.getId()==id) {
                return product;
            }
        }
        return null;
    }

    @Override
    public void addProduct(Product product) {
    products.add(product);
    }

    @Override
    public void updateProduct(int id, Product product) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == id) {
                products.set(i, product);
                return;
            }
        }
    }


    @Override
    public void deleteProduct(int id) {
        products.removeIf(product -> product.getId() == id);
    }
}
