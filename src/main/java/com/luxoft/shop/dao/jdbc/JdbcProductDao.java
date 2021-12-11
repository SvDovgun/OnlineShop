package com.luxoft.shop.dao.jdbc;

import com.luxoft.shop.dao.ProductDao;
import com.luxoft.shop.dao.jdbc.mapper.ProductRowMapper;
import com.luxoft.shop.entity.Product;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class JdbcProductDao implements ProductDao {
    private static final ProductRowMapper PRODUCT_ROW_MAPPER = new ProductRowMapper();

    private static String url = "jdbc:postgresql://192.168.1.102:5000/camstore";
    private static String user = "postgres";
    private static String password = "postgres";
    private static final String FIND_ALL_SQL = "SELECT id, name, price, notes, creationdate FROM PRODUCTS ORDER BY id ASC;";
    private static final String ADD_SQL = "INSERT INTO PRODUCTS (name, price, notes, creationdate) VALUES(?, ?, ?, ?);";
    private static final String EDIT_SQL = "UPDATE products SET name=?, price=?, notes=?, creationdate=? WHERE id=?;";
    private static final String FIND_BY_ID_SQL = "Select id, name, price, notes, creationdate FROM PRODUCTS where id = ?;";


    @Override
    public List<Product> findAll() {
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_SQL);
            ResultSet resultSet = preparedStatement.executeQuery()) {

            List<Product> products = new ArrayList<>();
            while (resultSet.next()) {
                Product product = PRODUCT_ROW_MAPPER.mapRow(resultSet);
                products.add(product);
            }
            return products;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void add(Product product) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_SQL))
        {
             preparedStatement.setString(1, product.getName());
             preparedStatement.setDouble(2, product.getPrice());
             preparedStatement.setString(3, product.getNotes());
             preparedStatement.setTimestamp(4, product.getCreationDate());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Product is not created ", e);
        }
    }

    @Override
    public void edit(Product product) {
        try (
                Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(EDIT_SQL))
        {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setString(3, product.getNotes());
            preparedStatement.setTimestamp(4, product.getCreationDate());
            preparedStatement.setInt(5, product.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Product is not updated ", e);
        }

    }

    @Override
    public Product findById(int id) {
        try (
                Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL))

        {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            Product product = PRODUCT_ROW_MAPPER.mapRow(resultSet);
            return product;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }



    private static void printResult(ResultSet resultSet) throws SQLException {
        int id;
        String name;
        double price;
        String notes;
        Date creationDate;
        while (resultSet.next()){
            id = resultSet.getInt("id");
            name = resultSet.getString("name");
            price = resultSet.getDouble("price");
            notes = resultSet.getString("notes");
            creationDate = resultSet.getDate("creationdate");

            System.out.println("id: " + id);
            System.out.println("name: " + name);
            System.out.println("price: " + price);
            System.out.println("notes: " + notes);
            System.out.println("creationDate: " + creationDate);


        }
    }

    private static ResultSet getAllProducts(String tableName) throws SQLException {
        Statement query;
        ResultSet resultSet;
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            query = connection.createStatement();
            resultSet = query.executeQuery("SELECT * FROM "+ tableName + ";");
        }

        return resultSet;
    }


}
