package com.example.inventorymanagement.database;

import com.example.inventorymanagement.models.Product;
import com.example.inventorymanagement.utils.StringUtils;

import java.sql.*;
import java.util.ArrayList;

public class ProductDao {
    public static boolean addProduct(Product product){
        boolean res = false;
        DbConnection dbConnection = new DbConnection();
        Connection connection = dbConnection.getConnection();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(StringUtils.createProductQuery);
            preparedStatement.setInt(1, product.getId());
            preparedStatement.setString(2, product.getName());
            preparedStatement.setInt(3, product.getPrice());
            preparedStatement.setInt(4, product.getStock());
            res = preparedStatement.execute();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return res;
    }
    public static ArrayList<Product> getProducts() throws SQLException {
        ArrayList<Product> temp = new ArrayList<>();
        DbConnection dbConnection = new DbConnection();
        Connection connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(StringUtils.selectProductQuery);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                Product newProduct = new Product(rs);
                temp.add(newProduct);
            }

        return temp;
    }

    public static void updateProduct(Product product){
        DbConnection dbConnection = new DbConnection();
        Connection connection = dbConnection.getConnection();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(StringUtils.updateProductQuery);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setInt(2, product.getPrice());
            preparedStatement.setInt(3, product.getStock());
            preparedStatement.setInt(4, product.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteProduct(Product product) {
        DbConnection dbConnection = new DbConnection();
        Connection connection = dbConnection.getConnection();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(StringUtils.deleteProductQuery);
            preparedStatement.setInt(1, product.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
