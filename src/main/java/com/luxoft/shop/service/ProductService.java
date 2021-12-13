package com.luxoft.shop.service;

import com.luxoft.shop.dao.ProductDao;
import com.luxoft.shop.entity.Product;
import lombok.extern.slf4j.Slf4j;
import java.sql.Timestamp;
import java.time.LocalDateTime;
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

    public void add(Product product) {
        Timestamp now = Timestamp.valueOf(LocalDateTime.now());
        product.setCreationDate(now);
        productDao.add(product);
    //    System.out.println("Product successfully added");
    }

    public Product findById(int id) {
        Product product = productDao.findById(id);
        return product;
    }

    public void edit(Product product) {
        Timestamp now = Timestamp.valueOf(LocalDateTime.now());
        product.setCreationDate(now);
        productDao.edit(product);
    }

    public void delete(int id) {
        productDao.delete(id);
    }
}
