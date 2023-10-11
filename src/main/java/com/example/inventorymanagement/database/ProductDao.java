package com.example.inventorymanagement.database;

import com.example.inventorymanagement.models.Product;
import com.example.inventorymanagement.utils.ScreenUtils;
import com.example.inventorymanagement.utils.StringUtils;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

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
            ScreenUtils.showAlertDialog(Alert.AlertType.ERROR, "", e.getMessage());
        }
        return res;
    }
    public static ObservableList<Product> getProducts() throws SQLException {
        ObservableList<Product> temp = FXCollections.observableArrayList();
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
            ScreenUtils.showAlertDialog(Alert.AlertType.ERROR, "", e.getMessage());
        }
    }

    public static Product getProductById(int id) throws SQLException {
        Product product = null;
        DbConnection dbConnection = new DbConnection();
        Connection connection = dbConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(StringUtils.getProductByIdQuery);
        preparedStatement.setInt(1, id);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()){
            product = new Product(rs);
        }
        return product;
    }

    public static void updateProductStock(int id, int newStock) throws SQLException {
        Product product = getProductById(id);
        product.setStock(newStock);
        updateProduct(product);
    }

    public static int getProductStock(int id){
        int stock = 0;
        DbConnection dbConnection = new DbConnection();
        Connection connection = dbConnection.getConnection();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(StringUtils.getProductStock);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                stock = rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return stock;
    }

    public static void deleteProduct(Product product) {
        DbConnection dbConnection = new DbConnection();
        Connection connection = dbConnection.getConnection();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(StringUtils.deleteProductQuery);
            preparedStatement.setInt(1, product.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            ScreenUtils.showAlertDialog(Alert.AlertType.ERROR, "", e.getMessage());
        }
    }

    public static ObservableList<Product> getLeastStockedProducts(){
        ObservableList<Product> temp = FXCollections.observableArrayList();
        DbConnection dbConnection = new DbConnection();
        Connection connection = dbConnection.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(StringUtils.getLeastProductQuery);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                Product newProduct = new Product(rs);
                temp.add(newProduct);
            }
        } catch (SQLException e) {
            ScreenUtils.showAlertDialog(Alert.AlertType.ERROR, "", e.getMessage());
        }
        return  temp;
    }

}
