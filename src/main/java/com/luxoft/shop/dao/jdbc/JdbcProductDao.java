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
   // private static String user = "shop_user";
    private static String password = "postgres";
  //  static String password = "psw";
    private static String tableName = "products";
    private static final String FIND_ALL_SQL = "SELECT id, name, price, notes, creationdate FROM PRODUCTS;";

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

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    public static void main(String[] args) throws SQLException {


        printResult(getAllProducts(tableName));
    }

    private static void printResult(ResultSet resultSet) throws SQLException {
        int id;
        String name;
        int price;
        String notes;
        Date creationDate;
        while (resultSet.next()){
            id = resultSet.getInt("id");
            name = resultSet.getString("name");
            price = resultSet.getInt("price");
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
