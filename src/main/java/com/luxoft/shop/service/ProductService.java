package com.luxoft.shop.service;

import com.luxoft.shop.dao.ProductDao;
import com.luxoft.shop.entity.Product;

import java.util.List;

public class ProductService {
    private ProductDao productDao;

    public ProductService(ProductDao productDao) {
        this.productDao = productDao;
    }

    public List<Product> findAll() {
        List<Product> products = productDao.findAll();
        System.out.println("Products size " +  products.size());
        return  products;
    }

}
