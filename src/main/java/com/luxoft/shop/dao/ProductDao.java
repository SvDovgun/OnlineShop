package com.luxoft.shop.dao;

import com.luxoft.shop.entity.Product;

import java.util.List;

public interface ProductDao {
    List<Product> findAll();

    void add(Product product);

    void edit(Product product);

    Product findById(int id);

    void delete(int id);
}
