package com.example.inventorymanagement.database;

import com.example.inventorymanagement.models.Order;
import com.example.inventorymanagement.models.Order;
import com.example.inventorymanagement.models.Order;
import com.example.inventorymanagement.utils.ScreenUtils;
import com.example.inventorymanagement.utils.StringUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderDao {
    public static boolean addOrder(Order order){
        int stock = ProductDao.getProductStock(order.getpId());
        boolean res = false;
        DbConnection dbConnection = new DbConnection();
        Connection connection = dbConnection.getConnection();
        try {
            if(order.getQuantity() > stock){
                throw new Exception("Quantity greater than stock available");
            }
            PreparedStatement preparedStatement = connection.prepareStatement(StringUtils.createOrderQuery);
            preparedStatement.setInt(1, order.getId());
            preparedStatement.setString(2, order.getcName());
            preparedStatement.setInt(3, order.getpId());
            preparedStatement.setInt(4, order.getQuantity());
            preparedStatement.setDate(5, order.getDeliveryDate());
            res = preparedStatement.execute();
            ProductDao.updateProductStock(order.getpId(), stock-order.getQuantity());
        } catch (Exception e) {
            ScreenUtils.showAlertDialog(Alert.AlertType.ERROR, "", e.getMessage());
        }
        return res;
    }
    public static ObservableList<Order> getOrders() throws SQLException {
        ObservableList<Order> temp = FXCollections.observableArrayList();
        DbConnection dbConnection = new DbConnection();
        Connection connection = dbConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(StringUtils.selectOrderQuery);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()){
            Order newOrder = new Order(rs);
            temp.add(newOrder);
        }
        return temp;
    }

    public static void deleteOrder(Order order) {
        DbConnection dbConnection = new DbConnection();
        Connection connection = dbConnection.getConnection();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(StringUtils.deleteOrderQuery);
            preparedStatement.setInt(1, order.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            ScreenUtils.showAlertDialog(Alert.AlertType.ERROR, "", e.getMessage());
        }
    }
}
