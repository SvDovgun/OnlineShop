package com.luxoft.shop.dao.jdbc.mapper;

import com.luxoft.shop.entity.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class ProductRowMapper {

    public Product mapRow(ResultSet resultSet) throws SQLException {

        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        int price = resultSet.getInt("price");
        String notes = resultSet.getString("notes");
        Timestamp creationDate = resultSet.getTimestamp("creationdate");
        Product product =Product.builder().
                id(id)
                .name(name)
                .price(price)
                .notes(notes)
                .creationDate(creationDate)
                .build();
        return product;
    }

}
