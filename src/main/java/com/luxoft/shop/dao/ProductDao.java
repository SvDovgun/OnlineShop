package com.luxoft.shop.dao;

import com.luxoft.shop.entity.Product;

import java.util.List;

public interface ProductDao {
    List<Product> findAll();
}
